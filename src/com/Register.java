package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/HTML");
		PrintWriter pw = response.getWriter();
		
		String user = request.getParameter("user");
		String psw = request.getParameter("psw");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			
			if((user!=null) && (psw!=null))
			{
				PreparedStatement pstmt = con.prepareStatement("select * from account where username=?");
				pstmt.setString(1, user);
				ResultSet rs = pstmt.executeQuery();
				if(rs.getString(1).equals(user))
				{
					pw.println("User already exists");
				}
			}
			else {
				PreparedStatement pstmt1 = con.prepareStatement("insert into account values(?,?)");
				pstmt1.setString(1, user);
				pstmt1.setString(2, psw);
				
				int i=pstmt1.executeUpdate();  
				System.out.println(i+" records inserted");  
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
