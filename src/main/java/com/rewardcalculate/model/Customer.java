package com.rewardcalculate.model;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Customer {
	private Long id;
	private List<Transaction> transactions;
	private int point;
	
	public Customer() {

	}

	public Customer(Long id, List<Transaction> transactions, int point) {
		this.id = id;
		this.transactions = transactions;
		this.point = point;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", transactions=" + transactions + ", point=" + point + "]";
	}
	

}
