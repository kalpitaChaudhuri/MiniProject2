package com.proj2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/profile")
public class Profile extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=null;
		Cookie[] cookies = req.getCookies();
		if(cookies!=null) {
			for(Cookie c: cookies) {
				if("email".equals(c.getName())) {
					email=c.getValue();
					break;
				}
			}
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/userdb", "root", "mysql");
				PreparedStatement ps= connection.prepareStatement("select * from user2 where email = ?");
				ps.setString(1, email);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					resp.setContentType("text/html");
					resp.getWriter().println("<h1>Profile Page</h1>");
					resp.getWriter().println("<br>Name: " + rs.getString("name") + "<br>");
					resp.getWriter().println("<br>Email: " + rs.getString("email") + "<br>");
					resp.getWriter().println("<br>Age: " + rs.getInt("age") + "<br>");
					resp.getWriter().println("<br>Address: " + rs.getString("address") + "<br>");
					resp.getWriter().println("<br>Mobile: " + rs.getLong("mobile") + "<br>");
				}
				connection.close();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
