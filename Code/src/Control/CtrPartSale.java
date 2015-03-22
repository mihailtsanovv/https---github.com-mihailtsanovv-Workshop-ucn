package Control;

import Model.*;
import DB.*;

import java.util.ArrayList;

public class CtrPartSale {
	
	CtrProduct pc = new CtrProduct();
	CtrSale sc = new CtrSale();

	public CtrPartSale() {

	}

	public ArrayList<PartSale> findAllPartSalesBySaleId(int saleId) {
		IFDBPartSale dbPartSale = new DBPartSale();
		ArrayList<PartSale> allPartSale = new ArrayList<PartSale>();
		allPartSale = dbPartSale.getAllPartSaleBySaleId(saleId, false);
		return allPartSale;
	}

	public void insertPartSale(int saleId, int productBarcode, String productName, double pricePerPiece, int amount, double price) throws Exception {
		PartSale partObj = new PartSale();
		partObj.setSale(sc.findById(saleId));
		partObj.setProduct(pc.findByBarcode(productBarcode));
		partObj.setProduct(pc.findByName(productName));
		partObj.setPricePerPiece(pc.findByBarcode(productBarcode).getSalesPrice());
		partObj.setAmount(amount);
		partObj.setPrice(price);
		
		

		try {
			DBConnection.startTransaction();
			DBPartSale dbPartSale = new DBPartSale();
			dbPartSale.insertPartSale(partObj);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("PartSale not inserted");
		}
	}
	
	public void deletePartSale(int saleId) throws Exception {

		try {
			DBConnection.startTransaction();
			DBPartSale dbPartSale = new DBPartSale();
			dbPartSale.deletePartSale(saleId);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("PartSale not deleted");
		}
	}
}
