package model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import gui.login.Account;
import model.ProductDAO.PRODUCT;
import subclass.CustomDate;

public class ReceiptDAO { //Làm việc với bảng HoaDon Và ChiTietHoaDon Và SanPham
	private final static String dbPrefix="jdbc:sqlserver://localhost";
	private Account account;
	private static String dbPort = "1433";
	private static String dbName = "QLCH";
	final static class RECEIPT{
		static final String ID = "MaHD";
		static final String EMPLOYEE="MaNV";
		static final String CUSTOMER="MaKH";
		static final String DATE="NgayLap";
	}
	final static class RECEIPT_DETAILS{
		static final String QUANTITY = "SoLuong";
	}
	
	public ReceiptDAO(Account account){
		try {
			DriverManager.registerDriver(new SQLServerDriver());
			this.account = account;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Connection getConnection() {
		String dbURL = dbPrefix + ":" + dbPort + ";" + "DatabaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
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
	
	public List<Receipt> getList() {
		List<Receipt> receiptList = new ArrayList<Receipt>();
		String query = "Select * From HOADON";
		Connection conn = getConnection();
		try(
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(query);
		){
			while(rs.next()) {
				String r_id = rs.getString(RECEIPT.ID);
				String r_employee = rs.getString(RECEIPT.EMPLOYEE);
				String r_customer = rs.getString(RECEIPT.CUSTOMER);
				Date r_date = rs.getDate(RECEIPT.DATE);
				receiptList.add(new Receipt(r_id, r_employee, r_customer, CustomDate.of(r_date)));
			}
			return receiptList;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn); //Khi dong chi can dong Connection thoi la dc
		}
		return null;
	}

	public List<Receipt> search(Receipt keyword) {
		String query = getQueryCondition(keyword);
		if(!query.isBlank()) {
			List<Receipt> receiptList = new ArrayList<Receipt>();
			query = "Select * From HOADON where " + query;
			System.out.println(query);
			Connection conn = getConnection();
			try(
					Statement stm = conn.createStatement();
					ResultSet rs = stm.executeQuery(query);
				){
					while(rs.next()) {
						String r_id = rs.getString(RECEIPT.ID);
						String r_employee = rs.getString(RECEIPT.EMPLOYEE);
						String r_customer = rs.getString(RECEIPT.CUSTOMER);
						Date r_date = rs.getDate(RECEIPT.DATE);
						receiptList.add(new Receipt(r_id, r_employee, r_customer, CustomDate.of(r_date)));
					}
					return receiptList;
				}catch(SQLException e) {
					e.printStackTrace();
				}finally {
					closeConnection(conn); //Khi dong chi can dong Connection thoi la dc
				}
		}
		return null;
	}

	public boolean add(Receipt r) {
		String initReceipt = "{call dbo.Khoi_Tao_Hoa_Don(?, ?, ?, ?)}";
		Connection conn = getConnection();
		try(CallableStatement cstm = conn.prepareCall(initReceipt)){
			conn.setAutoCommit(false);
			cstm.setString(1, r.getId());
			cstm.setString(2, r.getEmployee());
			cstm.setString(3, r.getCustomer());
			cstm.registerOutParameter(4, Types.BIT);
			cstm.execute();
			if(cstm.getBoolean(4)) {
				boolean isSuccess = addProductToReceipt(conn, r);
				if(isSuccess) {
					conn.commit();
					return true;
				}
			}
			//conn.rollback();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn); //Khi dong chi can dong Connection thoi la dc
		}
		return false;
	}
	
	private boolean addProductToReceipt(Connection conn, Receipt r) throws SQLException {
		String addToReceipt = "{call dbo.ThemSP_Vao_Hoa_Don(?, ?, ?, ?)}";
		List<Product> products = r.getProductList();
		if(products.isEmpty()) {return false;}
		CallableStatement cstm = conn.prepareCall(addToReceipt);
	
		for(Product p : products) {
			cstm.setString(1, r.getId());
			cstm.setString(2, r.getCustomer());
			cstm.setString(3, p.getId());
			cstm.setInt(4, p.getQuantity());
			cstm.addBatch();
		}
		cstm.executeBatch(); // Nếu không có lỗi thì câu lệnh dưới được chạy
		return true;
	}

	public boolean remove(String id) {
		String sql = "{call dbo.Xoa_Hoa_Don(?, ?)}";
		Connection conn = getConnection();
		
		try(
			CallableStatement cstm = conn.prepareCall(sql);
		){
			conn.setAutoCommit(false);
			cstm.setString(1, id);
			cstm.registerOutParameter(2, Types.BIT);
			cstm.execute();
			if(cstm.getInt(2) == 1) {
				conn.commit();
				return true;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn); //Khi dong chi can dong Connection thoi la dc
		}
		return false;
	}
	
	public List<Product> getPurchasedProduct(String r_id){
		List<Product> products = new ArrayList<>();
		String query = "Select MaSP, TenSP, DonGia, SoLuong From dbo.HD_SP Where MaHD LIKE '%"+ r_id +"%'";
		System.out.println(query);
		Connection conn = getConnection();
		try(
				Statement stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(query);
		){
			while(rs.next()) {
				String p_id = rs.getString(PRODUCT.ID);
				String p_name = rs.getString(PRODUCT.NAME);
				Float p_price = rs.getFloat(PRODUCT.PRICE);
				Integer p_quantity = rs.getInt(PRODUCT.QUANTITY);
				products.add(new Product(p_id, p_name, p_price, p_quantity));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			closeConnection(conn); //Khi dong chi can dong Connection thoi la dc
		}
		return products;
	}
	
	/*-----------------------------Hàm bổ trợ không quan trọng-------------------------------------------------------------*/
	private String getQueryCondition(Receipt r) {
		StringBuilder queryCondition = new StringBuilder();
		if(r.getId() != null) {
			queryCondition.append(getEqualCondition(RECEIPT.ID, r.getId(), false)).append(" and");
		}
		if(r.getEmployee() != null) {
			queryCondition.append(getEqualCondition(RECEIPT.EMPLOYEE, r.getEmployee(), false)).append(" and");
		}
		if(r.getCustomer()!=null) {
			queryCondition.append(getEqualCondition(RECEIPT.CUSTOMER, r.getCustomer(), false)).append(" and");
		}
		if(r.getDate()!=null) {
			Integer p;
			if((p =r.getDate().getYear())!=null) {
				queryCondition.append(getComparisionCondition(String.format("YEAR(%s)", RECEIPT.DATE) , p, "=")).append(" and");
			}
			if((p = r.getDate().getMonth())!=null) {
				queryCondition.append(getComparisionCondition(String.format("MONTH(%s)", RECEIPT.DATE) , p, "=")).append(" and");
			}
			if((p = r.getDate().getDay())!=null) {
				queryCondition.append(getComparisionCondition(String.format("DAY(%s)", RECEIPT.DATE) ,p, "=")).append(" and");
			}
		}
		queryCondition.setLength(queryCondition.length() - 4);
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
