package gui.employee;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import mau.PaneMau;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class EmployeeGUI extends PaneMau {
	private static final long serialVersionUID = 1L;
	private JTable e_employeeList;
	private JTextField e_name;
	private JComboBox e_position;
	private JTextField e_dob;
	private JTextArea e_address;
	private JTextField c_phone;
	private JTextField c_email;
	private JTextField e_salary;
	private JTextField e_quitDate;
	private JTextField e_search;

	/**
	 * Create the panel.
	 */
	
	public static void main(String args[]) {
		JFrame f = new JFrame();
		EmployeeGUI gui = new EmployeeGUI();
		f.setBounds(100,40, 980, 640);
		f.getContentPane().add(gui);
		f.setVisible(true);
	}
	
	public EmployeeGUI() {
		
		JLabel lblNewLabel = new JLabel("Danh Sách Nhân Viên");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(30, 40, 540, 30);
		pane.add(lblNewLabel);
		
		e_employeeList = this.createDefaultJTable(30, 123, 500, 457);
		JScrollPane tableScrollPane = this.createDefaultJScrollPane(e_employeeList, 30, 123, 500, 457);
		pane.add(tableScrollPane);
		
		JPanel panel = new JPanel();
		panel.setBounds(540, 81, 430, 462);
		pane.add(panel);
		panel.setLayout(null);
		
		JLabel e_image = new JLabel("New label");
		e_image.setBounds(10, 42, 100, 130);
		panel.add(e_image);
		
		JLabel e_id = new JLabel("NV00000001");
		e_id.setHorizontalAlignment(SwingConstants.CENTER);
		e_id.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_id.setBounds(120, 42, 260, 30);
		panel.add(e_id);
		
		e_name = new JTextField();
		e_name.setHorizontalAlignment(SwingConstants.CENTER);
		e_name.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_name.setColumns(10);
		e_name.setBounds(120, 142, 280, 30);
		panel.add(e_name);
		
		e_dob = new JTextField();
		e_dob.setHorizontalAlignment(SwingConstants.CENTER);
		e_dob.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_dob.setColumns(10);
		e_dob.setBounds(120, 182, 120, 30);
		panel.add(e_dob);
		
		JButton e_newImage = new JButton("Ảnh");
		e_newImage.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_newImage.setBounds(10, 184, 89, 26);
		panel.add(e_newImage);
		
		e_position = new JComboBox();
		e_position.setBounds(250, 182, 150, 30);
		panel.add(e_position);
		
		c_phone = new JTextField();
		c_phone.setHorizontalAlignment(SwingConstants.CENTER);
		c_phone.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_phone.setColumns(10);
		c_phone.setBounds(120, 242, 280, 30);
		panel.add(c_phone);
		
		c_email = new JTextField();
		c_email.setHorizontalAlignment(SwingConstants.CENTER);
		c_email.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_email.setColumns(10);
		c_email.setBounds(120, 283, 280, 30);
		panel.add(c_email);
		
		JLabel lblNewLabel_2_2 = new JLabel("Điện Thoại :");
		lblNewLabel_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2_2.setBounds(10, 242, 94, 30);
		panel.add(lblNewLabel_2_2);
		
		JLabel lblNewLabel_2_2_1 = new JLabel("Email :");
		lblNewLabel_2_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2_2_1.setBounds(10, 283, 94, 30);
		panel.add(lblNewLabel_2_2_1);
		
		JLabel lblNewLabel_2_2_1_1 = new JLabel("Địa Chỉ :");
		lblNewLabel_2_2_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2_2_1_1.setBounds(10, 324, 94, 30);
		panel.add(lblNewLabel_2_2_1_1);
		

		e_address = this.createDefaultJTextArea(120, 324, 280, 78);
		JScrollPane addressScrollPane = this.createDefaultJScrollPane(e_address);
		panel.add(addressScrollPane);
		
		JLabel lblNewLabel_2_2_1_1_1 = new JLabel("Lương :");
		lblNewLabel_2_2_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2_2_1_1_1.setBounds(10, 421, 94, 30);
		panel.add(lblNewLabel_2_2_1_1_1);
		
		e_salary = new JTextField();
		e_salary.setHorizontalAlignment(SwingConstants.CENTER);
		e_salary.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_salary.setColumns(10);
		e_salary.setBounds(216, 422, 184, 30);
		panel.add(e_salary);
		
		e_quitDate = new JTextField();
		e_quitDate.setForeground(Color.BLACK);
		e_quitDate.setBackground(Color.GREEN);
		e_quitDate.setText("Đang Làm");
		e_quitDate.setHorizontalAlignment(SwingConstants.CENTER);
		e_quitDate.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
		e_quitDate.setColumns(10);
		e_quitDate.setBounds(0, 0, 184, 30);
		panel.add(e_quitDate);
		
		JLabel e_user = new JLabel("Jokerxtrem");
		e_user.setBackground(Color.BLACK);
		e_user.setHorizontalAlignment(SwingConstants.CENTER);
		e_user.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_user.setBounds(120, 100, 260, 30);
		panel.add(e_user);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(540, 272, 115, 30);
		pane.add(lblNewLabel_2_1);
		
		JButton e_addBtn = new JButton("Thêm");
		e_addBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_addBtn.setBounds(540, 554, 89, 26);
		pane.add(e_addBtn);
		
		JButton e_removeBtn = new JButton("Xóa");
		e_removeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		e_removeBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_removeBtn.setBounds(881, 554, 89, 26);
		pane.add(e_removeBtn);
		
		JButton e_editBtn = new JButton("Sửa");
		e_editBtn.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_editBtn.setBounds(639, 554, 89, 26);
		pane.add(e_editBtn);
		
		e_search = new JTextField();
		e_search.setHorizontalAlignment(SwingConstants.CENTER);
		e_search.setFont(new Font("SansSerif", Font.PLAIN, 16));
		e_search.setColumns(10);
		e_search.setBounds(125, 81, 302, 30);
		pane.add(e_search);
		
		JLabel lblNewLabel_2_2_2 = new JLabel("Tìm Kiếm :");
		lblNewLabel_2_2_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_2_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2_2_2.setBounds(30, 81, 94, 30);
		pane.add(lblNewLabel_2_2_2);
		
		JButton e_advancedSearchBtn = new JButton("Nâng Cao");
		e_advancedSearchBtn.setFont(new Font("SansSerif", Font.PLAIN, 14));
		e_advancedSearchBtn.setBounds(431, 81, 99, 31);
		pane.add(e_advancedSearchBtn);
		
	}
}
