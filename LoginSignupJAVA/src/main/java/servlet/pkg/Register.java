package servlet.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/regForm")
public class Register extends HttpServlet{
	private static final long serialVersionUID = 1L;
   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
   {
	   PrintWriter out = res.getWriter();
	   //get values from JSP form
	   String myname=req.getParameter("name1");
	   String myemail=req.getParameter("email1");
	   String mypass=req.getParameter("password1");
	   String mygender=req.getParameter("gender1");
	   String mycity=req.getParameter("city1");
	   
	   //create DB connection
	   try {
		   Class.forName("com.mysql.cj.jdbc.Driver");
           Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication", "root", "0800");
           String q="INSERT INTO REGISTER VALUES(?,?,?,?,?)";
           PreparedStatement ps = con.prepareStatement(q);
		   ps.setString(1, myname);
		   ps.setString(2, myemail);
		   ps.setString(3, mypass);
		   ps.setString(4, mygender);
		   ps.setString(5, mycity);
		   
		   int count = ps.executeUpdate();
		   if(count>0) {
			   res.setContentType("text/html");
			   out.print("<h3 style='color:green'>User Register Success...</h3>");
			   RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			   rd.include(req, res);
		   }else {
			   res.setContentType("text/html");
			   out.print("<h3 style='color:red'>User Registration Failed...</h3>");
			   RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
			   rd.include(req, res);
		   }
	   }
	   catch(Exception e) {
		   e.printStackTrace();
		   res.setContentType("text/html");
		   out.print("<h3 style='color:red'> User Registration Failed... Exception:"+e.getMessage()+" </h3>");
		   RequestDispatcher rd = req.getRequestDispatcher("/register.jsp");
		   rd.include (req, res);
	   }
   }
}
