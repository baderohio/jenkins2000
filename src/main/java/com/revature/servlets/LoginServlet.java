package com.revature.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.revature.beans.User;
import com.revature.services.UserService;

public class LoginServlet extends HttpServlet {
	private static Logger log = Logger.getLogger(LoginServlet.class);
	private static UserService us = new UserService();

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// We want to use this get to the actual login page.
		log.trace("Login servlet received a get request");
		
		// If a loginservlet receives a get request, we know
		// we are trying to log in. So, we should send them
		// to the login page.
		
		// What if we are already logged in?
		if(req.getSession().getAttribute("user") != null) {
			resp.sendRedirect("home");
		} else {
			// not logged in, go to login page.
			RequestDispatcher rd  = req.getRequestDispatcher("login.html");
			rd.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// We will use THIS to log in.
		log.trace("Login servlet received a post request");
		
		// req represents the httpRequest sent by the client
		// the request holds any information from our form
		String username = req.getParameter("user");
		String password = req.getParameter("pass");
		log.trace("Logging in with user/pass: "+username+"/"+password);
		User u = us.login(username, password);
		log.trace(u);
		
		// The Session: An object that persists between requests
		// from the same client (identified by an id)
		if(u == null) {
			resp.sendRedirect("login");
		} else {
			HttpSession session = req.getSession();
			// Note: the session comes from the request object
			session.setAttribute("user", u);
			resp.sendRedirect("home");
		}
	}
	
}
