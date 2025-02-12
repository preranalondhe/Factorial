package com.kdn;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class factservlet
 */
@WebServlet("/factservlet")
public class factservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public factservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		out.println("<html><head><title>servlet factorial</title></head>");
		String n1=request.getParameter("num");
		int a1=Integer.parseInt(n1);
		int fact=1;
		for(int i=1;i<=a1;i++)
		{
			fact=fact*i;
		}
		out.println("<p>Factorial is:"+fact+"</p>");
		out.println("</html>");
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","pass");
			PreparedStatement stmt=con.prepareStatement("insert into factorial1 values(?,?)");
			stmt.setInt(1, a1);
			stmt.setInt(2, fact);
			int i=stmt.executeUpdate();
			System.out.println(i+"data added");
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	}


