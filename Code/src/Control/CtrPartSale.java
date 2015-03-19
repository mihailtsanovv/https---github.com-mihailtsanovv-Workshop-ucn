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

	public void insertPartSale(int saleId, int productBarcode, int amount) throws Exception {
		PartSale partObj = new PartSale();
		partObj.setAmount(amount);
		partObj.setProduct(pc.findByBarcode(productBarcode));
		partObj.setSale(sc.findById(saleId));

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
}
