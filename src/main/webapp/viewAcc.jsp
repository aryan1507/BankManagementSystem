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
	<% 
		long accNum = Long.parseLong(request.getParameter("AccountNum"));
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
		PreparedStatement ps = con.prepareStatement("select * from account where accountNum=?");
		ps.setLong(1, accNum);
		ResultSet rs = ps.executeQuery();
	%>
	
	<table cellPadding="20px" border="1">
	<tr>
	<th>ID</th>
	<th>AccountNum</th>
	<th>PinCode</th>
	<th>Balance</th>
	<th>Name</th>
	<th>Mobile Number</th>
	<th>Delete</th>
	</tr>
	<% while(rs.next()) {%>
		<tr>
			<td><%= rs.getInt(1) %></td>
			<td><%= rs.getLong(2) %></td>
			<td><%= rs.getInt(3) %></td>
			<td><%= rs.getDouble(4) %></td>
			<td><%= rs.getString(5) %></td>
			<td><%= rs.getLong(6) %></td>
			<td><a href="deleteAcc.jsp?id=<%= rs.getInt(1) %>">Delete</a></td>
		</tr>
	<% } %>
	</table>
</body>
</html>