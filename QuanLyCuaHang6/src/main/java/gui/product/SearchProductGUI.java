package gui.product;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import mau.SubDialog;
import model.Product;

public class SearchProductGUI extends SubDialog {
	private static final long serialVersionUID = 1L;
	private SearchProductGUI thisDialog = this;
	private JTextField p_id;
	private JTextField p_name;
	private JComboBox<String> p_state;
	private JComboBox<String> p_type;
	private JTextField p_price;
	private JTextField p_quantity;
	private ButtonGroup p_image;
	private Product productResponse;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			SearchProductGUI dialog = new SearchProductGUI(new JFrame());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SearchProductGUI(JFrame owner) {
		super(owner);
		setSize(600, 360);
//		setLocation(getX()+100, getY()+120);
		setLocation(600, 240);
		JLabel title = this.createDefaultHeader("Tìm Kiếm Sản Phẩm", 200, 10);
		pane.add(title);
		
		JLabel lblNewLabel = new JLabel("Tên Sản Phẩm:");
		lblNewLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel.setBounds(20, 100, 120, 30);
		pane.add(lblNewLabel);
		
		JLabel lblMSnPhm = new JLabel("Mã Sản Phẩm:");
		lblMSnPhm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblMSnPhm.setBounds(20, 60, 120, 30);
		pane.add(lblMSnPhm);
		
		JLabel lblnGi = new JLabel("Đơn Giá Từ:");
		lblnGi.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblnGi.setBounds(20, 180, 120, 30);
		pane.add(lblnGi);
		
		JLabel lblSLng = new JLabel("Số Lượng Ít Hơn:");
		lblSLng.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblSLng.setBounds(296, 180, 123, 30);
		pane.add(lblSLng);
		
		JLabel lblCHnhnh = new JLabel("Hình Ảnh:");
		lblCHnhnh.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblCHnhnh.setBounds(20, 140, 120, 30);
		pane.add(lblCHnhnh);
		
		p_id = this.createDefaultJTextField(132, 62, 280, 26);
		pane.add(p_id);
		
		p_name = this.createDefaultJTextField(132, 102, 280, 26);
		pane.add(p_name);
		
		
		p_state = new JComboBox<String>();
		p_state.setModel(new DefaultComboBoxModel<>(ProductView.STATES));
		p_state.addItem(ProductView.anyState);
		p_state.setSelectedItem(ProductView.anyState);
		this.decorateJComboBox(p_state, 420, 62, 140, 26);
		pane.add(p_state);
		
		p_type = new JComboBox<>();
		p_type.setModel(new DefaultComboBoxModel<>(ProductView.TYPES));
		p_type.addItem(ProductView.anyType);
		p_type.setSelectedItem(ProductView.anyType);
		this.decorateJComboBox(p_type, 420, 102, 140, 26);
		pane.add(p_type);
		
		
		JRadioButton image_yes = this.createDefaultJRadioButton("Có", 132, 142, 90, 26, false);
		pane.add(image_yes);
		
		JRadioButton image_no = this.createDefaultJRadioButton("Không", 230, 142, 90, 26, false);
		pane.add(image_no);
		
		JRadioButton image_both =this.createDefaultJRadioButton("Cả Hai", 322, 142, 90, 26, true);
		pane.add(image_both);
		
		p_image = new ButtonGroup();
		p_image.add(image_yes);
		p_image.add(image_no);
		p_image.add(image_both);
		
		p_price = this.createDefaultJTextField(132, 182, 150, 26);
		pane.add(p_price);
		
		p_quantity = this.createDefaultJTextField(420, 182, 140, 26);
		pane.add(p_quantity);
		
		JButton searchBtn = this.createDefaultJButton("Tìm Kiếm", 90, 280, 120, 30);
		searchBtn.addMouseListener(new SearchButtonPressHandler());
		pane.add(searchBtn);
		
		JButton goBackBtn =this.createDefaultJButton("Trở Lại", 370, 280, 120, 30);
		goBackBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				productResponse = null;
				dispose();
			}
		});
		pane.add(goBackBtn);
	}
	
	private class SearchButtonPressHandler extends MouseAdapter{
		@Override
		public void mouseClicked(MouseEvent e) {
			String id = p_id.getText().isBlank() ? null : p_id.getText();
			String name = p_name.getText().isBlank() ? null : p_name.getText();
			String type = p_type.getSelectedItem().toString().equals(ProductView.anyType) ? null : p_type.getSelectedItem().toString();
			String price = p_price.getText().isBlank() ? null : p_price.getText();
			String quantity = p_quantity.getText().isBlank() ? null : p_quantity.getText();
			String state = p_state.getSelectedItem().toString().equals(ProductView.anyState) ? null : p_state.getSelectedItem().toString();
			boolean isValid = ProductView.checkInputString(thisDialog, null, name, type, price, quantity, state, null, null);
			if(isValid) {
				Float priceFloat = price == null ? null : Float.parseFloat(price);
				Integer quantityInt = quantity == null ? null : Integer.parseInt(quantity);
				productResponse = new Product(id, name, type, priceFloat, quantityInt,
						null, state, null);
				thisDialog.dispose();
			}
		}
	}
	public Product getProductResponse() {
		return productResponse;
	}
}
