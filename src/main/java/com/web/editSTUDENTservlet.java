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

@WebServlet(urlPatterns = "/edit")
public class editSTUDENTservlet extends HttpServlet {

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
	
	RequestDispatcher rd = req.getRequestDispatcher("editstudent.jsp");
	rd.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int sid= Integer.parseInt(req.getParameter("id"));
		String name= req.getParameter("sname");
		Double marks= Double.parseDouble(req.getParameter("smarks"));
		int rollnum= Integer.parseInt(req.getParameter("srollnum"));
		String grade=req.getParameter("sgrade"); 
		String city= req.getParameter("scity");
		
		
		System.out.println(name);
		System.out.println(marks);
		System.out.println(rollnum);
		System.out.println(grade);
		System.out.println(city);
		
		try {
			Connection connection=JDBCutil.getConnection();
			String quary = " update student set name=? , marks=? , rollnum=? , grade=? , city=? where id=?";
			PreparedStatement preparedstatement = connection.prepareStatement(quary);
			preparedstatement.setString(1,name);
			preparedstatement.setDouble(2,marks);
			preparedstatement.setInt(3,rollnum);
			preparedstatement.setString(4,grade);
			preparedstatement.setString(5,city);
			preparedstatement.setInt(6,sid);
			
			int row = preparedstatement.executeUpdate();
			if(row > 0) {
				System.out.println("data updated !!!");
			}
			else {
				System.out.println("Something Went Wrong data not updated !!!");
			}
			//closing resources
			preparedstatement.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		resp.sendRedirect("list");
	}

}
