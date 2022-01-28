package com.example.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.connection.DbConnection;
import com.example.model.Account;
import com.example.model.Client;

public class AccountDao {
		/*
		 * Creates a new account entity in the database with clientId, account and balance
		 */
		public static int insertAccount(int cleintId, String account, double balance) {
			Connection con = DbConnection.getMyConnection();
			PreparedStatement stmt = null;
			try{
				String sql = "insert into account(account, balance, client_id) values(?,?,?)";
				stmt = con.prepareStatement(sql);
				System.out.println("accoutn " + account + " balance " + balance + " sn " + cleintId);
				stmt.setString(1, account);
				stmt.setDouble(2, balance);
				stmt.setInt(3, cleintId);

				boolean status = stmt.execute();
				return 1;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return -1;
		}

		/*
		 * Getting all account by client id.
		 */
//		public static List<Account> getAllAccountsByid(int id) {
//
//			List<Account> accountList = new ArrayList<>();
//			Connection con = DbConnection.getMyConnection();
//			CallableStatement stmt = null;
//			try  {
//
//				String sql = "{call get_account_by_id(?) }";
//				 stmt = con.prepareCall(sql);
//				 stmt.setInt(1, id);
//
//				ResultSet rs = stmt.executeQuery();
//				while (rs.next()) {
//					accountList.add(new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return accountList;
//		}
		
		public static List<Account> getAccountByClient(int clientid) {
			List<Account> accountList = new ArrayList<Account>();
			Connection con = DbConnection.getMyConnection();
			
			PreparedStatement stmt;
			try {
//				String sql = "SELECT * FROM client where client_id=?";
				String sql = "SELECT * FROM account where client_id=?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, clientid);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					accountList.add(new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
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
			return accountList;
		}

		// Get all account with having balance between two param
		public static List<Account> getAllAccountsByidWithParams(int sn, int amountGreaterThan, int amountLessThan) {

			List<Account> accountList = new ArrayList<>();
			Connection con = DbConnection.getMyConnection();
			CallableStatement stmt = null;
			try  {

				String sql = "{call get_account_by_id_with_params(?,?,?) }";
				stmt = con.prepareCall(sql);
				stmt.setInt(1, sn);
				stmt.setInt(2, amountGreaterThan);
				stmt.setInt(3, amountLessThan);

				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					accountList.add(new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return accountList;
		}

		/*
		 * Get single account details from the id
		 */
		public static Account getAccountByClientAndSn(int clientId, int sn) {
			Connection con = DbConnection.getMyConnection();
			PreparedStatement stmt = null;
			try{
				String sql = "SELECT sn, account, balance from account where client_id = ? AND sn = ?";

				stmt = con.prepareStatement(sql);
				stmt.setInt(1, clientId);
				stmt.setInt(2, sn);

				ResultSet rs = stmt.executeQuery();

				if (rs.next() == false) {
					return null;
				}
				return new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}

		/*
		 * Updating the account information from the table account into the database. 
		 */
		public static int updateAccount(int sn, double balance, String account) {
			Connection con = DbConnection.getMyConnection();
			PreparedStatement stmt = null;
			try{
				String sql = "UPDATE account SET account = ?, Balance = ? WHERE sn = ?";
				 stmt = con.prepareStatement(sql);
				 stmt.setString(1, account);
				 stmt.setDouble(2, balance);
				 stmt.setInt(3, sn);

				int changed = stmt.executeUpdate();
				if (changed == 1) {
					return 1; // If updated a record
					
				} else {
					return -1; // if not updated a record
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return -1;
		}
		
		/*
		 * deleting the account information by account id.
		 */
		public static int deleteAccount(int sn , int clientId) {
			Connection con = DbConnection.getMyConnection();
			PreparedStatement stmt = null;
			try {

				String sql = "DELETE FROM account WHERE client_id = ? AND sn = ?";

				 stmt = con.prepareStatement(sql);
				 stmt.setInt(1, clientId);
				 stmt.setInt(2, sn);

				int changed = stmt.executeUpdate();
				
				if (changed == 1) {
					return 1; // if recored deleted 
				} else {
					return -1; // if record not deleted
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return -1;
		}

		public static List<Account> getAccountBetween(int clientid, double amountLessThan, double amountGreaterThan) {
			List<Account> accountList = new ArrayList<Account>();
			Connection con = DbConnection.getMyConnection();
			
			PreparedStatement stmt;
			try {
//				String sql = "SELECT * FROM client where client_id=?";
				String sql = "SELECT * FROM account where client_id = ? and balance < ? and balance > ?";
				System.out.println("clientid :: " + clientid +" amountLessThan " + amountLessThan + " amountGreaterThan " + amountGreaterThan);
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, clientid);
				stmt.setDouble(2, amountLessThan);
				stmt.setDouble(3, amountGreaterThan);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					accountList.add(new Account(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
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
			return accountList;
		}
}



