package Control;

import Model.*;
import DB.*;

import java.util.ArrayList;

public class CtrSale {

	CtrCustomer cc = new CtrCustomer();

	/** Creates a new instance of CtrSale */
	public CtrSale() {

	}

	public ArrayList<Sale> findAllSales() {
		IFDBSale dbSale = new DBSale();
		ArrayList<Sale> allSale = new ArrayList<Sale>();
		allSale = dbSale.getAllSale(false);
		return allSale;
	}

	public Sale findById(int id) {
		IFDBSale dbSale = new DBSale();
		return dbSale.searchSaleId(id, true);
	}

	public int insertSale(int customerId, double totalPrice) throws Exception {
		Sale saleObj = new Sale();
		saleObj.setCustomer(cc.findById(customerId));
		saleObj.setTotalPrice(totalPrice);

		try {
			DBConnection.startTransaction();
			DBSale dbSale = new DBSale();
			int id = dbSale.insertSale(saleObj);
			DBConnection.commitTransaction();
			return id;
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
