package Model;

public class GunReplicas extends Products {

	private String fabric;
	private String calibre;

	public GunReplicas(int barcode, String name, double price,
			double purchasePrice, double rentPrice, String country,
			int minAmount, String fabric, String calibre) {
		super(barcode, name, price, purchasePrice, rentPrice, country,
				minAmount);
		this.fabric = fabric;
		this.calibre = calibre;
	}

	public String getFabric() {
		return fabric;
	}

	public void setFabric(String fabric) {
		this.fabric = fabric;
	}

	public String getCalibre() {
		return calibre;
	}

	public void setCalibre(String calibre) {
		this.calibre = calibre;
	}

}
