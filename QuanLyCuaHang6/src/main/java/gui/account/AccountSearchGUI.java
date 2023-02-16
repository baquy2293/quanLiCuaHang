package gui.account;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import mau.SubDialog;
import model.Customer;
import subclass.CustomDate;

public class AccountSearchGUI extends SubDialog {

	private static final long serialVersionUID = 1L;
	private AccountSearchGUI thisDialog = this;
	private Customer customerResponse = null;
	private JTextField c_id;
	private JTextField c_name;
	private JTextField c_dob_month;
	private JTextField c_phone;
	private JTextField c_email;
	private JTextField c_score1;
	private JTextField c_score2;
	private JTextField c_dob_year;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccountSearchGUI dialog = new AccountSearchGUI(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccountSearchGUI(JFrame owner) {
		super(owner);
		setSize(480, 400);
		
		JLabel lblNewLabel = new JLabel("Tìm Kiếm Khách Hàng");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 20, 444, 30);
		pane.add(lblNewLabel);
		
		JLabel lblTnKhchHng = new JLabel("Mã Khách Hàng :");
		lblTnKhchHng.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng.setBounds(20, 70, 130, 26);
		pane.add(lblTnKhchHng);
		
		JLabel lblTnKhchHng_1 = new JLabel("Tên Khách Hàng :");
		lblTnKhchHng_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1.setBounds(20, 107, 130, 26);
		pane.add(lblTnKhchHng_1);
		
		JLabel lblTnKhchHng_1_1 = new JLabel("Ngày Sinh :");
		lblTnKhchHng_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1_1.setBounds(20, 144, 130, 26);
		pane.add(lblTnKhchHng_1_1);
		
		JLabel lblTnKhchHng_1_1_1 = new JLabel("Số Điện Thoại :");
		lblTnKhchHng_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1_1_1.setBounds(20, 181, 130, 26);
		pane.add(lblTnKhchHng_1_1_1);
		
		JLabel lblTnKhchHng_1_1_1_1 = new JLabel("Email :");
		lblTnKhchHng_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1_1_1_1.setBounds(20, 218, 130, 26);
		pane.add(lblTnKhchHng_1_1_1_1);
		
		JLabel lblTnKhchHng_1_1_1_1_1 = new JLabel("Điểm Tích Lũy :");
		lblTnKhchHng_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1_1_1_1_1.setBounds(20, 255, 130, 26);
		pane.add(lblTnKhchHng_1_1_1_1_1);
		
		JLabel lblTnKhchHng_1_1_1_1_1_1 = new JLabel("Từ");
		lblTnKhchHng_1_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1_1_1_1_1_1.setBounds(146, 255, 20, 26);
		pane.add(lblTnKhchHng_1_1_1_1_1_1);
		
		JLabel lblTnKhchHng_1_1_1_1_1_1_1 = new JLabel("Đến");
		lblTnKhchHng_1_1_1_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1_1_1_1_1_1_1.setBounds(293, 255, 36, 26);
		pane.add(lblTnKhchHng_1_1_1_1_1_1_1);
		
		c_id = new JTextField();
		c_id.setHorizontalAlignment(SwingConstants.CENTER);
		c_id.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_id.setColumns(10);
		c_id.setBounds(146, 70, 290, 26);
		pane.add(c_id);
		
		c_name = new JTextField();
		c_name.setHorizontalAlignment(SwingConstants.CENTER);
		c_name.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_name.setColumns(10);
		c_name.setBounds(146, 107, 290, 26);
		pane.add(c_name);
		
		c_dob_month = new JTextField();
		c_dob_month.setHorizontalAlignment(SwingConstants.CENTER);
		c_dob_month.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_dob_month.setColumns(10);
		c_dob_month.setBounds(194, 144, 30, 26);
		pane.add(c_dob_month);
		
		c_phone = new JTextField();
		c_phone.setHorizontalAlignment(SwingConstants.CENTER);
		c_phone.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_phone.setColumns(10);
		c_phone.setBounds(146, 181, 183, 26);
		pane.add(c_phone);
		
		c_email = new JTextField();
		c_email.setHorizontalAlignment(SwingConstants.CENTER);
		c_email.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_email.setColumns(10);
		c_email.setBounds(146, 218, 290, 26);
		pane.add(c_email);
		
		c_score1 = new JTextField();
		c_score1.setHorizontalAlignment(SwingConstants.CENTER);
		c_score1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_score1.setColumns(10);
		c_score1.setBounds(176, 255, 107, 26);
		pane.add(c_score1);
		
		c_score2 = new JTextField();
		c_score2.setHorizontalAlignment(SwingConstants.CENTER);
		c_score2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_score2.setColumns(10);
		c_score2.setBounds(329, 255, 107, 26);
		pane.add(c_score2);
		
		JButton c_searchBtn = new JButton("Tìm Kiếm");
		c_searchBtn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		c_searchBtn.setBounds(40, 327, 120, 26);
		pane.add(c_searchBtn);
		
		JButton c_cancel = new JButton("Hủy Bỏ");
		c_cancel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		c_cancel.setBounds(300, 327, 120, 26);
		pane.add(c_cancel);
		
		c_dob_year = new JTextField();
		c_dob_year.setHorizontalAlignment(SwingConstants.CENTER);
		c_dob_year.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_dob_year.setColumns(10);
		c_dob_year.setBounds(271, 144, 58, 26);
		pane.add(c_dob_year);
		
		JLabel lblTnKhchHng_1_1_2 = new JLabel("Tháng");
		lblTnKhchHng_1_1_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1_1_2.setBounds(146, 144, 49, 26);
		pane.add(lblTnKhchHng_1_1_2);
		
		JLabel lblTnKhchHng_1_1_2_1 = new JLabel("Năm");
		lblTnKhchHng_1_1_2_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblTnKhchHng_1_1_2_1.setBounds(234, 144, 49, 26);
		pane.add(lblTnKhchHng_1_1_2_1);
		
		/*-------------------------- Event --------------------------------------------*/
		c_searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				getInput();
			}
		});
		c_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisDialog.dispose();
			}
		});
	}
	
	private Customer getInput() {
		String id = c_id.getText().isBlank() ? null :  c_id.getText();
		String name = c_name.getText().isBlank() ? null : c_name.getText();
		String phoneNumber = c_phone.getText().isBlank() ? null : c_phone.getText();
		String email = c_email.getText().isBlank() ? null : c_email.getText();

		boolean isValid = true;
		Integer month = null, year = null;
		if(!c_dob_month.getText().isBlank()) {
			isValid = checkMonth(c_dob_month.getText());
			if(isValid == true) {month = Integer.parseInt(c_dob_month.getText());}
			else {return null;}
		}
		if(!c_dob_year.getText().isBlank()) {
			isValid = checkYear(c_dob_year.getText());
			if(isValid == true) {year = Integer.parseInt(c_dob_year.getText());}
			else {return null;}
		}
		if(!c_score1.getText().isBlank()) {
			//
		}
		if(isValid) {
			customerResponse = new Customer(id, null, name, CustomDate.searchDate(year, month, null), null, phoneNumber, email, null, 0);
			thisDialog.dispose();
		}
		return null;
	}
	
	public Customer getDialogResponse() {
		return this.customerResponse;
	}
	
	private boolean checkMonth(String m) {
		if(m.matches("[0-9]{1,2}")) {
			int month = Integer.parseInt(m);
			if(month > 0 && month < 13) {
				return true;
			}
		}
		JOptionPane.showMessageDialog(thisDialog, 
				"Tháng không hợp lệ!", 
				"Lỗi", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
	private boolean checkYear(String y) {
		if(y.matches("[0-9]{1-4}")) {
			return true;
		}
		JOptionPane.showMessageDialog(thisDialog, 
				"Năm không hợp lệ!", 
				"Lỗi", JDialog.ERROR);
		return false;
	}
}
