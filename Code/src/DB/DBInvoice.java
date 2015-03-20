package DB;

import DB.*;
import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBInvoice implements IFDBInvoice {
	private Connection con;

	public DBInvoice() {
		con = DBConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<Invoice> getAllInvoice(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	@Override
	public Invoice searchInvoiceId(int id, boolean retriveAssociation) {
		String wClause = "id like '%" + id + "%'";
		System.out.println("SearchC " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public int insertInvoice(Invoice inv) throws Exception {
//		int nextId = GetMax.getMaxId("Select max(id) from Invoice");
//		nextId = nextId + 1;
//		System.out.println("next id = " + nextId);
//
		int rc = -1;
		String query = "INSERT INTO Invoice(invoiceNo, paymentDate, amount)  VALUES('"
				+ inv.getNumber()
				+ "','"
				+ inv.getPaymentDate()
				+ "','"
				+ inv.getAmount()
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
			invObj.setNumber(results.getInt("invoiceNo"));
			invObj.setPaymentDate(results.getString("paymentDate"));
			invObj.setAmount(results.getInt("amount"));
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

}