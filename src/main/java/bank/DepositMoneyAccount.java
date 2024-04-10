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

@WebServlet("/depositm")
public class DepositMoneyAccount extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPut(req,resp);
	}
	
	public void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double amt = Double.parseDouble(req.getParameter("damt"));
//		Cookie[] arr = req.getCookies();
//		Cookie c = arr[0];
//		long accNum = Long.parseLong(c.getValue());
		HttpSession hs = req.getSession();
		long accNum = Long.parseLong((String) hs.getAttribute("AccountNum"));
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "root");
			PreparedStatement ps = con.prepareStatement("select bal from account where accountNum = ?");
			ps.setLong(1, accNum);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
					amt += rs.getDouble(1);
					PreparedStatement ps1 = con.prepareStatement("update account set bal = ? where accountNum = ?");
					ps1.setDouble(1, amt);
					ps1.setLong(2, accNum);
					ps1.execute();
					resp.getWriter().println("<h1>Money Deposited Successfully</h1>");
					RequestDispatcher rd = req.getRequestDispatcher("UserOptions.html");
					rd.include(req,resp);
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
