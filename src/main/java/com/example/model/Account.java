package com.example.model;

public class Account {
	private int sn;
	private String account;
	private double balance;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public Account(int sn, String account, double balance) {
		super();
		this.sn = sn;
		this.account = account;
		this.balance = balance;
	}

	public int getSn() {
		return sn;
	}

	public void setSn(int sn) {
		this.sn = sn;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [sn=" + sn + ", account=" + account + ", balance=" + balance + "]";
	}
}
