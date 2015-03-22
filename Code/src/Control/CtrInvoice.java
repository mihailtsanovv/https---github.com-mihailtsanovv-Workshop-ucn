package Control;

import Model.*;
import DB.*;

import java.util.ArrayList;

public class CtrInvoice {
	
	CtrSale sc = new CtrSale();

	public CtrInvoice() {

	}

	public ArrayList<Invoice> findAllInvoicesBySaleId(int saleId) {
		IFDBInvoice dbInvoice = new DBInvoice();
		ArrayList<Invoice> allInvoice = new ArrayList<Invoice>();
		allInvoice = dbInvoice.getAllInvoiceBySaleId(saleId, false);
		return allInvoice;
	}

	public void insertInvoice(int saleId) throws Exception {
		Invoice invObj = new Invoice();
		invObj.setSaleDate(sc.findById(saleId).getDate());
		invObj.setSale(sc.findById(saleId));

		try {
			DBConnection.startTransaction();
			DBInvoice dbInvoice = new DBInvoice();
			dbInvoice.insertInvoice(invObj);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Invoice not inserted");
		}
	}
	
	public void deleteInvoice(int saleId) throws Exception {

		try {
			DBConnection.startTransaction();
			DBInvoice dbInvoice = new DBInvoice();
			dbInvoice.deleteInvoice(saleId);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Invoice not deleted");
		}
	}
}
