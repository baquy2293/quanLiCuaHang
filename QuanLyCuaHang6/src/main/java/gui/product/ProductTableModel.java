package gui.product;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import gui.Utils;
import model.Product;

public class ProductTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Mã", "Tên Sản Phẩm", "Loại Sản Phẩm", "Trạng Thái", "Đơn Giá", "Số Lượng", "Mô Tả","Hình Ảnh"};
	private List<Product> products;
	
	public ProductTableModel() {
		products = new ArrayList<Product>();
	}
	
	public void setData(List<Product> products) {
		this.products = products;
		this.fireTableDataChanged();
	}
	
	
	@Override
	public String getColumnName(int i) {
		return columnNames[i];
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return products.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		Product product = products.get(row);
		switch(col) {
		case 0: return product.getId();
		case 1: return product.getName();
		case 2: return product.getType();
		case 3: return product.getState();
		case 4: return Utils.formatNumber(product.getPrice());
		case 5: return product.getQuantity();
		case 6: return product.getDescription();
		case 7: {
				String img = product.getImage() == null ? null 
								: Base64.getEncoder().encodeToString(product.getImage());
				return img;
			}
		}
		return null;
	}
	
	public void reloadModel() {
		this.fireTableDataChanged();
	}
	
	public Product getProduct(int rowIndex) {
		if(rowIndex != -1) {
			return products.get(rowIndex);
		}else {
			return null;
		}
		
	}
	
	public List<Product> getListProduct(){
		return this.products;
	}
	
	public void clear() {
		products.clear();
		this.fireTableDataChanged();
	}
}
