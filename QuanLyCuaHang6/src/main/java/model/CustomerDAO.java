package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import gui.login.Account;
import subclass.CustomDate;

public class CustomerDAO {
	private final static String dbPrefix="jdbc:sqlserver://localhost";
	private Account account;
	private static String dbPort = "1433";
	private static String dbName = "QLCH";
	final static class CUSTOMER{
		static final String ID = "MaKH";
		static final String USER="TaiKhoan";
		static final String NAME="TenKH";
		static final String DOB="NgaySinh";
		static final String ADDRESS="DiaChi";
		static final String PHONENUMBER="Sdt";
		static final String EMAIL="Email";
		static final String IMAGE="HinhAnh";
		static final String SCORE="Diem";
	}
	
	public CustomerDAO(Account account) {
		try {
			DriverManager.registerDriver(new SQLServerDriver());
			this.account = account;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Connection getConnection() {
		String url = dbPrefix + ":" + dbPort + ";" + "DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
		try {
			Connection conn = DriverManager.getConnection(url, account.getUser(), String.valueOf(account.getPassword()));
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
	
	public List<Customer> getListCustomer(){
		String query = "Select * From KHACHHANG";
		List<Customer> customerList = new ArrayList<Customer>();
		Connection conn = getConnection(); //Khi dong chi can dong Connection thoi la dc
		try(
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		){
			Customer p;
			while (rs.next()) {
				String id = rs.getString(CUSTOMER.ID);
				String user = rs.getString(CUSTOMER.USER);
				String name = rs.getString(CUSTOMER.NAME);
				Date dob = rs.getDate(CUSTOMER.DOB);
				String address = rs.getString(CUSTOMER.ADDRESS);
				String phoneNumber = rs.getString(CUSTOMER.PHONENUMBER);
				String email = rs.getString(CUSTOMER.EMAIL);
				byte[] image = rs.getBytes(CUSTOMER.IMAGE);
				Integer score = rs.getInt(CUSTOMER.SCORE);
				
				p = new Customer(id, user, name, CustomDate.of(dob) , address, phoneNumber, email, image, score);
				customerList.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn); //Khi dong chi can dong Connection thoi la dc
		}
		return customerList;
	}
	
	public List<Customer> search(Customer keyword) {
		List<Customer> customerList = new ArrayList<>();
		String query = getQueryCondition(keyword);
		if(query!=null) {
			query = "Select * From KHACHHANG Where" + query.substring(0, query.length()-4);
			Connection conn = getConnection(); //Khi dong chi can dong Connection thoi la dc
			try(
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
			){
				Customer p;
				while (rs.next()) {
					String id = rs.getString(CUSTOMER.ID);
					String user = rs.getString(CUSTOMER.USER);
					String name = rs.getString(CUSTOMER.NAME);
					Date dob = rs.getDate(CUSTOMER.DOB);
					String address = rs.getString(CUSTOMER.ADDRESS);
					String phoneNumber = rs.getString(CUSTOMER.PHONENUMBER);
					String email = rs.getString(CUSTOMER.EMAIL);
					byte[] image = rs.getBytes(CUSTOMER.IMAGE);
					Integer score = rs.getInt(CUSTOMER.SCORE);
					
					p = new Customer(id, user, name, CustomDate.of(dob) , address, phoneNumber, email, image, score);
					customerList.add(p);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}else { //Nếu query bằng null thì tức là tìm kiếm không nhập gì cả
			customerList = getListCustomer();
			
		}
		return customerList;
	}
	
	
	public boolean add(Customer customer) {
		Connection conn = getConnection();
		final String sql = String.format("Insert KHACHHANG(%s, %s, %s, %s, %s, %s, %s, %s, %s) Values (?, ?, ?, ?, ?, ?, ?, ?, 0)",
				CUSTOMER.ID, CUSTOMER.USER, CUSTOMER.NAME, CUSTOMER.DOB, CUSTOMER.ADDRESS,
				CUSTOMER.PHONENUMBER, CUSTOMER.EMAIL, CUSTOMER.IMAGE, CUSTOMER.SCORE);
		try(PreparedStatement insertStm =  conn.prepareStatement(sql);){
			Date dob = customer.getDob().toSQLDate();
			
			insertStm.setString(1, customer.getId());
			insertStm.setString(2, customer.getUser());
			insertStm.setString(3, customer.getName());
			insertStm.setDate(4, dob);
			insertStm.setString(5, customer.getAddress());
			insertStm.setString(6, customer.getPhoneNumber());
			insertStm.setString(7, customer.getEmail());
			insertStm.setBytes(8, customer.getImage());
			int result = insertStm.executeUpdate();
			if(result == 0) {
				return false;
			}
			return true; //True vi da thuc thi trong sql thanh cong hay khong con tuy co thoa man sql trong do hay ko
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return false;
	}
	
	public boolean update(Customer customer) {
		Connection conn = getConnection();
		final String sql =String.format("Update KHACHHANG set %s=?, %s=?, %s=?, %s=?, %s=?, %s=? where %s=?",
				CUSTOMER.NAME, CUSTOMER.DOB, CUSTOMER.ADDRESS, CUSTOMER.PHONENUMBER, CUSTOMER.EMAIL, CUSTOMER.IMAGE, CUSTOMER.ID);
		try(
			PreparedStatement updateStatement =  conn.prepareStatement(sql);
		){	
			Date dob = customer.getDob().toSQLDate();
			updateStatement.setString(1, customer.getName());
			updateStatement.setDate(2, dob);
			updateStatement.setString(3, customer.getAddress());
			updateStatement.setString(4, customer.getPhoneNumber());
			updateStatement.setString(5, customer.getEmail());
			updateStatement.setBytes(6, customer.getImage());
			updateStatement.setString(7, customer.getId());
			int result = updateStatement.executeUpdate();
			if(result == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			closeConnection(conn);
		}
		return false;
	}
	
	public  boolean remove(String id) {
		Connection conn = getConnection();
		String sql = String.format("Delete From KHACHHANG Where %s=?", CUSTOMER.ID) ;
		try(PreparedStatement deleteStm = conn.prepareStatement(sql);){
			deleteStm.setString(1, id);
			int result = deleteStm.executeUpdate();
			if(result == 0) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeConnection(conn);
		}
		return false;
	}

	/*-----------------------------Hàm bổ trợ quan trọng-------------------------------------------------------------------*/
	private String getQueryCondition(Customer c) {
		StringBuilder queryCondition = new StringBuilder();
		if(c.getId()!=null) {
			queryCondition.append(getEqualCondition(CUSTOMER.ID, c.getId(), false)).append(" and");
		}
		if(c.getUser() != null) {
			queryCondition.append(getEqualCondition(CUSTOMER.USER, c.getUser(), false)).append(" and");
		}
		if(c.getName() !=null) {
			queryCondition.append(getEqualCondition(CUSTOMER.NAME, c.getName(), true)).append(" and");
		}
		if(c.getDob()!=null) {
			CustomDate dob = c.getDob();
			if(dob.getMonth() != null) {
				queryCondition.append(getComparisionCondition(String.format("MONTH(%s)", CUSTOMER.DOB) , dob.getMonth(), "=")).append(" and");
			}
			if(dob.getYear() != null) {
				queryCondition.append(getComparisionCondition(String.format("YEAR(%s)", CUSTOMER.DOB) , dob.getYear(), "=")).append(" and");
			}
		}
		
		if(c.getPhoneNumber() != null) {
			queryCondition.append(getEqualCondition(CUSTOMER.PHONENUMBER, c.getPhoneNumber(), false)).append(" and");
		}
		if(c.getEmail() !=null) {
			queryCondition.append(getEqualCondition(CUSTOMER.EMAIL, c.getEmail(), false)).append(" and");
		}
		return queryCondition.isEmpty()? null : queryCondition.toString();
	}
	
	/*-----------------------------Hàm bổ trợ không quan trọng-------------------------------------------------------------*/
	
	private String getEqualCondition(String columnName, String value, boolean isVarChar) {
		if(value.matches("[^']{1,}")) {
			String column = "[".concat(columnName).concat("]");
			if(isVarChar == true) {
				return  " " + column + " LIKE CONCAT('%', CONVERT(nvarchar, N'"+ value +"'), '%')";
			}
			return " " + column + " LIKE CONCAT('%', CONVERT(varchar, '"+ value +"'), '%')";
		}
		return null;
	}
	
	private String getComparisionCondition(String columnName ,Number value, String comparator) {
		return " " + columnName + comparator + value.toString();
	}
}
