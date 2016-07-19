package arms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import javax.swing.*;

public class StudentFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldCourse1;
	private JTextField textFieldCourse2;
	private JTextField textFieldCourse3;
	private JTextField textFieldCourse4;
	private JTextField textFieldCourse5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentFrame frame = new StudentFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 721, 596);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblStudentFrame = new JLabel("Student Menu");
		lblStudentFrame.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentFrame.setForeground(Color.RED);
		lblStudentFrame.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblStudentFrame.setBounds(261, 11, 163, 14);
		contentPane.add(lblStudentFrame);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.setVisible(false);
				dispose();
				ARMS_LogIn.main(null);					
			}
		});
		btnLogOut.setBounds(597, 521, 89, 23);
		contentPane.add(btnLogOut);
		
		JButton btnViewCourseCatalog = new JButton("View Course Catalog");
		btnViewCourseCatalog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//CourseCatalog catalog = new CourseCatalog();
					CourseCatalog.NewScreen();
			}
		});
		btnViewCourseCatalog.setBounds(55, 60, 200, 25);
		contentPane.add(btnViewCourseCatalog);
		
		JLabel lblEnterCourseIds = new JLabel("Enter Course IDs You Would Like to Take:");
		lblEnterCourseIds.setBounds(55, 119, 369, 30);
		contentPane.add(lblEnterCourseIds);
		
		textFieldCourse1 = new JTextField();
		textFieldCourse1.setBounds(55, 187, 85, 25);
		contentPane.add(textFieldCourse1);
		textFieldCourse1.setColumns(10);
		
		JLabel lblCourse = new JLabel("Course 1:");
		lblCourse.setBounds(54, 162, 87, 14);
		contentPane.add(lblCourse);
		
		JLabel lblCourse_1 = new JLabel("Course 2:");
		lblCourse_1.setBounds(168, 162, 87, 14);
		contentPane.add(lblCourse_1);
		
		textFieldCourse2 = new JTextField();
		textFieldCourse2.setColumns(10);
		textFieldCourse2.setBounds(169, 187, 85, 25);
		contentPane.add(textFieldCourse2);
		
		JLabel lblCourse_2 = new JLabel("Course 3:");
		lblCourse_2.setBounds(286, 162, 87, 14);
		contentPane.add(lblCourse_2);
		
		textFieldCourse3 = new JTextField();
		textFieldCourse3.setColumns(10);
		textFieldCourse3.setBounds(288, 187, 85, 25);
		contentPane.add(textFieldCourse3);
		
		JLabel lblCourse_3 = new JLabel("Course 4:");
		lblCourse_3.setBounds(410, 162, 87, 14);
		contentPane.add(lblCourse_3);
		
		textFieldCourse4 = new JTextField();
		textFieldCourse4.setColumns(10);
		textFieldCourse4.setBounds(410, 187, 85, 25);
		contentPane.add(textFieldCourse4);
		
		textFieldCourse5 = new JTextField();
		textFieldCourse5.setColumns(10);
		textFieldCourse5.setBounds(543, 187, 85, 25);
		contentPane.add(textFieldCourse5);
		
		JLabel lblCourse_4 = new JLabel("Course 5:");
		lblCourse_4.setBounds(541, 162, 87, 14);
		contentPane.add(lblCourse_4);
		
		JButton btnSubmitCourseRequests = new JButton("Submit Course Requests");
		btnSubmitCourseRequests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String courses = null;
				ArrayList<Integer> courseIDs = new ArrayList<Integer>();				
				
				if (!textFieldCourse1.getText().isEmpty()){
					courses = textFieldCourse1.getText();
					courseIDs.add(Integer.parseInt(textFieldCourse1.getText()));
					
					if (!textFieldCourse2.getText().isEmpty()){
						courses += ", " + textFieldCourse2.getText();
						courseIDs.add(Integer.parseInt(textFieldCourse2.getText()));
						
						if (!textFieldCourse3.getText().isEmpty()){
							courses += ", " + textFieldCourse3.getText();
							courseIDs.add(Integer.parseInt(textFieldCourse3.getText()));
							
							if (!textFieldCourse4.getText().isEmpty()){
								courses += ", " + textFieldCourse4.getText();
								courseIDs.add(Integer.parseInt(textFieldCourse4.getText()));
								
								if (!textFieldCourse5.getText().isEmpty()){
									courses += ", " + textFieldCourse5.getText();
									courseIDs.add(Integer.parseInt(textFieldCourse5.getText()));
								}
							}
						}
					}
					
					JOptionPane.showMessageDialog(null, "You have selected the following courses: " + courses);
					
				} else {
					JOptionPane.showMessageDialog(null, "You must enter at least one course ID.");
				}						

			}
		});
		btnSubmitCourseRequests.setBounds(55, 237, 200, 25);
		contentPane.add(btnSubmitCourseRequests);
	}
}
