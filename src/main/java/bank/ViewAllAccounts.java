package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewAllAcc")
public class ViewAllAccounts extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
				PreparedStatement ps = con.prepareStatement("select * from account");
				ResultSet rs = ps.executeQuery();
				
				PrintWriter pout = res.getWriter();
				pout.print("ID\t");
				pout.print("AccountNum\t");
				pout.print("PinCode\t");
				pout.print("Balance\t");
				pout.print("Name\t");
				pout.print("Mobile Number\t");
				pout.println();
				pout.println();
				while(rs.next()) {
					pout.print(rs.getInt(1) + "\t");
					pout.print(rs.getLong(2) +"\t");
					pout.print(rs.getInt(3) + "\t");
					pout.print(rs.getDouble(4) + "\t");
					pout.print(rs.getString(5) + "\t");
					pout.print(rs.getLong(6) + "\t");
					pout.println();
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
