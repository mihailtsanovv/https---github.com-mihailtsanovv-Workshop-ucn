package Control;

import Model.*;
import DB.*;

import java.util.ArrayList;

public class CtrSale {

	/** Creates a new instance of CtrSale */
	public CtrSale() {

	}

	public ArrayList<Sale> findAllSales() {
		IFDBSale dbSale = new DBSale();
		ArrayList<Sale> allSale = new ArrayList<Sale>();
		allSale = dbSale.getAllSale(false);
		return allSale;
	}

	public Sale findByBarcode(int id) {
		IFDBSale dbSale = new DBSale();
		return dbSale.searchSaleId(id, true);
	}

	public int updateSale(boolean deliveryStatus) {

		IFDBSale dbSale = new DBSale();
		Sale saleObj = new Sale();

		saleObj.setDeliveryStatus(deliveryStatus);
		return dbSale.updateSale(saleObj);

	}

	public void insertSale(String deliveryDate) throws Exception {
		Sale saleObj = new Sale();
		saleObj.setDeliveryDate(deliveryDate);

		try {
			DBConnection.startTransaction();
			DBSale dbSale = new DBSale();
			dbSale.insertSale(saleObj);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Sale not inserted");
		}
	}
	
	public void deleteSale(int id) throws Exception {

		try {
			DBConnection.startTransaction();
			DBSale dbSale = new DBSale();
			dbSale.deleteSale(id);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Sale not deleted");
		}
	}
}
