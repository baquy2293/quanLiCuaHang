package gui.account;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.Utils;
import mau.SubDialog;
import model.Customer;
import subclass.CheckingFile;
import subclass.CustomDate;
import subclass.FileTypeFilter;

public class AccountUpdateGUI extends SubDialog {
	private static final long serialVersionUID = 1L;
	//private JFrame owner;
	private AccountUpdateGUI thisDialog = this;
	private Customer customerResponse = null;
	
	private Customer customer;
	private JTextField c_name;
	private JTextField c_dob;
	private JTextArea c_address;
	private JTextField c_phone;
	private JTextField c_email;
	private JLabel c_img;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AccountAddGUI dialog = new AccountAddGUI(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AccountUpdateGUI(JFrame owner, Customer customer) {
		super(owner);
		this.customer = customer;
		setSize(600, 440);
		setLocation(600, 130);
		
		JLabel lblNewLabel_2 = new JLabel("Chỉnh Sửa Thông Tin");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel_2.setBounds(150, 10, 300, 26);
		pane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_1 = new JLabel("Tên Khách Hàng :");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(160, 84, 130, 26);
		pane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Ngày Sinh :");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(160, 124, 120, 26);
		pane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Địa Chỉ :");
		lblNewLabel_1_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_2.setBounds(160, 164, 100, 26);
		pane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Số Điện Thoại :");
		lblNewLabel_1_3.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_3.setBounds(160, 224, 120, 26);
		pane.add(lblNewLabel_1_3);
		
		c_img = this.createDefaultLabelImage(20, 84, 100, 130);
		pane.add(c_img);
		
		c_name = new JTextField();
		c_name.setHorizontalAlignment(SwingConstants.CENTER);
		c_name.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_name.setColumns(10);
		c_name.setBounds(284, 83, 290, 26);
		pane.add(c_name);
		
		c_phone = new JTextField();
		c_phone.setHorizontalAlignment(SwingConstants.CENTER);
		c_phone.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_phone.setColumns(10);
		c_phone.setBounds(284, 224, 290, 26);
		pane.add(c_phone);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Email :");
		lblNewLabel_1_3_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_3_1.setBounds(160, 264, 120, 26);
		pane.add(lblNewLabel_1_3_1);
		
		c_email = new JTextField();
		c_email.setHorizontalAlignment(SwingConstants.CENTER);
		c_email.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_email.setColumns(10);
		c_email.setBounds(284, 264, 290, 26);
		pane.add(c_email);
		
		c_address = this.createDefaultJTextArea(284, 164, 290, 52);
		JScrollPane jsp = this.createDefaultJScrollPane(c_address, 284, 164, 290, 52);
		pane.add(jsp);
		
		c_dob = new JTextField();
		c_dob.setHorizontalAlignment(SwingConstants.CENTER);
		c_dob.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_dob.setColumns(10);
		c_dob.setBounds(284, 124, 290, 26);
		pane.add(c_dob);
		
		JButton c_chooseImgBtn = new JButton("Chọn Ảnh");
		c_chooseImgBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_chooseImgBtn.setBounds(20, 220, 100, 26);
		pane.add(c_chooseImgBtn);
		
		JButton c_addBtn = new JButton("Xác Nhận");
		c_addBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_addBtn.setBounds(50, 360, 130, 30);
		pane.add(c_addBtn);
		
		JButton c_cancel = new JButton("Hủy");
		c_cancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_cancel.setBounds(400, 360, 130, 30);
		pane.add(c_cancel);
		
		/*-------------------- Event ----------------------------------------------------------*/
		c_chooseImgBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<FileTypeFilter> ftf = new ArrayList<FileTypeFilter>();
				ftf.add(new FileTypeFilter("png", "PNG"));
				ftf.add(new FileTypeFilter("jpg", "JPG"));
				String filePath = thisDialog.getFileChooser(thisDialog, ftf, new CheckingFile() {
					@Override
					public boolean check(File f) {
						if(Utils.isImage(f)) {
							return true;
						}
						return false;
					}
				});
				if(filePath!=null) {
					c_img.setIcon(thisDialog.getImageIcon(filePath, 100, 130));
				}
			}
		});
		
		c_addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setValueForCustomerResponse();
			}
		});
		
		c_cancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisDialog.dispose();
			}
		});
		
		setCustomer(this.customer);
	}
	/*
	 * Phương thức Private
	 */
	private void setValueForCustomerResponse() {
		String name = c_name.getText();
		String dob = c_dob.getText();
		String address = c_address.getText();
		String phoneNumber = c_phone.getText();
		String email = c_email.getText();
		boolean isValid = AccountView.checkInputString(thisDialog, null, null, name, dob, address, phoneNumber, email);
		if(isValid) {
			byte[] image = null;
			if(c_img.getIcon()!= null) {
				try {
					image = Utils.toByteArray(Utils.toBufferedImage(c_img.getIcon()) , "png");
				} catch (IOException e) {
					image = null;
				}
			}
			customerResponse = new Customer(customer.getId(), null, name, CustomDate.of(dob), address, phoneNumber, email, image, null);
			thisDialog.dispose();
		}
		
	}
	
	public void setCustomer(Customer c) {
		if(customer.getImage()!=null) {
			try {
				c_img.setIcon(new ImageIcon(Utils.toBufferedImage(customer.getImage())));
			} catch (IOException e) {
				//UpdateGUI Không thể đọc được dữ liệu ảnh từ CSDL
				//JOptionPane.showMessageDialog(thisDialog, "Không thể đọc ảnh từ CSDL lên cửa sổ Update");
			}
		}else {
			
		}
		c_name.setText(customer.getName());
		c_dob.setText(customer.getDob().toString());
		c_address.setText(customer.getAddress());
		c_phone.setText(customer.getPhoneNumber());
		c_email.setText(customer.getEmail());
	}
	
	public Customer getDialogResponse() {
		return customerResponse;
	}
}
