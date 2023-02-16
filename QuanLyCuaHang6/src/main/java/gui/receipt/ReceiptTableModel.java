package gui.receipt;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Receipt;

public class ReceiptTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	String[] columnNames = {"Mã Hóa Đơn", "Mã Khách Hàng", "Mã Nhân Viên", "Ngày Tạo"};
	private List<Receipt> receiptList;
	
	public ReceiptTableModel() {
		receiptList = new ArrayList<Receipt>();
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return receiptList.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Receipt r = receiptList.get(rowIndex);
		switch(columnIndex) {
			case 0: return r.getId();
			case 1: return r.getCustomer();
			case 2: return r.getEmployee();
			case 3: return r.getDate();
		}
		return null;
	}
	/*
	 *  3 phương thức thêm
	 */
	@Override
	public String getColumnName(int i) {
		return columnNames[i];
	}
	//Để set List<Customer> cho model và kích hoạt event change
	public void setData(List<Receipt> receiptList) {
		this.receiptList = receiptList;
		this.fireTableDataChanged();
	}
	//Để lấy đối tượng customer ở row thứ rowIndex
	public Receipt get(int rowIndex) {
		return receiptList.get(rowIndex);
	}
	
}
