package gui.account;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import gui.Utils;
import mau.PaneMau;
import model.Customer;
import subclass.CustomDate;

public class AccountView extends PaneMau {

	private static final long serialVersionUID = 1L;
	private JFrame owner;
	private JTextField c_id;
	private JLabel c_user;
	private JTextField c_name;
	private JTextField c_dob;
	private JTextField c_address;
	private JTextField c_phone;
	private JTextField c_email;
	private JLabel c_image;
	private JTextField c_score;

	private JTable customerTable;
	private CustomerTableModel customerTableModel;
	private JTextField c_userSearch;
	private JButton c_search;
	private JButton c_add;
	private JButton c_reload;
	private JButton c_remove1;
	private JButton c_update;
	private JButton c_remove;

	public AccountView(JFrame owner) {
		this.owner = owner;
		JLabel lblDanh = new JLabel("Danh Sách Khách Hàng");
		lblDanh.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanh.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblDanh.setBounds(140, 14, 240, 30);
		pane.add(lblDanh);

		JPanel c_info = new JPanel();
		c_info.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		c_info.setBackground(new Color(240, 240, 240));
		c_info.setBounds(600, 56, 360, 460);

		pane.add(c_info);
		c_info.setLayout(null);

		JLabel lblDanh_1 = new JLabel("Thông Tin Khách Hàng");
		lblDanh_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblDanh_1.setFont(new Font("SansSerif", Font.PLAIN, 20));
		lblDanh_1.setBounds(672, 11, 240, 30);
		pane.add(lblDanh_1);

		JLabel lblNewLabel_2_1 = new JLabel("Ngày Sinh:");
		lblNewLabel_2_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_2_1.setBounds(10, 290, 120, 24);
		c_info.add(lblNewLabel_2_1);

		JLabel lblNewLabel_2_1_1 = new JLabel("Địa Chỉ:");
		lblNewLabel_2_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_2_1_1.setBounds(10, 325, 120, 24);
		c_info.add(lblNewLabel_2_1_1);

		JLabel lblNewLabel_2_1_1_1 = new JLabel("Tên Khách Hàng:");
		lblNewLabel_2_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1.setBounds(10, 260, 120, 24);
		c_info.add(lblNewLabel_2_1_1_1);

		JLabel lblNewLabel_2_1_1_1_1 = new JLabel("Số Điện Thoại:");
		lblNewLabel_2_1_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_1.setBounds(10, 365, 120, 24);
		c_info.add(lblNewLabel_2_1_1_1_1);

		JLabel lblNewLabel_2_1_1_1_2 = new JLabel("Email:");
		lblNewLabel_2_1_1_1_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2.setBounds(10, 394, 120, 24);
		c_info.add(lblNewLabel_2_1_1_1_2);

		JLabel lblNewLabel_2_2 = new JLabel("Mã Khách Hàng:");
		lblNewLabel_2_2.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_2_2.setBounds(10, 220, 120, 24);
		c_info.add(lblNewLabel_2_2);

		JLabel lblNewLabel_2_1_1_1_2_1 = new JLabel("Điểm Tích Lũy:");
		lblNewLabel_2_1_1_1_2_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_2_1_1_1_2_1.setBounds(10, 429, 120, 24);
		c_info.add(lblNewLabel_2_1_1_1_2_1);

		JLabel lblNewLabel = new JLabel("User:");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 51, 50, 30);
		pane.add(lblNewLabel);

		/*
		 * --------------------------- Phần nội dung
		 * -----------------------------------------------------------------
		 */
		customerTableModel = new CustomerTableModel();
		customerTable = new JTable();
		customerTable.setBounds(10, 93, 565, 423);
		customerTable.setFont(new Font("SansSerif", Font.PLAIN, 16));
		customerTable.setRowHeight(28);
		JTableHeader listCustomerHeader = customerTable.getTableHeader();
		listCustomerHeader.setFont(new Font("SansSerif", Font.BOLD, 18));
		listCustomerHeader.setBackground(Color.yellow);
		JScrollPane scrollPane = new JScrollPane(customerTable);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(10, 93, 565, 423);
		customerTable.setTableHeader(listCustomerHeader);
		customerTable.setModel(customerTableModel);
		customerTable.getColumnModel().removeColumn(customerTable.getColumnModel().getColumn(8));
		customerTable.getColumnModel().removeColumn(customerTable.getColumnModel().getColumn(7));
		customerTable.getColumnModel().removeColumn(customerTable.getColumnModel().getColumn(1));
		customerTable.getColumnModel().removeColumn(customerTable.getColumnModel().getColumn(0));
		pane.add(scrollPane);

		c_image = new JLabel();
		c_image.setHorizontalAlignment(SwingConstants.CENTER);
		c_image.setBounds(141, 45, 100, 130);
		c_info.add(c_image);

