package servlet.pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String myemail = req.getParameter("email1");
		String mypass = req.getParameter("password");
		PrintWriter out = res.getWriter();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/authentication", "root", "0800");
			PreparedStatement ps = con.prepareStatement(" select * from register where email=? and password=? ");
			ps.setString(1, myemail);
			ps.setString(2, mypass);
			ResultSet set=ps.executeQuery();
			//only single data so no while loop needed
			if(set.next()) {
				HttpSession session = req.getSession();
				session.setAttribute("name_key", set.getString("name"));
				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.include(req, res);
			}else {
				res.setContentType("text/html");
				out.print("<h3>Login Faild: User info incorrect...");
				RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
				rd.include(req, res);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			res.setContentType("text/html");
			out.print("<h3>Login Faild, Exception: "+e.getMessage()+" ");
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.include(req, res);
		}
	}
}
