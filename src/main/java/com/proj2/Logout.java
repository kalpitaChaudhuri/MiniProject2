package com.proj2;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class Logout extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	
	Cookie[] cookies = req.getCookies();
	if(cookies.length>1) {
		Cookie c=cookies[1];
		c.setMaxAge(0);
		resp.addCookie(c);
		out.println("<h2>Logged out Successfully</h2>");
		req.getRequestDispatcher("Login.html").include(req, resp);
	}
	else {
		out.println("<h2>Please Login First</h2> ");
		req.getRequestDispatcher("Login.html").include(req, resp);
	}
}
}
