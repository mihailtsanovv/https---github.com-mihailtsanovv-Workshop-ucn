package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice {

	private int number;
	private String paymentDate;
	private int amount;
	
	public Invoice(){
		
	}

	public Invoice(int number, String paymentDate, int amount) {
		this.number = number;
		this.paymentDate = getPaymentDate();
		this.amount = amount;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getPaymentDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String dateString = dateFormat.format(date);
		return dateString;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
