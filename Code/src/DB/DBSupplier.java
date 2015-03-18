package DB;

import DB.*;
import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBSupplier implements IFDBSupplier {
	private Connection con;

	public DBSupplier() {
		con = DBConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<Supplier> getAllSupplier(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	@Override
	public Supplier searchSupplierName(String name, boolean retriveAssociation) {
		String wClause = "name like '%" + name + "%'";
		System.out.println("SearchC " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public Supplier searchSupplierId(int id, boolean retriveAssociation) {
		String wClause = "id like '%" + id + "%'";
		System.out.println("SearchC " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public int insertSupplier(Supplier sup) throws Exception {
		int nextId = GetMax.getMaxId("Select max(id) from supomer");
		nextId = nextId + 1;
		System.out.println("next id = " + nextId);

		int rc = -1;
		String query = "INSERT INTO supomer(id,name, address, country, phone, email)  VALUES('"
				+ nextId
				+ "','"
				+ sup.getName()
				+ "','"
				+ sup.getAddress()
				+ "','"
				+ sup.getCountry()
				+ "','"
				+ sup.getPhone()
				+ "','"
				+ sup.getEmail() 
				+ "')";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("sup is not inserted correct");
			throw new Exception("sup is not inserted correct");
		}
		return (rc);
	}

	@Override
	public int updateSupplier(Supplier sup) {
		Supplier supObj = sup;
		int rc = -1;

		String query = "UPDATE supomer SET " + "name ='" + supObj.getName()
				+ "', "
				+ "address ='" + supObj.getAddress() 
				+ "', "
				+ "country ='" + supObj.getCountry() 
				+ "', " 
				+ "phone ='" + supObj.getPhone() 
				+ "' " 
				+ "email ='" + supObj.getEmail()
				+ "' " + " WHERE id = '" + supObj.getId() 
				+ "'";
		System.out.println("Update query:" + query);
		try { // update supomer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Update exception in supomer db: " + ex);
		}
		return (rc);
	}

	public int deleteSupplier(int id) {
		int rc = -1;

		String query = "DELETE FROM supomer WHERE id = '" + id + "'";
		System.out.println(query);
		try { // delete from supomer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Delete exception in supomer db: " + ex);
		}
		return (rc);
	}

	private ArrayList<Supplier> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Supplier> list = new ArrayList<Supplier>();

		String query = buildQuery(wClause);

		try { // read the supomer from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Supplier supObj = new Supplier();
				supObj = buildSupplier(results);
				list.add(supObj);
			}// end while
			stmt.close();
			// if (retrieveAssociation) { // The supervisor and department is to
			// be
			// // build as well
			// for (Supplier supObj : list) {
			// Supplier superEmp = singleWhere(
			// " ssn = '" + superssn + "'", false);
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
		String query = "SELECT * FROM Supplier";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private Supplier buildSupplier(ResultSet results) {
		Supplier supObj = new Supplier();
		try {
			supObj.setId(results.getInt("id"));
			supObj.setName(results.getString("name"));
			supObj.setAddress(results.getString("address"));
			supObj.setCountry(results.getString("country"));
			supObj.setPhone(results.getString("phone"));
			supObj.setEmail(results.getString("email"));
		} catch (Exception e) {
			System.out.println("error in building the supomer object");
		}
		return supObj;
	}

	private Supplier singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Supplier supObj = new Supplier();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the supomer from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				supObj = buildSupplier(results);
				// assocaition is to be build
				stmt.close();

			} else { // no supomer was found
				supObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return supObj;
	}

}