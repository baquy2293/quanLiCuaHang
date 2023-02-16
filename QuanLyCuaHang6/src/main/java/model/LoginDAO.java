package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import gui.login.Account;

public class LoginDAO {
	private final static String dbPrefix="jdbc:sqlserver://localhost";
	private static String user = "register";
	private static String password = "123456a@";
	private static String dbPort = "1433";
	private static String dbName = "QLCH";
	public LoginDAO(){
		try {
			DriverManager.registerDriver(new SQLServerDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
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
	
	private void closeConnection(Connection conn) {
		try {
			conn.close();
			System.out.println("Database Closed");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can't close database");
		}
	}
	
	public Account getUser(Account account) {
		Connection conn = getConnection();
		try (CallableStatement cs = conn.prepareCall("{call LayThongTinDangNhapTrongCSDL(?, ?, ?, ?, ?)}");){
			cs.setString(1, account.getUser());
			cs.setString(2,String.valueOf(account.getPassword()));
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.execute();
			if(cs.getString(5) != null) {
				Account srv_acc = new Account(cs.getString(3),  cs.getString(4).toCharArray());
				srv_acc.setPos(cs.getNString(5));
				return srv_acc;
			}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return null;
	}
}
