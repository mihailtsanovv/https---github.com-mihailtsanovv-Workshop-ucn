package DB;

import Control.CtrProduct;
import Control.CtrSale;
import DB.*;
import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBPartSale implements IFDBPartSale {
	private Connection con;
	CtrProduct pc = new CtrProduct();
	CtrSale sc = new CtrSale();

	public DBPartSale() {
		con = DBConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<PartSale> getAllPartSaleBySaleId(int saleId, boolean retriveAssociation) {
		return miscWhere("saleId="+saleId, retriveAssociation);
	}

	@Override
	public int insertPartSale(PartSale part) throws Exception {
//		int nextId = GetMax.getMaxId("Select max(id) from PartSale");
//		nextId = nextId + 1;
//		System.out.println("next id = " + nextId);
//
		int rc = -1;
		String query = "INSERT INTO PartSale(saleId, productBarcode, amount)  VALUES('"
				+ part.getSale().getId()
				+ "','"
				+ part.getProduct().getBarcode()
				+ "','"
				+ part.getAmount()
				+ "')";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("part is not inserted correct");
			throw new Exception("part is not inserted correct");
		}
		return (rc);
	}

	private ArrayList<PartSale> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<PartSale> list = new ArrayList<PartSale>();

		String query = buildQuery(wClause);

		try { // read the PartSale from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				PartSale partObj = new PartSale();
				partObj = buildPartSale(results);
				list.add(partObj);
			}// end while
			stmt.close();
			// if (retrieveAssociation) { // The partervisor and department is to
			// be
			// // build as well
			// for (PartSale partObj : list) {
			// PartSale parterEmp = singleWhere(
			// " ssn = '" + parterssn + "'", false);
			// System.out.println("Supervisor is seleceted");
			// // here the department has to be selected as well
			// }
			// }// end if

		} catch (Exception e) {
			System.out.println("Query exception - select: " + e);
			e.printStackTrace();
		}
		return list;
	}

	private String buildQuery(String wClause) {
		String query = "SELECT * FROM PartSale";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private PartSale buildPartSale(ResultSet results) {
		PartSale partObj = new PartSale();
		try {
			partObj.setSale(sc.findById(results.getInt("saleId")));
			partObj.setProduct(pc.findByBarcode(results.getInt("productBarcode")));
			partObj.setAmount(results.getInt("amount"));
		} catch (Exception e) {
			System.out.println("error in building the PartSale object");
		}
		return partObj;
	}

	private PartSale singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		PartSale partObj = new PartSale();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the PartSale from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				partObj = buildPartSale(results);
				// assocaition is to be build
				stmt.close();

			} else { // no PartSale was found
				partObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return partObj;
	}

}