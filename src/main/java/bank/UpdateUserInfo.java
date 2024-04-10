package bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/updateuserinfo")
public class UpdateUserInfo extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPut(req, resp);
	}
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long accNum = Long.parseLong(req.getParameter("AccountNum"));
		int pincode = Integer.parseInt(req.getParameter("pincode"));
		String name = req.getParameter("name");
		long mobileNum = Long.parseLong(req.getParameter("mobileNum"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			PreparedStatement ps = con.prepareStatement("update account set pincode=?, name=?, mobileNum=? where accountNum = ?");
			ps.setInt(1, pincode);
			ps.setString(2, name);
			ps.setLong(3, mobileNum);
			ps.setLong(4, accNum);
			ps.execute();
			resp.getWriter().println("<h1>User Information Updated Successfully</h1>");
			RequestDispatcher rd = req.getRequestDispatcher("UserOptions.html");
			rd.include(req,resp);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
