package Model;

public class Products {
	private int barcode;
	private String name;
	private double price;
	private double purchasePrice;
	private double rentPrice;
	private String country;
	private int minAmount;

	public Products(int barcode, String name, double price,
			double purchasePrice, double rentPrice, String country,
			int minAmount) {
		this.barcode = barcode;
		this.name = name;
		this.price = price;
		this.purchasePrice = purchasePrice;
		this.rentPrice = rentPrice;
		this.country = country;
		this.minAmount = minAmount;
	}

	public int getBarcode() {
		return barcode;
	}

	public void setBarcode(int barcode) {
		this.barcode = barcode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}

}
