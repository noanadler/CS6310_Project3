package arms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;

import java.sql.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CourseCatalog {

	private JFrame frame;
	private JTable table;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseCatalog window = new CourseCatalog();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	Connection connection = null;
	private JButton btnClose;
	
	/**
	 * Create the application.
	 */
	public CourseCatalog() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1135, 707);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCourseCatalog = new JLabel("Course Catalog");
		lblCourseCatalog.setForeground(Color.BLACK);
		lblCourseCatalog.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblCourseCatalog.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourseCatalog.setBounds(438, 11, 262, 80);
		frame.getContentPane().add(lblCourseCatalog);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 1104, 510);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		connection = sqliteConnection.dbConnector();
		
		try{
			//String query = "select * from CourseOfferings";
			String query = "select * from Courses";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			btnClose = new JButton("Close");
			btnClose.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					frame.dispose();
				}
			});
			btnClose.setBounds(1025, 625, 89, 23);
			frame.getContentPane().add(btnClose);
			
			
		}catch (Exception e){
			e.printStackTrace();
		}
		
		table.getColumnModel().getColumn(2).setMinWidth(150);
		
//		table.getColumnModel().getColumn(0).setHeaderValue("Course ID");
//		table.getColumnModel().getColumn(1).setHeaderValue("Course Number");
//		table.getColumnModel().getColumn(2).setHeaderValue("Course Title");
//		table.getColumnModel().getColumn(3).setHeaderValue("Semesters Available");
//		table.getColumnModel().getColumn(4).setHeaderValue("Class Size");
//		table.getColumnModel().getColumn(5).setHeaderValue("Remaining Seats");
		
		
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//			},
//			new String[] {
//				//"Course ID", "Course Title", "Semesters Offered", "Year", "Class Size", "Seats Remaining"
//				"ID", "CourseID", "FallSem", "SpringSem", "SummerSem", "ClassSize", "RemSeats", "CourseTitle"
//			}
//		) {
//			Class[] columnTypes = new Class[] {
//				Object.class, Object.class, String.class, String.class, Object.class, Object.class
//			};
//			public Class getColumnClass(int columnIndex) {
//				return columnTypes[columnIndex];
//			}
//		});
		
	}
}
