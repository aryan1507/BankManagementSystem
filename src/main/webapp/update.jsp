<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% HttpSession hs = request.getSession();
		long accNum = Long.parseLong((String)hs.getAttribute("AccountNum"));
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		PreparedStatement ps = con.prepareStatement("select * from account where accountNum = ?");
		ps.setLong(1, accNum);
		ResultSet rs = ps.executeQuery();
		rs.next();
	%>
	<form action="updateuserinfo" method="post">
		Id: <input type="number" name="ID" value="<%= rs.getInt(1) %>" readonly="true"><br><br>
		Account Number: <input type="number" name="AccountNum" value="<%= accNum %>" readonly="true"><br><br>
		PinCode: <input type="number" name="pincode" value="<%= rs.getInt(3) %>"><br><br>
		Balance: <input type="number" name="bal" value="<%= rs.getDouble(4) %>" readonly="true"><br><br>
		Name: <input type="text" name="name" value="<%= rs.getString(5) %>"><br><br>
		Mobile Number: <input type="number" name="mobileNum" value="<%= rs.getLong(6) %>"><br><br>
		<input type="submit" value="Update">
	</form>
</body>
</html>