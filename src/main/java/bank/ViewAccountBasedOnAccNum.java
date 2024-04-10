package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewAcc")
public class ViewAccountBasedOnAccNum extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String accNum = req.getParameter("AccountNum");
		try {
			if (accNum != null && !accNum.isEmpty() && accNum.matches("//d+")) {
				long AccountNum = Long.parseLong(accNum);
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
				PreparedStatement ps = con.prepareStatement("select * from account where accountNum=?");
				ps.setLong(1,AccountNum);
				ResultSet rs = ps.executeQuery();
				
				PrintWriter pout = res.getWriter();
				pout.print("ID\t");
				pout.print("AccountNum\t");
				pout.print("PinCode\t");
				pout.print("Balance\t");
				pout.print("Name\t");
				pout.print("Mobile Number\t");
				pout.println();
				
				if(rs.next()) {
					pout.print(rs.getInt(1) + "\t");
					pout.print(rs.getLong(2) +"\t");
					pout.print(rs.getInt(3) + "\t");
					pout.print(rs.getDouble(4) + "\t");
					pout.print(rs.getString(5) + "\t");
					pout.print(rs.getLong(6) + "\t");
				}
				else {
					PrintWriter pout1 = res.getWriter();
					pout1.print("Account Not Found");
				}
			}
			else {
				PrintWriter pout1 = res.getWriter();
				pout1.print("Invalid Account Number");
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
