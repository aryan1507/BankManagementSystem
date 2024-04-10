package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addAcc")
public class AddAccount extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Random r = new Random();
		long accountNum = (long)(r.nextDouble()*1000000000000L);
		String id = req.getParameter("id");
		String pincode = req.getParameter("pincode");
		String bal = req.getParameter("bal");
		String name = req.getParameter("name");
		String mobileNum = req.getParameter("mobileNum");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			PreparedStatement ps = con.prepareStatement("insert into account(id,accountNum,pincode,bal,name,mobileNum) values(?,?,?,?,?,?)");
			ps.setInt(1,Integer.parseInt(id));
			ps.setLong(2,accountNum);
			ps.setInt(3,Integer.parseInt(pincode));
			ps.setDouble(4,Double.parseDouble(bal));
			ps.setString(5,name);
			ps.setLong(6,Long.parseLong(mobileNum));
			
			ps.execute();
			PrintWriter pout = res.getWriter();
			pout.println("Account Registered Successfully!");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
