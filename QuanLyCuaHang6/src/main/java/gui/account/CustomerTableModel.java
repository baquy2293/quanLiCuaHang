package gui.account;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Customer;


/*
 * CustomerTableModel là class model chứa header (String[]) và dữ liệu (List<Customer>)
 * Khi JTable.setModel(model) thì 1 customer được set cho 1 row và gọi toString() để hiểu thị dữ liệu lên các Columns
 * Đối tượng được lưu trong model là Customer có thể truy nhập bằng cách gọi Customer c = list.get(int index);
 */
public class CustomerTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	final static String[] columnNames = {"ID", "User", "Họ và Tên", "Ngày Sinh", "Địa Chỉ", "Số Điện Thoại", "Email","Hình Ảnh", "Điểm"};
	private List<Customer> customerList;
	
	CustomerTableModel() {
		this.customerList = new ArrayList<Customer>();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return customerList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}
	
	/*
	 * Day la phuong thuc render du lieu len cot
	 */
	@Override
	public Object getValueAt(int row, int col) {
		Customer customer = customerList.get(row);
		switch(col) {
		case 0: return customer.getId();
		case 1: return customer.getUser();
		case 2: return customer.getName();
		case 3: return customer.getDob();
		case 4: return customer.getAddress();
		case 5: return customer.getPhoneNumber();
		case 6: return customer.getEmail();
		case 7 : return customer.getScore();
		case 8: {
				String img = customer.getImage() == null ? null 
								: Base64.getEncoder().encodeToString(customer.getImage());
				return img;
			}
		}
		return null;
	}
	
	/*
	 * 3 phương thức tự thêm
	 */
	// Để set header
	@Override
	public String getColumnName(int i) {
		return columnNames[i];
	}
	//Để set List<Customer> cho model và kích hoạt event change
	public void setData(List<Customer> customerList) {
		this.customerList = customerList;
		this.fireTableDataChanged();
	}
	//Để lấy đối tượng customer ở row thứ rowIndex
	public Customer get(int rowIndex) {
		return customerList.get(rowIndex);
	}
	
	
	
	
}
