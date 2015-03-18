package Model;

import java.text.*;
import java.util.*;

public class Sales {

	private int id;
	private String date;
	private Customer customers;
	private Products products;
	private double totalPrice;
	private HashMap<Integer, Integer> partSale;

	public Sales(int id) {
		this.id = id;
		date = getDate();
		partSale = new HashMap<Integer,Integer>();
	}

	public void addProducts(int barcode, int quantity){
		
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

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public HashMap<Integer, Integer> getProducts() {
		return partSale;
	}
}
