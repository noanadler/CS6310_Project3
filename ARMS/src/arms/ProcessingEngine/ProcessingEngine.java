package arms.ProcessingEngine;

import arms.api.CourseInstance;
import arms.api.ScheduleRequest;
import arms.api.Student;
import arms.dataAccess.DbActions;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ProcessingEngine {
    private static List<CourseInstance> courseOfferings;
    private static List<ScheduleRequest> recentStudentRequests;
    private static List<Student> students;
    private static HashMap<Integer,CourseInstance> offeringsMap;
    private static HashMap<Integer,ScheduleRequest> requestsMap;
    private static boolean shadowMode = false;

    static {
        courseOfferings = DbActions.getCatalog();
        recentStudentRequests = DbActions.getAllRecentScheduleRequests();
        students = DbActions.getStudents();
        prioritizeStudents();
        offeringsMap = new HashMap<>();
        requestsMap = new HashMap<>();
        for(CourseInstance offering : courseOfferings){
            offeringsMap.put(offering.getId(), offering);
        }
        for(ScheduleRequest request : recentStudentRequests){
            requestsMap.put(request.getStudentId(), request);
        }
        GurobiAdapter.updateOfferingConstraints(courseOfferings);
        GurobiAdapter.updateRequestConstraints(recentStudentRequests);
        GurobiAdapter.updatePriorityConstraints(students);
    }
    public static List<CourseInstance> getCourseOfferings() {
        return courseOfferings;
    }
    public static List<ScheduleRequest> getRecentStudentRequests() {
        return recentStudentRequests;
    }
    public static void setCourseOfferings(List<CourseInstance> offerings) {
        courseOfferings = offerings;
    }
    public static void setRecentStudentRequests(List<ScheduleRequest> requests) {
        recentStudentRequests = requests;
    }
    public static void prioritizeStudents(){
        Collections.sort(students); //Sorts descending based on rank - See impl. in Student.compareTo
    }
    public static void updateScheduleRequest(ScheduleRequest newRequest){
        recentStudentRequests.stream().filter(request -> request.getStudentId() == newRequest.getStudentId()).forEach(request -> {
            recentStudentRequests.remove(request); //There should be exactly one request that gets removed.
        });
        recentStudentRequests.add(newRequest);
        GurobiAdapter.updateRequestConstraints(recentStudentRequests);
    }
    public static void executeRequest(){
        List<ScheduleRequest> res = GurobiAdapter.processConstraints();
        if(res == null) return; //Problem unfeasible.
        recentStudentRequests = res;
        for(CourseInstance instance : courseOfferings){
            instance.setRemSeats(instance.getClassSize()); //Reset remaining seats.
        }
        for(ScheduleRequest request : res){
            for(Integer offeringId : request.getRequestedCourses().values()){
                if(offeringId == null)continue;
                CourseInstance offering = offeringsMap.get(offeringId);
                offering.setRemSeats(offering.getRemSeats()-1);
            }
        }
        //If not in shadow mode, update offerings (rem seats) and schedule request results in the db.
    }
}
