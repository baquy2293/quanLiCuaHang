package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import gui.login.Account;

/*
 * Lớp ProductDAO làm việc với CSDL để trả về 1 hoặc 1 list đối tượng Product
 * Cung cấp các phương thức
 * getListProduct();
 * search(Product p);
 * add(Product p);
 * update(Product p);
 * remove(String id);
 */

public class ProductDAO {
	private static String dbPrefix = "jdbc:sqlserver://localhost";
	private Account account;
    private static String dbPort = "1433";
    private static String databaseName = "QLCH";
	static class PRODUCT{
		static final String ID = "MaSP";
		static final String NAME = "TenSP";
		static final String TYPE = "LoaiSP";
		static final String PRICE = "DonGia";
		static final String QUANTITY = "SoLuong";
		static final String IMAGE = "HinhAnh";
		static final String STATE = "TrangThai";
		static final String DESCRIPTION = "MoTa";
		
	}
	public ProductDAO(Account account) {
		try {
			DriverManager.registerDriver(new SQLServerDriver());
			this.account = account;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		String dbURL = dbPrefix + ":" + dbPort + ";" + "DatabaseName=" + databaseName + ";encrypt=true;trustServerCertificate=true;";
		try {
			Connection conn = DriverManager.getConnection(dbURL, account.getUser(), String.valueOf(account.getPassword()));
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
	
	public List<Product> getListProduct(){
		String query = "Select * From SANPHAM";
		List<Product> productList = new ArrayList<Product>();
		Connection conn = getConnection(); //Khi dong chi can dong Connection thoi la dc
		try(
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
		){
			Product p;
			while (rs.next()) {
				String id = rs.getString(PRODUCT.ID);
				String name = rs.getString(PRODUCT.NAME);
				String type = rs.getString(PRODUCT.TYPE);
				float price = rs.getFloat(PRODUCT.PRICE);
				int quantity = rs.getInt(PRODUCT.QUANTITY);
				byte[] image = rs.getBytes(PRODUCT.IMAGE);
				String state = rs.getString(PRODUCT.STATE);
				String description = rs.getString(PRODUCT.DESCRIPTION);
				p = new Product(id, name, type, price, quantity, image, state, description);
				productList.add(p);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn); //Khi dong chi can dong Connection thoi la dc
		}
		return productList;
	}
	
	public List<Product> search(Product keyword) {
		List<Product> productList = new ArrayList<Product>();
		String query = getQueryCondition(keyword);
		if(query!=null) {
			query = "Select * From SANPHAM Where" + query.substring(0, query.length()-4);
			System.out.println(query);
			Connection conn = getConnection(); //Khi dong chi can dong Connection thoi la dc
			try(
				Statement st = conn.createStatement();
				ResultSet rs = st.executeQuery(query);
			){
				Product p;
				while (rs.next()) {
					String id = rs.getString(PRODUCT.ID);
					String name = rs.getString(PRODUCT.NAME);
					String type = rs.getString(PRODUCT.TYPE);
					float price = rs.getFloat(PRODUCT.PRICE);
					int quantity = rs.getInt(PRODUCT.QUANTITY);
					byte[] image = rs.getBytes(PRODUCT.IMAGE);
					String state = rs.getString(PRODUCT.STATE);
					String description = rs.getString(PRODUCT.DESCRIPTION);
					p = new Product(id, name, type, price, quantity, image, state, description);
					productList.add(p);
				}
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return productList;
	}
	
	
	public boolean add(Product product) {
		Connection conn = getConnection();
		String sql = "Insert SANPHAM(MaSP, TenSP, LoaiSP, DonGia, SoLuong, HinhAnh, TrangThai, MoTa) Values (?,?,?,?,?,?,?,?)";
		try(PreparedStatement insertStm =  conn.prepareStatement(sql);){
			insertStm.setString(1, product.getId());
			insertStm.setString(2, product.getName());
			insertStm.setString(3, product.getType());
			insertStm.setFloat(4, product.getPrice());
			insertStm.setInt(5, product.getQuantity());
			insertStm.setBytes(6, product.getImage());
			insertStm.setString(7, product.getState());
			insertStm.setString(8, product.getDescription());
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
	
	public boolean update(Product product) {
		Connection conn = getConnection();
		String sql = "Update SANPHAM set TenSP=?, LoaiSP=?, DonGia=?, SoLuong=?, HinhAnh=?, TrangThai=?, MoTa=? where MaSP=?";
		
		try(
			PreparedStatement updateStatement =  conn.prepareStatement(sql);
		){
			updateStatement.setString(1, product.getName());
			updateStatement.setString(2, product.getType());
			updateStatement.setFloat(3, product.getPrice());
			updateStatement.setInt(4, product.getQuantity());
			updateStatement.setBytes(5, product.getImage());
			updateStatement.setString(6, product.getState());
			updateStatement.setString(7, product.getDescription());
			updateStatement.setString(8, product.getId());
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
		String sql = "Delete From SANPHAM Where MaSP=?";
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

	/*-----------------------------Hàm bổ trợ không quan trọng-------------------------------------------------------------*/
	private String getQueryCondition(Product p) {
		StringBuilder queryCondition = new StringBuilder();
		if(p.getId()!=null) {
			queryCondition.append(getEqualCondition(PRODUCT.ID, p.getId(), false)).append(" and");
		}
		if(p.getName()!= null) {
			queryCondition.append(getEqualCondition(PRODUCT.NAME, p.getName(), true)).append(" and");
		}
		if(p.getType() !=null) {
			queryCondition.append(getEqualCondition(PRODUCT.TYPE, p.getType(), true)).append(" and");
		}
		if(p.getPrice() != null) {
			queryCondition.append(getComparisionCondition(PRODUCT.PRICE, p.getPrice(), ">=")).append(" and");
		}
		if(p.getQuantity() != null) {
			queryCondition.append(getComparisionCondition(PRODUCT.QUANTITY, p.getQuantity(), "<=")).append(" and");
		}
		if(p.getState()!=null) {
			queryCondition.append(getEqualCondition(PRODUCT.STATE, p.getState(), true)).append(" and");
		}
		return queryCondition.isEmpty()? null : queryCondition.toString();
	}
	
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
