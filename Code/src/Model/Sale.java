package Model;

import java.text.*;
import java.util.*;

public class Sale {

	private int id;
	private String date;
	private Customer customer;
	private double totalPrice;

	public Sale() {

	}

	public Sale(int id, Customer customer) {
		this.id = id;
		date = getDate();
		this.customer = customer;
		totalPrice = getTotalPrice();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		return dateString;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