		JLabel c_background = new JLabel("");
		c_background.setHorizontalAlignment(SwingConstants.CENTER);
		c_background.setIcon(Utils.getImageIcon("src/main/resources/img/background.png", 360, 240));
		c_background.setBounds(1, 1, 358, 164);
		c_info.add(c_background);

		c_user = new JLabel();
		c_user.setHorizontalAlignment(SwingConstants.CENTER);
		c_user.setFont(new Font("SansSerif", Font.BOLD, 16));
		c_user.setBounds(141, 176, 100, 24);
		c_info.add(c_user);

		c_id = new JTextField();
		c_id.setEditable(false);
		c_id.setHorizontalAlignment(SwingConstants.CENTER);
		c_id.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c_id.setBounds(133, 220, 217, 24);
		c_info.add(c_id);
		c_id.setColumns(10);

		c_name = new JTextField();
		c_name.setEditable(false);
		c_name.setHorizontalAlignment(SwingConstants.CENTER);
		c_name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c_name.setColumns(10);
		c_name.setBounds(133, 260, 217, 24);
		c_info.add(c_name);

		c_address = new JTextField();
		c_address.setEditable(false);
		c_address.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c_address.setColumns(10);
		c_address.setBounds(133, 325, 217, 24);
		c_info.add(c_address);

		c_phone = new JTextField();
		c_phone.setEditable(false);
		c_phone.setHorizontalAlignment(SwingConstants.CENTER);
		c_phone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c_phone.setColumns(10);
		c_phone.setBounds(133, 365, 217, 24);
		c_info.add(c_phone);

		c_email = new JTextField();
		c_email.setEditable(false);
		c_email.setHorizontalAlignment(SwingConstants.CENTER);
		c_email.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c_email.setColumns(10);
		c_email.setBounds(133, 394, 217, 24);
		c_info.add(c_email);

		c_score = new JTextField();
		c_score.setEditable(false);
		c_score.setHorizontalAlignment(SwingConstants.CENTER);
		c_score.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c_score.setColumns(10);
		c_score.setBounds(133, 429, 109, 24);
		c_info.add(c_score);

		c_dob = new JTextField();
		c_dob.setEditable(false);
		c_dob.setHorizontalAlignment(SwingConstants.CENTER);
		c_dob.setFont(new Font("Tahoma", Font.PLAIN, 14));
		c_dob.setColumns(10);
		c_dob.setBounds(133, 294, 217, 24);
		c_info.add(c_dob);

		c_userSearch = new JTextField();
		c_userSearch.setHorizontalAlignment(SwingConstants.CENTER);
		c_userSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		c_userSearch.setColumns(10);
		c_userSearch.setBounds(60, 51, 385, 30);
		pane.add(c_userSearch);

		c_search = new JButton("Nâng Cao");
		c_search.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_search.setBounds(455, 52, 120, 30);
		pane.add(c_search);

		c_add = new JButton("Thêm");
		c_add.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_add.setBounds(10, 527, 180, 30);
		pane.add(c_add);

		c_reload = new JButton("Tải Lại");
		c_reload.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_reload.setBounds(200, 527, 180, 30);
		pane.add(c_reload);

		c_remove1 = new JButton("Xóa");
		c_remove1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_remove1.setBounds(395, 527, 180, 30);
		pane.add(c_remove1);

		c_remove = new JButton("Xóa");
		c_remove.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_remove.setBounds(796, 527, 164, 30);
		pane.add(c_remove);

		c_update = new JButton("Chỉnh sửa");
		c_update.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_update.setBounds(600, 527, 164, 30);
		pane.add(c_update);

		JButton goBack = new JButton("Trở Về");
		goBack.setFont(new Font("SansSerif", Font.BOLD, 18));
		goBack.setBounds(830, 610, 126, 40);
		pane.add(goBack);

