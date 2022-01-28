package com.example.main;

import com.example.controller.AccountController;
import com.example.controller.ClientController;

import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Javalin app = Javalin.create().start(7001);
		
		app.post("/clients", ClientController.POST_CLIENT);
		
		app.get("/clients", ClientController.GET_CLIENT);
		
		app.get("/clients/:id", ClientController.GET_CLIENT_BY_ID);
		
		app.put("/clients/:id", ClientController.PUT_CLIENT);
		
		app.delete("/clients/:id", ClientController.DELETE_CLIENT);
		
		app.post("/clients/:id/accounts", AccountController.POST_ACCOUNT);
		
		app.get("/clients/:id/accounts", AccountController.GET_ACCOUNT_BY_CLIENT);
		
		app.get("/clients/:id/accounts/:sn", AccountController.GET_ACCOUNT_BY_SN);
		
		app.put("/clients/:id/accounts/:sn", AccountController.PUT_ACCOUNT);
		
		app.delete("/clients/:id/accounts/:sn", AccountController.DELETE_ACCOUNT);
		
		app.get("/clients/:id/accounts1?amountLessThan=:amountLessThan&amountGreaterThan=:amountGreaterThan", AccountController.GET_BALANCE_BETWEEN);
		
	}

}
