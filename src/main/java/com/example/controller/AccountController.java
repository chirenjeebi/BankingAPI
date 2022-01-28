package com.example.controller;

import java.util.LinkedList;
import java.util.List;

import com.example.dao.AccountDao;
import com.example.model.Account;

import io.javalin.http.Handler;

public class AccountController {
	
	public static final Handler POST_ACCOUNT = (ctx) -> {	
		int id = Integer.parseInt(ctx.pathParam("id"));
		String account = ctx.formParam("account");
		double balance = Double.parseDouble(ctx.formParam("balance"));
		
		int status = AccountDao.insertAccount(id, account, balance);
		// success message.
		if (status > 0 ) {
			ctx.json("account created suffessfully");
			ctx.status(201);
		} else {
			ctx.json("unable to create the account");
			ctx.status();
		}

	};

//	public static final Handler GET_ACCOUNT = (ctx) -> {
//		boolean hasQueryParams = true;
//		int amountGreaterThan = 0;
//		int amountLessthan = 0;
//		// Get the parameters passed in the context
//		int clientId = Integer.parseInt(ctx.pathParam("client_id"));
//		try {
//			amountGreaterThan = Integer.parseInt(ctx.queryParam("amountGreaterThan"));
//			amountLessthan = Integer.parseInt(ctx.queryParam("amountLessThan"));
//		} catch (NumberFormatException e) {
//			hasQueryParams = false;
//		}
//
//		List<Account> accountList = new LinkedList<>();
//		System.out.println(hasQueryParams);
//		if (hasQueryParams) {
//			accountList = AccountDao.getAllAccountsByidWithParams(clientId, amountGreaterThan, amountLessthan);
//
//		} else {
//			accountList = AccountDao.getAllAccountsByid(clientId);
//		}
//		if (accountList.size() > 0) {
//			ctx.status(200);
//		} else { 
//			ctx.status(404);
//			ctx.result("No accounts found.");
//			return;
//		}
//
//		// Create the string to return.
//		String resultString = "";
//		for (Account account : accountList) {
//			resultString += account.toString();
//			resultString += "\n";
//		}
//		System.out.println(resultString);
//		ctx.result(resultString);
//
//	};
	
	

	public static final Handler GET_ACCOUNT_BY_CLIENT = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		//int sn = Integer.parseInt(ctx.pathParam("sn"));
		
		List<Account> account = AccountDao.getAccountByClient(id);
		if (account != null) {
			ctx.status(200);
			ctx.json(account);
			System.out.println(account.toString());
		} else { 
			ctx.result("No account with that ID exists");
			System.out.println("No account with that ID exist");
			ctx.status(404);
		}
	};
	
	
	public static final Handler GET_BALANCE_BETWEEN = (ctx) -> {
		int id = Integer.parseInt(ctx.pathParam("id"));
		double amountLessThan = Double.parseDouble(ctx.attribute("amountLessThan"));
		double amountGreaterThan = Double.parseDouble(ctx.attribute("amountGreaterThan"));
		
		List<Account> account = AccountDao.getAccountBetween(id, amountLessThan, amountGreaterThan);
		if (account != null) {
			ctx.status(200);
			ctx.json(account);
			System.out.println(account.toString());
		} else { 
			ctx.result("No account with that ID exists");
			System.out.println("No account with that ID exist");
			ctx.status(404);
		}
	};

	
	
	

	public static final Handler GET_ACCOUNT_BY_SN = (ctx) -> {
		int sn = Integer.parseInt(ctx.pathParam("sn"));
		int id = Integer.parseInt(ctx.pathParam("id"));
		
//		List<Account> account = AccountDao.getAccountByClient(id);
		Account account = AccountDao.getAccountByClientAndSn(id, sn);
		if (account != null) {
			ctx.status(200);
			ctx.json(account);
			System.out.println(account.toString());
		} else { 
			ctx.result("No account with that ID exists");
			System.out.println("No account with that ID exist");
			ctx.status(404);
		}
	};

	public static final Handler PUT_ACCOUNT = (ctx) -> {

		int id = Integer.parseInt(ctx.pathParam("id"));
		int sn = Integer.parseInt(ctx.pathParam("sn"));
		String account = ctx.formParam("account");
		double balance = Double.parseDouble(ctx.formParam("balance"));
		
		
		System.out.println("id ::" + id + " sn " + sn);
		// checking the account with the client id.
		Account acc = AccountDao.getAccountByClientAndSn(id, sn);
		System.out.println(acc);
		if (acc != null) {
			int result = AccountDao.updateAccount(sn, balance, account);
			if (result == 1) {
				ctx.status(200);
				ctx.json("account updated");
			} else { 
				ctx.json("account is not updated");
			}

		} else { 
			ctx.json("Account not found with the id");
			ctx.status(404);
		}

	};
	

	public static final Handler DELETE_ACCOUNT = (ctx) -> {

		int id = Integer.parseInt(ctx.pathParam("id"));
		int sn = Integer.parseInt(ctx.pathParam("sn"));
		
		// checking the account with client id and account sn
		
		Account acc = AccountDao.getAccountByClientAndSn(id, sn);
		if (acc != null) {
			int result = AccountDao.deleteAccount(sn, id);
			if (result == 1) {
				ctx.status(200);
				ctx.json("Deleted account" + sn);
			} else {
				ctx.json(" Account is not deleted :" + sn);
			}

		} else { 
			ctx.json("account not found with selected id");
			ctx.status(404);
		}
	};

}
