package Model;

import java.text.*;
import java.util.*;

public class Sale {

	private int id;
	private String date;
	private String deliveryDate;
	private boolean deliveryStatus;
	private Customer customer;
	private Invoice invoice;
	private ArrayList<PartSale> partSale;
	private double totalPrice;

	public Sale() {

	}

	public Sale(int id) {
		this.id = id;
		date = getDate();
		partSale = new ArrayList<PartSale>();
		setCustomer(new Customer());
		setInvoice(new Invoice());
		totalPrice = getTotalPrice();
	}

	public void addPartSale(PartSale partS) {
		partSale.add(partS);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		return dateString;
	}
	
	public void setDate(String date) {
		this.date = date;
	}

	public boolean isDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(boolean deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
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

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
