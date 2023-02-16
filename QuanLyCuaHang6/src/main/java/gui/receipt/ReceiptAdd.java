package gui.receipt;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.Utils;
import gui.product.ProductTableModel;
import mau.SubDialog;
import model.Product;
import model.Receipt;
import subclass.CustomDate;

public class ReceiptAdd extends SubDialog {
	private static final long serialVersionUID = 1L;
	private JFrame owner;
	private ReceiptAdd thisDialog = this;
	private JTextField r_employeeId;
	private JTextField r_customerId;
	private ProductTableModel productModel;
	private JTable cartTable;
	private JLabel r_totalCost;
	
	private List<Product> productList = null;
	private Receipt receiptResponse;
	
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
	public ReceiptAdd(JFrame owner) {
		super(owner);
		productModel = new ProductTableModel();
		JLabel title = this.createDefaultHeader("Tạo Hóa Đơn", 200, 10);
		pane.add(title);
		
		JLabel lblNewLabel = new JLabel("Mã Nhân Viên");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 50, 120, 30);
		pane.add(lblNewLabel);
		
		r_employeeId = new JTextField();
		r_employeeId.setText("NV00000000");
		r_employeeId.setHorizontalAlignment(SwingConstants.CENTER);
		r_employeeId.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_employeeId.setBounds(140, 50, 300, 30);
		pane.add(r_employeeId);
		r_employeeId.setColumns(10);
		
		JLabel lblMKhchHng = new JLabel("Mã Khách Hàng:");
		lblMKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblMKhchHng.setBounds(20, 91, 120, 30);
		pane.add(lblMKhchHng);
		
		r_customerId = new JTextField();
		r_customerId.setText("KH00000001");
		r_customerId.setHorizontalAlignment(SwingConstants.CENTER);
		r_customerId.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_customerId.setColumns(10);
		r_customerId.setBounds(140, 90, 300, 30);
		pane.add(r_customerId);
		
		JLabel lblSnPhm = new JLabel("Sản Phẩm:");
		lblSnPhm.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSnPhm.setBounds(20, 142, 120, 30);
		pane.add(lblSnPhm);
		
		JButton r_checkBtn = new JButton("Check");
		r_checkBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_checkBtn.setBounds(440, 90, 120, 30);
		pane.add(r_checkBtn);
		
		cartTable = this.createDefaultJTable(140, 142, 300, 280);
		productModel = new ProductTableModel();
		cartTable.setModel(productModel);
		cartTable.getColumnModel().removeColumn(cartTable.getColumnModel().getColumn(7));
		cartTable.getColumnModel().removeColumn(cartTable.getColumnModel().getColumn(6));
		cartTable.getColumnModel().removeColumn(cartTable.getColumnModel().getColumn(3));
		cartTable.getColumnModel().removeColumn(cartTable.getColumnModel().getColumn(2));
		
		JScrollPane cartTableScrollPane = this.createDefaultJScrollPane(cartTable, 140, 142, 300, 280);
		pane.add(cartTableScrollPane);
		
		JButton r_addProduct = new JButton("Giỏ Hàng");
		
		r_addProduct.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_addProduct.setBounds(440, 142, 120, 30);
		pane.add(r_addProduct);
		
		JLabel lblTngChiPh = new JLabel("Tổng Chi Phí:");
		lblTngChiPh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTngChiPh.setBounds(20, 442, 120, 30);
		pane.add(lblTngChiPh);
		
		JButton r_addBtn = new JButton("Xác Nhận");
		r_addBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_addBtn.setBounds(50, 520, 120, 30);
		pane.add(r_addBtn);
		
		JButton r_cancelBtn = new JButton("Hủy");
		r_cancelBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_cancelBtn.setBounds(401, 520, 120, 30);
		pane.add(r_cancelBtn);
		
		r_totalCost = new JLabel("0");
		r_totalCost.setHorizontalAlignment(SwingConstants.RIGHT);
		r_totalCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		r_totalCost.setBounds(280, 442, 160, 30);
		pane.add(r_totalCost);
		
		/*----------------------------------- Add Event ----------------------------------*/
		r_addProduct.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addProductButtonHandler();
			}
		});
		r_addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addButtonHandler();
			}
		});
		r_cancelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				receiptResponse = null;
				thisDialog.dispose();
			}
		});
	}
	
	public Receipt getReceiptResponse(){
		return receiptResponse;
	}
	/*
	 * Phuong thuc Private (Xử lý nội bộ)
	 */
	private void addProductButtonHandler() {
		CartGUI cartGUI = new CartGUI(owner, productModel.getListProduct());
		cartGUI.setVisible(true);
		List<Product> products = cartGUI.getProductListResponse();
		if(products==null) {// Đóng Cart và không có sự thay đổi
			return;
		}
		//Hiểu thị (cập nhật) lên cartTable
		productModel.setData(products);
		// Tính total
		float total = 0;
		for(int i = 0; i < products.size(); i++) {
			total+= products.get(i).getPrice()*products.get(i).getQuantity();
		}
		r_totalCost.setText(Utils.formatNumber(total));
	}
	
	private void addButtonHandler() {
		//Lấy dữ liệu
		String employee = r_employeeId.getText();
		String customer = r_customerId.getText();
		this.productList = productModel.getListProduct();
		//if(valid){
			LocalDate today = LocalDate.now();
			CustomDate date = new CustomDate(today.getYear(), today.getMonth().getValue(), today.getDayOfMonth());
			String id = String.format(Utils.random(12)); //char(12) nha
			//Set du lieu
			receiptResponse = new Receipt(id, employee, customer, date, this.productList);
			thisDialog.dispose();
		//}
	}
	
}
