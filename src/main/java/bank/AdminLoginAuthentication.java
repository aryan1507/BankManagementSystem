package bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/loginauthentication")
public class AdminLoginAuthentication extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
			PreparedStatement ps = con.prepareStatement("select * from admin where email=? and password=?");
			ps.setString(1,email);
			ps.setString(2,password);
			
			ResultSet rs = ps.executeQuery();
			PrintWriter pout = res.getWriter();
			if(rs.next()) {
				pout.println("<h1>Welcome Admin!</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("AdminLoginNextPage.html");
				rd.include(req,res);		
			}
			else {
				pout.println("<h1>Invalid Credentials!</h1>");
				RequestDispatcher rd = req.getRequestDispatcher("AdminLogin.html");
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
//while(true) {
//switch(c) {
//		case 1:
//			System.out.println("Register User");
//			System.out.print("Enter id: ");
//			int id = sc.nextInt();
//			System.out.print("Enter name: ");
//			String name = sc.next();
//			System.out.print("Enter email: ");
//			String Email = sc.next();
//			System.out.print("Enter password: ");
//			String pswd = sc.next();
//			PreparedStatement ps1 = con.prepareStatement("insert into admin(id,name,email,password) values(?,?,?,?)");
//			ps1.setInt(1, id);
//			ps1.setString(2, name);
//			ps1.setString(3, Email);
//			ps1.setString(4, pswd);
//			ps1.execute();
//			pout.println("User created successfully\n");
//			break;
//			
//		case 2:
//				System.out.print("Select one of the following options:\n 1. Add Account \n 2. View Account Details \n 3. View All Accounts Details \n 4. Exit \n");
//				int ch = sc.nextInt();
//				switch(ch) {
//					case 1:
////						addAccount();
//						break;
//					case 2:
////						viewAccount();
//						break;
//					case 3:
////						viewAllAccounts();
//						break;
//					case 4:
//						return;
//					default:
//						System.out.println("Invalid! Select the option between 1 to 4");
//				}
//				break;
//		default:
//			System.out.println("Invalid! Select the option between 1 and 2");
//}
//}
//}
