package gui.product;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.Utils;
import mau.SubDialog;

public class SubSearchProduct extends SubDialog {

	private static final long serialVersionUID = 1L;
	private JTextField p_id;
	private JTextField p_name;
	//private JTable productTable;
	private JTable cartTable;

	public static void main(String[] args) {
		try {
			SubSearchProduct dialog = new SubSearchProduct(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SubSearchProduct(JFrame owner) {
		super(owner);
		setSize(500, 700);
		JLabel lblNewLabel = new JLabel("Tìm Kiếm Sản Phẩm");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 464, 30);
		pane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mã Sản Phẩm:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(20, 52, 100, 30);
		pane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Tên Sản Phẩm:");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(20, 82, 100, 30);
		pane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Loại Sản Phẩm:");
		lblNewLabel_1_1_1.setFont(new Font("SansSerif", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(20, 112, 100, 30);
		pane.add(lblNewLabel_1_1_1);
		
		p_id = new JTextField();
		p_id.setHorizontalAlignment(SwingConstants.CENTER);
		p_id.setBounds(130, 55, 280, 24);
		pane.add(p_id);
		p_id.setColumns(10);
		
		p_name = new JTextField();
		p_name.setHorizontalAlignment(SwingConstants.CENTER);
		p_name.setColumns(10);
		p_name.setBounds(130, 85, 280, 24);
		pane.add(p_name);
		
		JComboBox<String> p_type = new JComboBox<String>(new DefaultComboBoxModel<String>(ProductView.TYPES));
		p_type.addItem(ProductView.anyType);
		p_type.setSelectedItem(ProductView.anyState);
		p_type.setFont(new Font("SansSerif", Font.PLAIN, 16));
		p_type.setBounds(130, 115, 160, 24);
		pane.add(p_type);
		
		JTable productTable= this.createDefaultJTable(20, 153, 454, 200);
		JScrollPane productTableScroll = this.createDefaultJScrollPane(productTable, 20, 153, 454, 200);
		pane.add(productTableScroll);
		
		cartTable = this.createDefaultJTable(20, 434, 454, 120);
		JScrollPane cartTableScroll = this.createDefaultJScrollPane(cartTable, 20, 434, 454, 120);
		pane.add(cartTableScroll);
		
		JButton p_toCart = new JButton("");
		p_toCart.setIcon(Utils.getImageIcon("src/main/resources/logo/ToCart.png", 50, 50));
		p_toCart.setBounds(100, 363, 60, 60);
		pane.add(p_toCart);
		
		JButton p_takeOutCart = new JButton("");
		p_takeOutCart.setBounds(320, 363, 60, 60);
		p_takeOutCart.setIcon(Utils.getImageIcon("src/main/resources/logo/OutCart.png", 60, 60));
		pane.add(p_takeOutCart);
		
		JButton c_confirm = new JButton("Xác Nhận");
		c_confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_confirm.setBounds(20, 620, 120, 30);
		pane.add(c_confirm);
		
		JButton c_cancel = new JButton("Hủy Bỏ");
		c_cancel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_cancel.setBounds(354, 620, 120, 30);
		pane.add(c_cancel);
		
		JButton p_searchBtn = new JButton("");
		p_searchBtn.setIcon(Utils.getImageIcon("src/main/resources/logo/search.png", 60, 60));
		p_searchBtn.setBounds(414, 52, 60, 60);
		pane.add(p_searchBtn);
	}
	
	
}
