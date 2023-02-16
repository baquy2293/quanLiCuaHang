package gui.receipt;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import gui.Utils;
import gui.product.ProductTableModel;
import mau.PaneMau;
import model.Product;
import model.Receipt;
import subclass.CustomDate;
import subclass.GetProductObject;

public class ReceiptView extends PaneMau {
	private static final long serialVersionUID = 1L;
	private JFrame owner;
	private JTextField r_id;
	private JTextField r_customer;
	private JTextField r_employee;
	private JTextField r_date;
	private ProductTableModel r_productModel;
	private JTable r_productTable;
	private JTextField r_totalCost;
	
	private ReceiptTableModel receiptModel;
	private JTable receiptTable;
	
	private JButton c_reload;
	private JButton c_addBtn;
	private JButton c_searchBtn;
	private JButton c_removeBtn;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReceiptAdd dialog = new ReceiptAdd(new JFrame());
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReceiptView(JFrame owner) {
		super();
		this.owner = owner;
		JLabel lblNewLabel = new JLabel("Hóa Đơn");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(400, 11, 180, 30);
		pane.add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 101, 410, 588);
		pane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Hóa Đơn:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 10, 84, 30);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã Khách Hàng:");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 60, 120, 30);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Nhân Viên:");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(10, 100, 120, 30);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Ngày lập:");
		lblNewLabel_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(10, 140, 120, 30);
		panel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Sản Phẩm Mua:");
		lblNewLabel_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBounds(10, 200, 120, 30);
		panel.add(lblNewLabel_1_1_1_1_1);
		
		r_id = new JTextField();
		r_id.setEditable(false);
		r_id.setHorizontalAlignment(SwingConstants.CENTER);
		r_id.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_id.setBounds(125, 10, 275, 30);
		panel.add(r_id);
		r_id.setColumns(10);
		
		r_customer = new JTextField();
		r_customer.setEditable(false);
		r_customer.setHorizontalAlignment(SwingConstants.CENTER);
		r_customer.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_customer.setColumns(10);
		r_customer.setBounds(125, 60, 275, 30);
		panel.add(r_customer);
		
		r_employee = new JTextField();
		r_employee.setEditable(false);
		r_employee.setHorizontalAlignment(SwingConstants.CENTER);
		r_employee.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_employee.setColumns(10);
		r_employee.setBounds(125, 101, 275, 30);
		panel.add(r_employee);
		
		r_date = new JTextField();
		r_date.setEditable(false);
		r_date.setHorizontalAlignment(SwingConstants.CENTER);
		r_date.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_date.setColumns(10);
		r_date.setBounds(125, 140, 120, 30);
		panel.add(r_date);
		
		r_productModel = new ProductTableModel();
		r_productTable = this.createDefaultJTable(10, 240, 390, 282);
		r_productTable.setModel(r_productModel);
		r_productTable.getColumnModel().removeColumn(r_productTable.getColumnModel().getColumn(7));
		r_productTable.getColumnModel().removeColumn(r_productTable.getColumnModel().getColumn(6));
		r_productTable.getColumnModel().removeColumn(r_productTable.getColumnModel().getColumn(3));
		r_productTable.getColumnModel().removeColumn(r_productTable.getColumnModel().getColumn(2));
		JScrollPane productTableScroll = this.createDefaultJScrollPane(r_productTable, 10, 240, 390, 282);
		panel.add(productTableScroll);
		
		JLabel lblNewLabel_1_1_1_1_2 = new JLabel("Tổng Tiền:");
		lblNewLabel_1_1_1_1_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_2.setBounds(10, 547, 120, 30);
		panel.add(lblNewLabel_1_1_1_1_2);
		
		r_totalCost = new JTextField();
		r_totalCost.setEditable(false);
		r_totalCost.setHorizontalAlignment(SwingConstants.RIGHT);
		r_totalCost.setColumns(10);
		r_totalCost.setBounds(125, 547, 275, 30);
		panel.add(r_totalCost);
		
		JLabel lblChiTitHa = new JLabel("Chi Tiết Hóa Đơn");
		lblChiTitHa.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblChiTitHa.setBounds(20, 75, 180, 30);
		pane.add(lblChiTitHa);
		
		receiptModel = new ReceiptTableModel();
		receiptTable = this.createDefaultJTable(460, 340, 510, 348);
		receiptTable.setModel(receiptModel);
		JScrollPane receiptTableScroll = this.createDefaultJScrollPane(receiptTable, 460, 340, 510, 348);
		pane.add(receiptTableScroll);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(640, 101, 330, 176);
		pane.add(panel_1);
		
		c_addBtn = new JButton("Tạo Hóa Đơn");
		c_addBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_addBtn.setBounds(530, 300, 140, 30);
		pane.add(c_addBtn);
		
		c_searchBtn = new JButton("Tìm Kiếm");
		c_searchBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_searchBtn.setBounds(680, 300, 140, 30);
		pane.add(c_searchBtn);
		
		c_removeBtn = new JButton("Xóa Hóa Đơn");
		c_removeBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_removeBtn.setBounds(830, 300, 140, 30);
		pane.add(c_removeBtn);
		
		c_reload = new JButton("");
		c_reload.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_reload.setBounds(460, 300, 60, 30);
		c_reload.setIcon(this.getImageIcon("src/main/resources/logo/refresh.png", 24, 24));
		pane.add(c_reload);
		/*-------------------------------- Event ----------------------------------------------*/
		receiptTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				selectedItemChangeHandler();
			}
			
			});
	}
	
	public void addReceiptReloadListener(MouseAdapter ma) {
		c_reload.addMouseListener(ma);
	}
	
	public void addReceiptAddListener(MouseAdapter ma) {
		c_addBtn.addMouseListener(ma);
	}
	
	public void addReceiptSearchListener(MouseAdapter ma) {
		c_searchBtn.addMouseListener(ma);
	}
	
	public void addReceiptRemoveListener(MouseAdapter ma) {
		c_removeBtn.addMouseListener(ma);
	}
	
	public void showListReceipt(List<Receipt> receiptList) {
		receiptModel.setData(receiptList);
	}
	
	public Receipt showReceiptAddAndGetResponse() {
		ReceiptAdd receiptAdd = new ReceiptAdd(owner);
		receiptAdd.setVisible(true);
		return receiptAdd.getReceiptResponse();
	}
	
	public Receipt showReceiptSearchAndGetResponse() {
		ReceiptSearchGUI receiptSearch = new ReceiptSearchGUI(owner);
		receiptSearch.setVisible(true);
		Receipt keyword = receiptSearch.getReceiptResponse();
		return keyword;
	}
	
	public String showReceiptRemoveAndGetResponse() {
		int selectedRow = receiptTable.getSelectedRow();
		if(selectedRow < 0) {
			return null;
		}
		Receipt r = receiptModel.get(selectedRow);
		int choice = JOptionPane.showConfirmDialog(owner, "Bạn có muốn hủy hóa đơn " + r.getId(),"Hủy Hóa Đơn" ,JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		if(choice == JOptionPane.YES_OPTION) {
			return r.getId();
		}
		return null;
	}
	
	final static boolean checkValid(JFrame owner, String id, String employee, String customer, CustomDate date) {
		if(id!=null) {
			if(!id.matches("[a-zA-Z0-9]{12}")) {
				JOptionPane.showMessageDialog(owner, "Mã Hóa Đơn nhập không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(employee!=null) {
			if(!employee.matches("(NV|Nv|nv)[0-9]{6}")) {
				JOptionPane.showMessageDialog(owner, "Mã nhân viên không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(customer!=null) {
			if(!customer.matches("(KH|Kh|kh)[0-9]{6}")) {
				JOptionPane.showMessageDialog(owner, "Mã khách hàng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(date!=null) {
			if(!CustomDate.isValidDate(date)) {
				JOptionPane.showMessageDialog(owner, "Ngày không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
		return false;
	}
	
	
	
	private void selectedItemChangeHandler() {
		int selectedRow = receiptTable.getSelectedRow();
		if(selectedRow != -1) {
			Receipt r = receiptModel.get(selectedRow);
			float total = 0;
			r_id.setText(r.getId());
			r_employee.setText(r.getEmployee());
			r_customer.setText(r.getCustomer());
			r_date.setText(r.getDate().toString());
			GetProductObject gpo = new GetProductObject(r.getId());
			List<Product> products = gpo.get();
			r_productModel.setData(products);
			
			for(Product p : products) {
				total+= p.getPrice()*p.getQuantity();
			}
			r_totalCost.setText(Utils.formatNumber(total));
		}else {
			clearView();
		}
	}
	
	private void clearView() {
		r_id.setText("");
		r_employee.setText("");
		r_customer.setText("");
		r_date.setText("");
		r_productModel.clear();
	}
}
