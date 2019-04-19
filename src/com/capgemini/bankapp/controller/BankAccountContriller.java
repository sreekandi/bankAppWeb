package com.capgemini.bankapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.capgemini.bankapp.exception.BankAccountNotFoundException;
import com.capgemini.bankapp.exception.LowBalanceException;
import com.capgemini.bankapp.model.BankAccount;
import com.capgemini.bankapp.service.BankAccountService;
import com.capgemini.bankapp.service.impl.BankAccountServiceImpl;

@WebServlet(urlPatterns = {"*.do"}, loadOnStartup = 1)
public class BankAccountContriller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BankAccountService bankService;

	public BankAccountContriller() {
		bankService = new BankAccountServiceImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		String path = request.getServletPath();
		
		if(path.equals("/displaydetails.do"))
		{
			List<BankAccount> bankAccount = bankService.findAllBankAccounts();
			RequestDispatcher dispatcher = request.getRequestDispatcher("displaydetails.jsp");
			
			request.setAttribute("accounts", bankAccount );
			dispatcher.forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String path = request.getServletPath();
		System.out.println(path);

		if (path.equals("/addNewBankaccount.do")) {
			String accountHolderName = request.getParameter("accountholdername");
			String accountType = request.getParameter("accounttype");
			double accountBalance = Double.parseDouble(request.getParameter("accountbalance"));

			BankAccount account = new BankAccount(accountHolderName, accountType, accountBalance);
			if (bankService.addNewAccount(account)) {
				out.println("<h2>BankAccount is Created Sucessfull..");
				out.println("<h2><a href = Index.html>Home</a><h2>");
				out.close();
			}
			
		}
			if(path.equals("/withdrawamount.do"))
			{
				long accountId = Long.parseLong(request.getParameter("Accountnumber"));
				double accountBalance1 = Double.parseDouble(request.getParameter("amount"));
				
				try
				{
					bankService.withdraw(accountId, accountBalance1);
					out.println("<h2>Amount Withraw Sucessfull..");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}catch(LowBalanceException e)
				{
					out.println("<h2>Amount Withraw incomplete");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
				catch(BankAccountNotFoundException e)
				{
					out.println("<h2>Account Not Found");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
			}

			if(path.equals("/deposit.do"))
			{
				long accountId = Long.parseLong(request.getParameter("Accountnumber"));
				double accountBalance1 = Double.parseDouble(request.getParameter("amount"));
				
				try
				{
					bankService.deposit(accountId, accountBalance1);
					out.println("<h2>Amount deposit Sucessfull..");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
				catch(BankAccountNotFoundException e)
				{
					out.println("<h2>Account Not Found");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
			}
			if(path.equals("/delete.do"))
			{
				long accountId = Long.parseLong(request.getParameter("Accountnumber"));
			
				try
				{
					bankService.deleteBankAccocunt(accountId);
					out.println("<h2>Amount delete Sucessfull..");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
				catch(BankAccountNotFoundException e)
				{
					out.println("<h2>Account Not Found");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
			}
			if(path.equals("/fundtransfer.do"))
			{
				long fromAccountId = Long.parseLong(request.getParameter("fromAccount"));
				long toAccountId = Long.parseLong(request.getParameter("toAccount"));
				double accountBalance1 = Double.parseDouble(request.getParameter("amount"));
				
				try
				{
					bankService.fundTransfer(fromAccountId, toAccountId, accountBalance1);
					out.println("<h2>Amount amount transfer Sucessfull..");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}catch(LowBalanceException e)
				{
					out.println("<h2>insufficient fund");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
				catch(BankAccountNotFoundException e)
				{
					out.println("<h2>Account Not Found");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
			}
			if(path.equals("/Search.do"))
			{
				long accountId = Long.parseLong(request.getParameter("Accountno"));
				
				
				try
				{
					BankAccount bankAccount = bankService.searchBankAccount(accountId);
					RequestDispatcher dispatcher = request.getRequestDispatcher("Search.jsp");
					
					request.setAttribute("account", bankAccount );
					dispatcher.forward(request, response);
				}
				catch(BankAccountNotFoundException e)
				{
					out.println("<h2>Account Not Found");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
			}
			
			if(path.equals("/fetchdetails.do"))
			{
				long accountId = Long.parseLong(request.getParameter("Accountno"));
				
				
				try
				{
					BankAccount bankAccount = bankService.searchBankAccount(accountId);
					RequestDispatcher dispatcher = request.getRequestDispatcher("UpDateDetails.jsp");
					
					request.setAttribute("account", bankAccount );
					dispatcher.forward(request, response);
				}
				catch(BankAccountNotFoundException e)
				{
					out.println("<h2>Account Not Found");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
			}
			if (path.equals("/UpDateDetails.do")) {
				String accountHolderName = request.getParameter("AccountHolderName");
				String accountType = request.getParameter("AccountType");
				long accountId = Long.parseLong(request.getParameter("Accountno"));
				 
				boolean result = bankService.updateBankAccount(accountId, accountHolderName, accountType);
				if(result)
				{
					out.print("<h2>updated account details");
					out.println("<h2><a href = Index.html>Home</a><h2>");
					out.close();
				}
					else
					{
						out.println("<h2>Account Not Found");
						out.println("<h2><a href = Index.html>Home</a><h2>");
						out.close();
					}
					
				
				
			}
			
	}	

}
