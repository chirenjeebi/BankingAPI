package com.example.service;

import java.util.List;

import com.example.dao.AccountDao;
import com.example.dao.ClientDao;
import com.example.model.Account;
import com.example.model.Client;

// it will create the new client to the client table. ( will create the new client)
public class ClientService {
	public void createClient(Client client) {
		ClientDao dao = new ClientDao();
		dao.saveClient(client);
	}
	
//	public Account createClientById(int clientId) {
//		AccountDao dao = new AccountDao();
//		dao.(clientId);
//	}
	
	// it will return the all the list of client from the client table.
	public List<Client> showClient() {
		ClientDao dao = new ClientDao();
		System.out.println(dao.getClient());
		return dao.getClient();
	}
	
	// it will get the client by client id from the client table.
	public Client  getClientById(int clientId) {
		ClientDao dao = new ClientDao();
		return dao.getClientById(clientId);
	}
	
//	public void updateClientById(Client clientId) {
//		ClientDao dao = new ClientDao();
//		  dao.updateClient(clientId);
//	}
	
	// it will delete the row elected by client id from the client table.
	public Client  deleteClientById(int clientId) {
	ClientDao dao = new ClientDao();
	return dao.deleteById(clientId);
}

}
