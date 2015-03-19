package Control;

import Model.*;
import DB.*;

import java.util.ArrayList;

public class CtrSupplier {

	/** Creates a new instance of CtrSupplier */
	public CtrSupplier() {

	}

	public ArrayList<Supplier> findAllSuppliers() {
		IFDBSupplier dbCust = new DBSupplier();
		ArrayList<Supplier> allCust = new ArrayList<Supplier>();
		allCust = dbCust.getAllSupplier(false);
		return allCust;
	}

	public Supplier findByName(String name) {
		IFDBSupplier dbCust = new DBSupplier();
		return dbCust.searchSupplierName(name, true);
	}

	public Supplier findById(int id) {
		IFDBSupplier dbCust = new DBSupplier();
		return dbCust.searchSupplierId(id, true);
	}

	public int updateSup(int id, String name, String address, String country,
			String phone, String email) {
		IFDBSupplier dbCust = new DBSupplier();
		Supplier supObj = new Supplier();

		supObj.setId(id);
		supObj.setName(name);
		supObj.setAddress(address);
		supObj.setCountry(country);
		supObj.setPhone(phone);
		supObj.setEmail(email);
		return dbCust.updateSupplier(supObj);

	}

	public void insertSup(String name, String address, String country,
			String phone, String email) throws Exception {
		Supplier supObj = new Supplier();
		supObj.setName(name);
		supObj.setAddress(address);
		supObj.setCountry(country);
		supObj.setPhone(phone);
		supObj.setEmail(email);

		try {
			DBConnection.startTransaction();
			DBSupplier dbCust = new DBSupplier();
			dbCust.insertSupplier(supObj);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Supplier not inserted");
		}
	}
	
	public void deleteSup(int id) throws Exception {

		try {
			DBConnection.startTransaction();
			DBSupplier dbSup = new DBSupplier();
			dbSup.deleteSupplier(id);
			DBConnection.commitTransaction();
		} catch (Exception e) {
			DBConnection.rollbackTransaction();
			throw new Exception("Supplier not deleted");
		}
	}

}
