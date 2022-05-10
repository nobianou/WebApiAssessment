package com.rewardcalculate.model;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class Transaction {
	private Long id;
	private LocalDate date;
	private List<Item> items;
	public Transaction() {
	
	}
	public Transaction(Long id, LocalDate date, List<Item> items) {
		this.id = id;
		this.date = date;
		this.items = items;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	@Override
	public String toString() {
		return "Transaction [id=" + id + ", date=" + date + ", items=" + items + "]";
	}
	
	
	
	
	
	

}
