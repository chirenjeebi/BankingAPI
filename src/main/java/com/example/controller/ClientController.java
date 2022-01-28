package com.example.controller;

import java.util.List;

import com.example.dao.ClientDao;
import com.example.model.Account;
import com.example.model.Client;
import com.example.service.ClientService;
import io.javalin.http.Handler;

public class ClientController {
	
	public static final Handler GET_CLIENT = (ctx) -> {
		ClientService service= new ClientService();
		List<Client> clients =  service.showClient();
		ctx.status(200);
		ctx.json(clients);
	};
	public static final Handler GET_CLIENT_BY_ID = (ctx) -> {
		ClientService service= new ClientService();
		Client client = service.getClientById(Integer.parseInt(ctx.pathParam("id")));
		if(client != null) {
			ctx.json(client);
			ctx.status(200);
		} else {
			ctx.status(404);
			ctx.json("id not found");
		}
	};
	
	public static final Handler POST_CLIENT = (ctx) -> {
		ClientService service = new ClientService();
		Client client = ctx.bodyAsClass(Client.class);
		service.createClient(client);
//		ctx.json(client);
		ctx.status(201);
	};
	
	public static final Handler PUT_CLIENT = (ctx) -> {
//		ServiceAccount service= new ServiceAccount();
//		Client client = service.getClientById(Integer.parseInt(ctx.pathParam("id")));

		String id = ctx.pathParam("id");
		String name = ctx.formParam("name");
		String address = ctx.formParam("address");
//		Client result = service.updateClientById(Integer.parseInt(ctx.pathParam("id"), name, address));
		int result = ClientDao.updateClient(new Client(Integer.parseInt(ctx.pathParam("id")), name, address));
		if (result == 1) {
			ctx.result("Updated :" + id + " " + name +" "+ address);
			ctx.json(result);
			ctx.status(201);
		} else { 
			ctx.status(404);
			ctx.json(result);
		}
	};
	
	public static final Handler DELETE_CLIENT = (ctx) -> {
		ClientService service= new ClientService();
		Client id = service.getClientById(Integer.parseInt(ctx.pathParam("id")));
		if(id != null) {
			 service.deleteClientById(Integer.parseInt(ctx.pathParam("id")));
		}else {
			ctx.status(404);
			ctx.json("id not found");
		}
	};
}
