package DB;

import DB.*;
import Model.*;

import java.sql.*;
import java.util.ArrayList;

public class DBCustomer implements IFDBCustomer {
	private Connection con;

	public DBCustomer() {
		con = DBConnection.getInstance().getDBcon();
	}

	@Override
	public ArrayList<Customer> getAllCustomer(boolean retriveAssociation) {
		return miscWhere("", retriveAssociation);
	}

	@Override
	public Customer searchCustomerName(String name, boolean retriveAssociation) {
		String wClause = "name like '%" + name + "%'";
		System.out.println("SearchC " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public Customer searchCustomerId(int id, boolean retriveAssociation) {
		String wClause = "id like '%" + id + "%'";
		System.out.println("SearchC " + wClause);
		return singleWhere(wClause, retriveAssociation);
	}

	@Override
	public int insertCustomer(Customer cust) throws Exception {
		int nextId = GetMax.getMaxId("Select max(id) from customer");
		nextId = nextId + 1;
		System.out.println("next id = " + nextId);

		int rc = -1;
		String query = "INSERT INTO customer(id,name, address, zipcode,city, phone)  VALUES('"
				+ nextId
				+ "','"
				+ cust.getName()
				+ "','"
				+ cust.getAddress()
				+ "','"
				+ cust.getZipCode()
				+ "','"
				+ cust.getCity()
				+ "','"
				+ cust.getPhone() + "')";

		System.out.println("insert : " + query);
		try {
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// end try
		catch (SQLException ex) {
			System.out.println("cust is not inserted correct");
			throw new Exception("cust is not inserted correct");
		}
		return (rc);
	}

	@Override
	public int updateCustomer(Customer cust) {
		Customer custObj = cust;
		int rc = -1;

		String query = "UPDATE customer SET " + "name ='" + custObj.getName()
				+ "', "
				+ "address ='"
				+ custObj.getAddress() + "', "
				+ "zipcode ='" + custObj.getZipCode() 
				+ "', " + "city ='"
				+ custObj.getCity() + "' " + "phone ='" 
				+ custObj.getPhone()
				+ "' " + " WHERE id = '" 
				+ custObj.getId() 
				+ "'";
		System.out.println("Update query:" + query);
		try { // update customer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);

			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Update exception in customer db: " + ex);
		}
		return (rc);
	}

	public int deleteCustomer(int id) {
		int rc = -1;

		String query = "DELETE FROM customer WHERE id = '" + id + "'";
		System.out.println(query);
		try { // delete from customer
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			rc = stmt.executeUpdate(query);
			stmt.close();
		}// slut try
		catch (Exception ex) {
			System.out.println("Delete exception in customer db: " + ex);
		}
		return (rc);
	}

	private ArrayList<Customer> miscWhere(String wClause,
			boolean retrieveAssociation) {
		ResultSet results;
		ArrayList<Customer> list = new ArrayList<Customer>();

		String query = buildQuery(wClause);

		try { // read the customer from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			while (results.next()) {
				Customer custObj = new Customer();
				custObj = buildCustomer(results);
				list.add(custObj);
			}// end while
			stmt.close();
			// if (retrieveAssociation) { // The supervisor and department is to
			// be
			// // build as well
			// for (Customer custObj : list) {
			// Customer superEmp = singleWhere(
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
		String query = "SELECT * FROM Customer";

		if (wClause.length() > 0)
			query = query + " WHERE " + wClause;

		return query;
	}

	private Customer buildCustomer(ResultSet results) {
		Customer custObj = new Customer();
		try {
			custObj.setId(results.getInt("id"));
			custObj.setName(results.getString("name"));
			custObj.setAddress(results.getString("address"));
			custObj.setZipCode(results.getInt("zipcode"));
			custObj.setCity(results.getString("city"));
			custObj.setPhone(results.getString("phone"));
		} catch (Exception e) {
			System.out.println("error in building the customer object");
		}
		return custObj;
	}

	private Customer singleWhere(String wClause, boolean retrieveAssociation) {
		ResultSet results;
		Customer custObj = new Customer();

		String query = buildQuery(wClause);
		System.out.println(query);
		try { // read the customer from the database
			Statement stmt = con.createStatement();
			stmt.setQueryTimeout(5);
			results = stmt.executeQuery(query);

			if (results.next()) {
				custObj = buildCustomer(results);
				// assocaition is to be build
				stmt.close();

			} else { // no customer was found
				custObj = null;
			}
		}// end try
		catch (Exception e) {
			System.out.println("Query exception: " + e);
		}
		return custObj;
	}

}