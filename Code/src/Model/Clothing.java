package Model;

public class Clothing extends Products {

	private double size;
	private String colour;

	public Clothing(int barcode, String name, double price,
			double purchasePrice, double rentPrice, String country,
			int minAmount, double size, String colour) {
		super(barcode, name, price, purchasePrice, rentPrice, country,
				minAmount);
		this.size = size;
		this.colour = colour;
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

}
