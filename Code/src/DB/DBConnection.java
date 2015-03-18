package DB;

import java.sql.*;

public class DBConnection {
	private static final String driver = "jdbc:sqlserver://localhost:1433";
	private static final String databaseName = ";databaseName=dmaJ0914_2Sem_1";
	private static String userName = ";user=dmaJ0914_2Sem_1";
	private static String password = ";password=IsAllowed";

	private DatabaseMetaData dma;
	private static Connection con;
	private static DBConnection instance = null;

	private DBConnection() {
		String url = driver + databaseName + userName + password;

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("Load af class ok");

		} catch (Exception e) {
			System.out.println("Can not find the driver");
			System.out.println(e.getMessage());
		}
		try {
			con = DriverManager.getConnection(url);
			con.setAutoCommit(true);
			dma = con.getMetaData();
			System.out.println("Connection to " + dma.getURL());
			System.out.println("Driver " + dma.getDriverName());
			System.out.println("Database product name "
					+ dma.getDatabaseProductName());
		} catch (Exception e) {

			System.out.println("Problems with the connection to the database");
			System.out.println(e.getMessage());
			System.out.println(url);
		}
	}

	public static void closeConnection() {
		try {
			con.close();
			System.out.println("The connection is closed");
		} catch (Exception e) {
			System.out.println("Error trying to close the database "
					+ e.getMessage());
		}
	}

	public Connection getDBcon() {
		return con;
	}

	public static DBConnection getInstance() {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}

	public static void startTransaction() {
		try {
			con.setAutoCommit(false);
		} catch (Exception e) {
			System.out.println("fail start transaction");
			System.out.println(e.getMessage());
		}
	}

	public static void commitTransaction() {
		try {
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("fail commit transaction");
			System.out.println(e.getMessage());
		}
	}

	public static void rollbackTransaction() {
		try {
			con.rollback();
			con.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("fail rollback transaction");
			System.out.println(e.getMessage());
		}
	}
}
