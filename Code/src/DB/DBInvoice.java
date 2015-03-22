package DB;

import Control.CtrSale;
import DB.*;
import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBInvoice implements IFDBInvoice {
	private Connection con;

	CtrSale sc = new CtrSale();
	public DBInvoice() {
		con = DBConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<Invoice> getAllInvoiceBySaleId(int saleId, boolean retriveAssociation) {
		return miscWhere("saleId ="+ saleId, retriveAssociation);
	}

	@Override
	public Invoice searchInvoiceId(int id, boolean retriveAssociation) {
		String wClause = "id like '%" + id + "%'";
		System.out.println("SearchC " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public int insertInvoice(Invoice inv) throws Exception {
		int nextInvoiceNo = GetMax.getMaxId("Select max(InvoiceNo) from Invoice");
		nextInvoiceNo = nextInvoiceNo + 1;
		System.out.println("next invoiceNo = " + nextInvoiceNo);

		int rc = -1;
		String query = "INSERT INTO Invoice(InvoiceNo, saleDate, saleId)  VALUES('"
				+ nextInvoiceNo
				+ "','"
				+ inv.getSale().getDate()
				+ "','"
				+ inv.getSale().getId()
				+ "')";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("inv is not inserted correct");
			throw new Exception("inv is not inserted correct");
		}
		return (rc);
	}

	private ArrayList<Invoice> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Invoice> list = new ArrayList<Invoice>();

		String query = buildQuery(wClause);

		try { // read the Invoice from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Invoice invObj = new Invoice();
				invObj = buildInvoice(results);
				list.add(invObj);
			}// end while
			stmt.close();
			// if (retrieveAssociation) { // The invervisor and deinvment is to
			// be
			// // build as well
			// for (Invoice invObj : list) {
			// Invoice inverEmp = singleWhere(
			// " ssn = '" + inverssn + "'", false);
			// System.out.println("Supervisor is seleceted");
			// // here the deinvment has to be selected as well
			// }
			// }// end if

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	private String buildQuery(String wClause) {
		String query = "SELECT * FROM Invoice";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private Invoice buildInvoice(ResultSet results) {
		Invoice invObj = new Invoice();
		try {
			invObj.setInvoiceNo(results.getInt("invoiceNo"));
			invObj.setSaleDate(sc.findById(results.getInt("saleId")).getDate());
			invObj.setSale(sc.findById(results.getInt("saleId")));
		} catch (Exception e) {
			System.out.println("error in building the Invoice object");
		}
		return invObj;
	}

	private Invoice singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Invoice invObj = new Invoice();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the Invoice from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				invObj = buildInvoice(results);
				// assocaition is to be build
				stmt.close();

			} else { // no Invoice was found
				invObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return invObj;
	}

	@Override
	public int deleteInvoice(int saleId) {
		int rc = -1;

		String query = "DELETE FROM Invoice WHERE saleId = '" + saleId + "'";
		System.out.println(query);
		try { // delete from Sale
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Delete exception in Invoice db: " + ex);
		}
		return (rc);
	}

}