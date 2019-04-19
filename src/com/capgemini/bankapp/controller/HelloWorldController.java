package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="helloworld", loadOnStartup= 1, urlPatterns= {"/hello","/helloworld","/login"})
public class HelloWorldController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public HelloWorldController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		System.out.println("Hello World");
		
		PrintWriter out = response.getWriter();
		out.println("Hello World");
		out.println();
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		resp.setContentType("text/html");
		RequestDispatcher dispatcher=null;
		//PrintWriter out = resp.getWriter();
		
		if(username.equals("root") && password.equals("root@123"))
		{
			//out.println("<h2>Login Sucessful.welcome" + username +"</h2>");
			 dispatcher=req.getRequestDispatcher("sucess.html");
		}
		else
		{
		dispatcher=req.getRequestDispatcher("error.html");
		}
		dispatcher.forward(req, resp);
	}
}
