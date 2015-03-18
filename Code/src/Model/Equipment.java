package Model;

public class Equipment extends ProductInh {

	private String type;
	private String description;

	public Equipment(int barcode, String name, double price,
			double purchasePrice, double rentPrice, String country,
			int minAmount, String type, String description){
		super(barcode, name, price, purchasePrice, rentPrice, country,
				minAmount);
	this.type = type;
	this.description = description;
	}

	public String getType(){
	    return type;
	}

	public void setType(String type){
	    this.type = type;
	}

	public String getDescription(){
	    return description;
	}

	public void setDescription(String description){
	    this.description = description;
	}
}