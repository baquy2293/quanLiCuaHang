package gui.receipt;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mau.SubDialog;
import model.Receipt;
import subclass.CustomDate;

public class ReceiptSearchGUI extends SubDialog {
	private static final long serialVersionUID = 1L;
	private ReceiptSearchGUI thisDialog = this;
	
	private JTextField r_customer;
	private JTextField r_employee;
	private JTextField r_id;
	private JTextField r_day;
	private JTextField r_month;
	private JTextField r_year;

	private Receipt receiptResponse;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ReceiptSearchGUI dialog = new ReceiptSearchGUI(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ReceiptSearchGUI(JFrame owner) {
		super(owner);
		setSize(450, 300);
		
		JLabel lblNewLabel = new JLabel("Tìm Kiếm Hóa Đơn");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 414, 30);
		pane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Hóa Đơn :");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 45, 100, 30);
		pane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Mã Nhân Viên :");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(20, 80, 100, 30);
		pane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Mã Khách Hàng :");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(20, 110, 120, 30);
		pane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Ngày Lập :");
		lblNewLabel_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1.setBounds(20, 140, 120, 30);
		pane.add(lblNewLabel_1_1_1_1);
		
		r_customer = new JTextField();
		r_customer.setHorizontalAlignment(SwingConstants.CENTER);
		r_customer.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_customer.setBounds(130, 113, 280, 24);
		pane.add(r_customer);
		r_customer.setColumns(10);
		
		r_employee = new JTextField();
		r_employee.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_employee.setHorizontalAlignment(SwingConstants.CENTER);
		r_employee.setColumns(10);
		r_employee.setBounds(130, 80, 280, 24);
		pane.add(r_employee);
		
		r_id = new JTextField();
		r_id.setHorizontalAlignment(SwingConstants.CENTER);
		r_id.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_id.setColumns(10);
		r_id.setBounds(130, 45, 280, 24);
		pane.add(r_id);
		
		r_day = new JTextField();
		r_day.setHorizontalAlignment(SwingConstants.CENTER);
		r_day.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_day.setBounds(130, 143, 60, 24);
		pane.add(r_day);
		r_day.setColumns(10);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Tháng");
		lblNewLabel_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1.setBounds(200, 140, 50, 30);
		pane.add(lblNewLabel_1_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1_1 = new JLabel("Năm");
		lblNewLabel_1_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1_1_1_1.setBounds(316, 140, 50, 30);
		pane.add(lblNewLabel_1_1_1_1_1_1);
		
		r_month = new JTextField();
		r_month.setHorizontalAlignment(SwingConstants.CENTER);
		r_month.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_month.setColumns(10);
		r_month.setBounds(249, 143, 60, 24);
		pane.add(r_month);
		
		r_year = new JTextField();
		r_year.setHorizontalAlignment(SwingConstants.CENTER);
		r_year.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_year.setColumns(10);
		r_year.setBounds(351, 143, 60, 24);
		pane.add(r_year);
		
		JButton r_searchBtn = new JButton("Tìm Kiếm");
		r_searchBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_searchBtn.setBounds(51, 212, 120, 30);
		pane.add(r_searchBtn);
		
		JButton r_cancel = new JButton("Hủy Bỏ");
		r_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cancel();
			}
		});
		/* ----------------------------- Event -----------------------------------------*/
		r_searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchButtonHandler();
			}
		});
		
		r_cancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		r_cancel.setBounds(262, 212, 120, 30);
		pane.add(r_cancel);
	}
	
	private void searchButtonHandler() {
		String id = r_id.getText().isBlank() ? null : r_id.getText();
		String employee = r_employee.getText().isBlank() ? null : r_employee.getText();
		String customer = r_customer.getText().isBlank() ? null : r_customer.getText();
		Integer day = !r_day.getText().matches("[0-9]{1,2}") ? null : Integer.parseInt(r_day.getText());
		Integer month = !r_month.getText().matches("[0-9]{1,2}") ? null : Integer.parseInt(r_month.getText());
		Integer year = !r_year.getText().matches("[0-9]{1,4}") ? null :  Integer.parseInt(r_year.getText());
		
		if((!r_day.getText().isBlank() && day==null) || // Có nhập tìm ngày mà day không thể chuyển sang kiểu int
				(!r_month.getText().isBlank() && month==null) ||
				(!r_day.getText().isBlank() && day==null)
				) {
			cancel();
		}
		
		CustomDate date = CustomDate.searchDate(year,month,day);
		receiptResponse = new Receipt(id, employee, customer, date, null);
		thisDialog.dispose();
		return;
	}
	
	private void cancel() {
		receiptResponse = null;
		thisDialog.dispose();
	}
	
	public Receipt getReceiptResponse() {
		return this.receiptResponse;
	}
	
}
