package Control;

import Model.*;
import DB.*;

import java.util.ArrayList;

public class CtrProduct {

	/** Creates a new instance of CtrProduct */
	public CtrProduct() {

	}

	public ArrayList<Product> findAllProducts() {
		IFDBProduct dbPro = new DBProduct();
		ArrayList<Product> allPro = new ArrayList<Product>();
		allPro = dbPro.getAllProduct(false);
		return allPro;
	}

	public Product findByName(String name) {
		IFDBProduct dbPro = new DBProduct();
		return dbPro.searchProductName(name, true);
	}

	public Product findByBarcode(int barcode) {
		IFDBProduct dbPro = new DBProduct();
		return dbPro.searchProductBarcode(barcode, true);
	}

	public int updatePro(int barcode, String name, double purchasePrice,
			double salesPrice, double rentPrice, String countryOfOrigin,
			int minStock, String size, String colour, String type,
			String description, String fabric, double calibre) {

		IFDBProduct dbPro = new DBProduct();
		Product proObj = new Product();

		proObj.setBarcode(barcode);
		proObj.setName(name);
		proObj.setPurchasePrice(purchasePrice);
		proObj.setSalesPrice(salesPrice);
		proObj.setRentPrice(rentPrice);
		proObj.setCountryOfOrigin(countryOfOrigin);
		proObj.setMinStock(minStock);
		proObj.setSize(size);
		proObj.setColour(colour);
		proObj.setType(type);
		proObj.setDescription(description);
		proObj.setFabric(fabric);
		proObj.setCalibre(calibre);
		return dbPro.updateProduct(proObj);

	}

	public void insertPro(int barcode, String name, double purchasePrice,
			double salesPrice, double rentPrice, String countryOfOrigin,
			int minStock, String size, String colour, String type,
			String description, String fabric, double calibre) throws Exception {
		Product proObj = new Product();
		proObj.setBarcode(barcode);
		proObj.setName(name);
		proObj.setPurchasePrice(purchasePrice);
		proObj.setSalesPrice(salesPrice);
		proObj.setRentPrice(rentPrice);
		proObj.setCountryOfOrigin(countryOfOrigin);
		proObj.setMinStock(minStock);
		proObj.setSize(size);
		proObj.setColour(colour);
		proObj.setType(type);
		proObj.setDescription(description);
		proObj.setFabric(fabric);
		proObj.setCalibre(calibre);

		try {
			DBConnection.startTransaction();
			DBProduct dbPro = new DBProduct();
			dbPro.insertProduct(proObj);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Product not inserted");
		}
	}
	
	public void deletePro(int barcode) throws Exception {

		try {
			DBConnection.startTransaction();
			DBProduct dbPro = new DBProduct();
			dbPro.deleteProduct(barcode);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Product not deleted");
		}
	}

}
