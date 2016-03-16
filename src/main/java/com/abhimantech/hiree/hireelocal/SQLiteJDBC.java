package com.abhimantech.hiree.hireelocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteJDBC {
	public static final String DATABASE_NAME = "resumedata";
	public static final String RESUME_TABLENAME = "Documents";
	public static final String TABLE_CREATE_SQL = "CREATE TABLE "
			+ RESUME_TABLENAME + " " + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
			+ "FileName           TEXT    NOT NULL, "
			+ " FilePath           TEXT     NOT NULL, "
			+ " Email           TEXT , " + " PhoneNum           TEXT , "
			+ " isSynced           INTEGER DEFAULT 0 , "
			+ " isProcessed        INTEGER DEFAULT 0 ) ";

	public static void initDatatbase() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME
					+ ".db");
			System.out.println("Opened database successfully");
			createTable(c);
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}

	private static void createTable(Connection c) {
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			stmt.executeUpdate(TABLE_CREATE_SQL);
			stmt.close();
			System.out.println("Table created successfully");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out
					.println("Table already exists, continue with data processing");
		}

	}

	public static void inserData(String sql) {
		Connection c = null;
		Statement stmt = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:" + DATABASE_NAME
					+ ".db");
			c.setAutoCommit(false);
			stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
			c.commit();
			c.close();
			System.out.println("Record inserted successfully");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			try {
				stmt.close();
				c.commit();
				c.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
