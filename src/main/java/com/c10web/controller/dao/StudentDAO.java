package com.c10web.controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.c10web.controller.servlet.DatabaseConnection;
import com.c10web.model.Student;

public class StudentDAO {
	private Connection conn;
	public StudentDAO() throws ClassNotFoundException, SQLException {
		this.conn = DatabaseConnection.getConnection();
	
	}
	public boolean addStudent(Student student) {
		boolean isRowInserted = false;
		String query ="INSERT INTO student(usernamem, password)"+"VALUES(?,?)";
		if(conn != null) {
			try {
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, student.getUsername());
				ps.setString(2, student.getPassword());
				
				int rows = ps.executeUpdate();
				if (rows > 0) {
					isRowInserted = true;
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		return isRowInserted;
	}
}
