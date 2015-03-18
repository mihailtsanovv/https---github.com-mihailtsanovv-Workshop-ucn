package DB;

import DB.*;
import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBProduct implements IFDBProduct {
	private Connection con;

	public DBProduct() {
		con = DBConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<Product> getAllProduct(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	@Override
	public Product searchProductName(String name, boolean retriveAssociation) {
		String wClause = "name like '%" + name + "%'";
		System.out.println("SearchP " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public Product searchProductBarcode(int barcode, boolean retriveAssociation) {
		String wClause = "barcode like '%" + barcode + "%'";
		System.out.println("SearchP " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public int insertProduct(Product pro) throws Exception {
		int nextBarcode = GetMax.getMaxId("Select max(barcode) from proomer");
		nextBarcode = nextBarcode + 1;
		System.out.println("next barcode = " + nextBarcode);

		int rc = -1;
		String query = "INSERT INTO proomer(barcode, name, purchasePrice, "
				+ "salesPrice, rentPrice, countryOfOrigin, minStock, size, "
				+ "colour, type, description, fabric, calibre)  VALUES('"
				+ nextBarcode
				+ "','"
				+ pro.getName()
				+ "','"
				+ pro.getPurchasePrice()
				+ "','"
				+ pro.getSalesPrice()
				+ "','"
				+ pro.getRentPrice()
				+ "','"
				+ pro.getCountryOfOrigin() 
				+ "','"
				+ pro.getMinStock()
				+ "','"
				+ pro.getSize()
				+ "','"
				+ pro.getColour()
				+ "','"
				+ pro.getType()
				+ "','"
				+ pro.getDescription() 
				+ "','"
				+ pro.getFabric()
				+ "','"
				+ pro.getCalibre() 
				+ "')";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("pro is not inserted correct");
			throw new Exception("pro is not inserted correct");
		}
		return (rc);
	}

	@Override
	public int updateProduct(Product pro) {
		Product proObj = pro;
		int rc = -1;

		String query = "UPDATE proomer SET " + "name ='" + proObj.getName()
				+ "', "
				+ "purchasePrice ='" + proObj.getPurchasePrice() 
				+ "', "
				+ "salesPrice ='" + proObj.getSalesPrice() 
				+ "', " 
				+ "rentPrice ='" + proObj.getRentPrice() 
				+ "' " 
				+ "countryOfOrigin ='" + proObj.getCountryOfOrigin()
				+ "', "
				+ "minStock ='" + proObj.getMinStock() 
				+ "', "
				+ "size ='" + proObj.getSize() 
				+ "', " 
				+ "colour ='" + proObj.getColour() 
				+ "' " 
				+ "type ='" + proObj.getType()
				+ "', "
				+ "description ='" + proObj.getDescription() 
				+ "', " 
				+ "fabric ='" + proObj.getFabric() 
				+ "' " 
				+ "calibre ='" + proObj.getCalibre()
				+ "' " + " WHERE barcode = '" + proObj.getBarcode() 
				+ "'";
		System.out.println("Update query:" + query);
		try { // update proomer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Update exception in proomer db: " + ex);
		}
		return (rc);
	}

	public int deleteProduct(int barcode) {
		int rc = -1;

		String query = "DELETE FROM proomer WHERE barcode = '" + barcode + "'";
		System.out.println(query);
		try { // delete from proomer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Delete exception in proomer db: " + ex);
		}
		return (rc);
	}

	private ArrayList<Product> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Product> list = new ArrayList<Product>();

		String query = buildQuery(wClause);

		try { // read the proomer from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Product proObj = new Product();
				proObj = buildProduct(results);
				list.add(proObj);
			}// end while
			stmt.close();
			// if (retrieveAssociation) { // The proervisor and department is to
			// be
			// // build as well
			// for (Product proObj : list) {
			// Product proerEmp = singleWhere(
			// " ssn = '" + proerssn + "'", false);
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
		String query = "SELECT * FROM Product";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private Product buildProduct(ResultSet results) {
		Product proObj = new Product();
		try {
			proObj.setBarcode(results.getInt("barcode"));
			proObj.setName(results.getString("name"));
			proObj.setPurchasePrice(results.getDouble("purchasePrice"));
			proObj.setSalesPrice(results.getDouble("salesPrice"));
			proObj.setRentPrice(results.getDouble("setRentPrice"));
			proObj.setCountryOfOrigin(results.getString("countryOfOrigin"));
			proObj.setMinStock(results.getInt("minStock"));
			proObj.setSize(results.getString("size"));
			proObj.setColour(results.getString("colour"));
			proObj.setType(results.getString("type"));
			proObj.setDescription(results.getString("description"));
			proObj.setFabric(results.getString("fabric"));
			proObj.setCalibre(results.getDouble("calibre"));
		} catch (Exception e) {
			System.out.println("error in building the proomer object");
		}
		return proObj;
	}

	private Product singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Product proObj = new Product();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the proomer from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				proObj = buildProduct(results);
				// assocaition is to be build
				stmt.close();

			} else { // no proomer was found
				proObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return proObj;
	}

}