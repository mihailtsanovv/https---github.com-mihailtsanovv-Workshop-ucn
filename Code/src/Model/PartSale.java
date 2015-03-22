package Model;


public class PartSale {

	private Sale sale;
	private Product product;
	private int amount;
	private double price;
	private double pricePerPiece;
	
	public PartSale() {
		
	}

	public PartSale( Sale sale, Product product, int amount, double price) {
		this.sale = sale;
		this.product = product;
		pricePerPiece = getProduct().getSalesPrice();
		this.amount = amount;
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Sale getSale() {
		return sale;
	}

	public void setSale(Sale sale) {
		this.sale = sale;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPricePerPiece() {
		return pricePerPiece;
	}

	public void setPricePerPiece(double pricePerPiece) {
		this.pricePerPiece = pricePerPiece;
	}

}