		/*--------------------------------- Add Event -----------------------------------*/
		customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				setCustomerInfo();
			}
		});
	}

	public void addCustomerAutoSearchUserListener(DocumentListener dl) {
		c_userSearch.getDocument().addDocumentListener(dl);
	}

	public void addCustomerSearchUserListener(KeyAdapter ka) {
		c_userSearch.addKeyListener(ka);
	}

	public void addCustomerSearchListener(ActionListener listener) {
		c_search.addActionListener(listener);
	}

	public void addCustomerAddListener(ActionListener listener) {
		c_add.addActionListener(listener);
	}
	
	public void addCustomerReloadListener(MouseAdapter ma) {
		c_reload.addMouseListener(ma);
	}
	
	public void addCustomerUpdateListener(ActionListener listener) {
		c_update.addActionListener(listener);
	}

	public void addCustomerRemoveListener(ActionListener listener) {
		c_remove.addActionListener(listener);
		c_remove1.addActionListener(listener);
	}

	public void showListCustomer(List<Customer> customerList) {
		customerTableModel.setData(customerList);
	}

	public Customer showSearchGUI(JFrame owner) {
		AccountSearchGUI searchGUI = new AccountSearchGUI(owner);
		searchGUI.setVisible(true);
		Customer customerResponse = searchGUI.getDialogResponse();
		return customerResponse;
	}

	public Customer showAddGUI(JFrame owner) {
		AccountAddGUI addGUI = new AccountAddGUI(owner);
		addGUI.setVisible(true);
		Customer customerResponse = addGUI.getDialogResponse();
		return customerResponse;
	}

	public Customer showUpdateGUI(JFrame owner) {
		Customer customer = getSelectedCustomer();
		if (customer != null) {
			AccountUpdateGUI updateGUI = new AccountUpdateGUI(owner, customer);
			updateGUI.setVisible(true);
			Customer customerResponse = updateGUI.getDialogResponse();
			return customerResponse;
		}
		return null;
	}

	public String showRemoveGUI() {
		int row = customerTable.getSelectedRow();
		String user = (String) customerTableModel.getValueAt(row, 1);
		int result = JOptionPane.showConfirmDialog(owner, "Bạn có chắc là muốn xóa user " + user + " ?",
				"Xác thực muốn xóa user", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		if (result == JOptionPane.YES_OPTION) {
			return (String) customerTableModel.getValueAt(row, 0);
		}
		return null;
	}

	/*
	 * Phương thức Default
	 */
	// Để kiểm tra dữ liệu Customer
	final static boolean checkInputString(JDialog owner, String id, String user, String name, String dob,
			String address, String phoneNumber, String email) {
		if (id != null) {
			if (!id.matches("KH[a-zA-Z0-9]{8}")) {
				JOptionPane.showMessageDialog(owner,
						"Id khách hàng không hợp lệ! \nId bao gồm từ \"KH\" đi với 8 mã số như KH00000000!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if (user != null) {

			if (!user.matches("[a-zA-Z0-9@[.]]+")) {
				JOptionPane.showMessageDialog(owner,
						"Id khách hàng không hợp lệ! \nId bao gồm từ \"KH\" đi với 8 mã số như KH00000000!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if (name != null) {
			if (!name.matches("[\\p{L}\\p{N}\\s]+")) {
				JOptionPane.showMessageDialog(owner, "Tên khách hàng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if (dob != null) {
			if (!dob.matches("[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}") || !CustomDate.isValidDate(CustomDate.of(dob))) {
				JOptionPane.showMessageDialog(owner, "Ngày sinh không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if (address != null) {
			if (address.length() >= 200) {
				JOptionPane.showMessageDialog(owner, "Địa chỉ quá 200 từ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if (phoneNumber != null) {
			if (!phoneNumber.matches("0[0-9]{9}")) {
				JOptionPane.showMessageDialog(owner, "Số điện thoại không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if (email != null) {
			if (!email.matches("[\\w]+@[a-z]{2,}[.][a-z]{2,}([.][a-z]{2,})?")) {
				JOptionPane.showMessageDialog(owner, "Email không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}

	/*
	 * Phương thức private sử dụng trong nội bộ
	 */
	// Hàm lấy dữ liệu từ 1 row trả về đối tượng Customer
	private Customer getSelectedCustomer() {
		int selectedRow = customerTable.getSelectedRow(); // Sử dụng table để lấy dòng thứ i
		if (selectedRow != -1) { // Từ dòng thứ i ta dụng tableModel để trả về đối tượng Customer dòng thứ i
			return customerTableModel.get(selectedRow);
		} else {
			return null;
		}
	}

	// Lấy dữ liệu 1 row + render nó lên phần thông tin khách hàng bên phải
	private void setCustomerInfo() {
		Customer c = getSelectedCustomer();
		if (c != null) {
			if (c.getImage() != null) {
				try {
					c_image.setIcon(new ImageIcon(Utils.toBufferedImage(c.getImage())));
				} catch (IOException e) {
					c_image.setIcon(Utils.getImageIcon("src/main/resources/img/DefaultImage.png", 100, 130));
				}
			} else {
				c_image.setIcon(Utils.getImageIcon("src/main/resources/img/DefaultImage.png", 100, 130));
			}

			c_id.setText(c.getId());
			c_user.setText(c.getUser());
			c_name.setText(c.getName());
			c_dob.setText(c.getDob().toString());
			c_address.setText(c.getAddress());
			c_phone.setText(c.getPhoneNumber());
			c_email.setText(c.getEmail());
			c_score.setText(String.valueOf(c.getScore()));
		}
	}
}
