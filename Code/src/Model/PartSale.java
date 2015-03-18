package Model;


public class PartSale {

	private Sale sale;
	private Product product;
	private int amount;

	public PartSale() {
		setSale(new Sale());
		setProduct(new Product());
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

}
