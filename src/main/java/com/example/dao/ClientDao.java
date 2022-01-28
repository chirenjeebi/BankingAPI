package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.connection.DbConnection;
import com.example.model.Client;


public class ClientDao {
	public boolean saveClient(Client client) {
		Connection con = DbConnection.getMyConnection();
		boolean success = false;
		PreparedStatement prepStatement;
		try {
			prepStatement = con.prepareStatement("insert into client(name, address) values(?,?)");
			prepStatement.setString(1, client.getName());
			prepStatement.setString(2, client.getAddress());
			success =  prepStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}
	
	public List<Client> getClient() {
		List<Client> clientList = new ArrayList<Client>();
		Connection con = DbConnection.getMyConnection();
		PreparedStatement stmt;
		try {
//			String sql = "SELECT * FROM client where client_id=?";
			String sql = "SELECT * FROM client";
			stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				clientList.add(new Client(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return clientList;
	}
	
	public Client getClientById(int client_id) {
		Client client = null;
		Connection con = DbConnection.getMyConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "SELECT * FROM client where client_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, client_id);

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return client;
	}
	
	// update name and address on database for specific client id
	public static int updateClient(Client updateClient) {
		Connection con = DbConnection.getMyConnection();
		PreparedStatement stmt = null;
		try{
			String sql = "UPDATE client SET name = ?, address = ? WHERE client_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, updateClient.getName());
			stmt.setString(2, updateClient.getAddress());
			stmt.setInt(3, updateClient.getId());

			int changed = stmt.executeUpdate();
			// if it record is updated 
			if (changed == 1) {
				return 1;
				
			} else {
				return -1; // if record is not updated 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return -1;
	}
	
	public Client deleteById(int client_id) {
		Client client = null;
		Connection con = DbConnection.getMyConnection();
		PreparedStatement stmt = null;
		try {
			String sql = "DELETE FROM client where client_id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, client_id);

			ResultSet rs = stmt.executeQuery();
			int result = stmt.executeUpdate();
			while(rs.next()) {
				client = new Client(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return client;
	}
}

