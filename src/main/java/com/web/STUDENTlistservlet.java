package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.util.JDBCutil;
import com.entityOJO.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/list")
public class STUDENTlistservlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	    Connection connection = null;
		Statement statement = null;
		
		List<Student> data = new ArrayList<>();
		 
	    try {
			
	    	connection=JDBCutil.getConnection();
			statement = connection.createStatement();
			System.out.println(4);
			
			String query = "select* from student";
			ResultSet resultset = statement.executeQuery(query);
			
			
			while(resultset.next()) {
			
		    int id = resultset.getInt("id");
			String name = resultset.getString("name");
			Double marks = resultset.getDouble("marks");
			int rollnum = resultset.getInt("rollnum"); 
			String grade = resultset.getString("grade");
			String city = resultset.getString("city");
			
			Student s = new Student(id,name,marks,rollnum,grade,city);
			data.add(s);
			
			
			 }
				
	    	
		} catch (Exception e) {
			System.out.println("There is problem while fetching database");
		}finally {
			//closing resources
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	    if(data!=null){
	    	req.setAttribute("data",data);
	    }
	    
		RequestDispatcher rd = req.getRequestDispatcher("studentlist.jsp");
		rd.forward(req, resp);
	}

}
