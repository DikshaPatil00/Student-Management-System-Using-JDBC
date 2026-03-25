package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.util.JDBCutil;
import com.entityOJO.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/delete")
public class deleteSTUDENTservlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sid = Integer.parseInt(req.getParameter("id"));
		
		Connection connection = null;
		PreparedStatement statement = null;
		Student s = null;
		
	    try {
			
	    	connection=JDBCutil.getConnection();
	    	String query = " select * from student where id=? ";
			statement = connection.prepareStatement(query);
			statement.setInt(1, sid);
			
			ResultSet resultset = statement.executeQuery();
			
			while(resultset.next()) {
			
		    int id = resultset.getInt("id");
			String name = resultset.getString("name");
			Double marks = resultset.getDouble("marks");
			int rollnum = resultset.getInt("rollnum"); 
			String grade = resultset.getString("grade");
			String city = resultset.getString("city");
			
			 s = new Student(id,name,marks,rollnum,grade,city);
				
			 }
				
	       System.out.println(s);
	    	
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
		
	    if(s !=null){
			req.setAttribute("obj" , s);
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("delete.jsp");
		rd.forward(req, resp);
		
		}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int sid = Integer.parseInt(req.getParameter("id")); 
		
		try {
			Connection connection = JDBCutil.getConnection();
			
			PreparedStatement prepareStatement = connection.prepareStatement("delete from student where id=?");
            prepareStatement.setInt(1, sid);
            
			int row = prepareStatement.executeUpdate();
			if(row > 0) {
				System.out.println("data deleted !!!");
				
			}else {
				System.out.println(" something went wrong data not deleted !!!");
			}
			
			prepareStatement.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("There is problem in deleting object..");
		}
         resp.sendRedirect("list");
	
	}	
}
