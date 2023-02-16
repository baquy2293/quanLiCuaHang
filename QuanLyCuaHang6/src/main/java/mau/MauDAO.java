package mau;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public abstract class MauDAO<E> {
	private final static String dbPrefix="jdbc:sqlserver://localhost";
	private static String user = "sa";
	private static String password = "123456a@";
	private static String dbPort = "1433";
	private static String dbName = "QLCH";
	
	protected MauDAO() {
		try {
			DriverManager.registerDriver(new SQLServerDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Connection getConnection() {
		String dbURL = dbPrefix + ":" + dbPort + ";" + "DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
		try {
			Connection conn = DriverManager.getConnection(dbURL, user, password);
			System.out.println("Connected Successfully!");
			return conn;
		} catch (SQLException e) {
			System.out.println("Can't connect to database");
			e.printStackTrace();
		}
		return null;
	}
	
	protected void closeConnection(Connection conn) {
		try {
			conn.close();
			System.out.println("Database Closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can't close database");
		}
	}
	
	/*
	 * Lấy dữ liệu từ CSDL 
	 */
	public abstract List<E> getList();
	
	/*
	 * Tìm kiếm từ dữ liệu trong E e
	 */
	public abstract List<E> search(E e);
	
	/*
	 * Thêm e  vào trong CSDL
	 */
	public abstract boolean add(E e);
	
	/*
	 * Update dữ liệu CSDL từ dữ liệu trong e 
	 */
	public abstract boolean update(E e);
	
	/*
	 * Remove e.id trong CSDL
	 */
	public abstract boolean remove(String id);
	
	
	protected abstract String getQueryCondition(E es);
		
	protected String getEqualCondition(String columnName, String value, boolean isVarChar) {
		if(value.matches("[^']{1,}")) {
			String column = "[".concat(columnName).concat("]");
			if(isVarChar == true) {
				return  " " + column + " LIKE CONCAT('%', CONVERT(nvarchar, N'"+ value +"'), '%')";
			}
			return " " + column + " LIKE CONCAT('%', CONVERT(varchar, '"+ value +"'), '%')";
		}
		return null;
	}
	
	protected String getComparisionCondition(String columnName ,Number value, String comparator) {
		return " " + columnName + comparator + value.toString();
	}
}
