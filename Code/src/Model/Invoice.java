package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice {

	private int invoiceNo;
	private String saleDate;
	private Sale sale;
	
	public Invoice(){
		
	}

	public Invoice(int invoiceNo, String saleDate, Sale sale) {
		this.invoiceNo = invoiceNo;
		this.saleDate = saleDate;
		this.sale = sale;
	}

	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public String getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(String saleDate) {
		this.saleDate = saleDate;
	}

}
