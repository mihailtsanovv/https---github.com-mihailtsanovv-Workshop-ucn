package Control;

import Model.*;
import DB.*;

import java.util.ArrayList;

public class CtrPartSale {

	public CtrPartSale() {

	}

	public ArrayList<PartSale> findAllPartSales() {
		IFDBPartSale dbPartSale = new DBPartSale();
		ArrayList<PartSale> allPartSale = new ArrayList<PartSale>();
		allPartSale = dbPartSale.getAllPartSale(false);
		return allPartSale;
	}

	public PartSale findById(int id) {
		IFDBPartSale dbPartSale = new DBPartSale();
		return dbPartSale.searchPartSaleId(id, true);
	}

	public void insertPartSale(int amount) throws Exception {
		PartSale partObj = new PartSale();
		partObj.setAmount(amount);

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
