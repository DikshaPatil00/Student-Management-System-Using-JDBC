package com.web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import com.util.JDBCutil;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns ="/add")
public class addSTUDENTsevlet extends HttpServlet {


		@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	
		RequestDispatcher rd = req.getRequestDispatcher("addform.jsp");
		rd.forward(req, resp);
	}

	
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
	
		Connection connection = null;
		PreparedStatement prestatement = null;
		
		
		//getting data from Html from(client)
		String name= req.getParameter("sname");
		Double marks= Double.parseDouble(req.getParameter("smarks"));
		int rollnum= Integer.parseInt(req.getParameter("srollnum"));
		String city= req.getParameter("scity");
		String grade=req.getParameter("sgrade") ;
		 
		
		try {
			connection = JDBCutil.getConnection();		
			//Statement statement = connection.createStatement();
		    String query = "insert into student(name,marks,rollnum,grade,city) values (?,?,?,?,?)";
			prestatement = connection.prepareStatement(query);
				
			prestatement.setNString(1,name);   
			prestatement.setDouble(2, marks);   
			prestatement.setInt(3,rollnum);
			prestatement.setString(4,grade);
			prestatement.setNString(5,city);
				
			int row = prestatement.executeUpdate();
			if(row > 0) {
				System.out.println("data inserted !!!");
			}
			else {
				System.out.println("Something Went Wrong data is not inserted !!!");
			}
			
			resp.sendRedirect("list");
			
		} catch (Exception e) {
			System.out.println("Their is problem while add data to database");
			e.printStackTrace();
		}finally {
			if(prestatement != null) {
			   try {
				prestatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		  }
				
	   }
		
   }	
	
}
