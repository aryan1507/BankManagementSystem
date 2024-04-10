package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AccountValidation")
public class UserValidation extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accNum = req.getParameter("AccountNum");
		String pincode = req.getParameter("pincode");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			PreparedStatement ps = con.prepareStatement("select * from account where AccountNum=? and pincode=?");
			ps.setString(1,accNum);
			ps.setString(2,pincode);
			
			ResultSet rs = ps.executeQuery();
			PrintWriter pout = res.getWriter();
			if(rs.next()) {
//				Cookie c = new Cookie("AccountNum", accNum);
//				res.addCookie(c);
				HttpSession hs = req.getSession(); 
				hs.setAttribute("AccountNum", accNum);
				pout.println("<h1>Welcome!</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("UserOptions.html");
				rd.include(req,res);		
			}
			else {
				pout.println("<h1>Invalid Credentials!</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("UserLogin.html");
				rd.include(req,res);				
			}
		}
		catch(SQLException e) {		
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
