package gui.product;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import gui.Utils;
import mau.SubDialog;
import model.Product;
import subclass.CheckingFile;
import subclass.FileTypeFilter;

public class UpdateProductGUI extends SubDialog {
	private static final long serialVersionUID = 1L;

	private UpdateProductGUI thisDialog = this;
	private String productId;
	private JLabel p_image;
	private JTextField p_name;
	private JComboBox<String> p_type;
	private JTextField p_price;
	private JTextField p_quantity;
	private JComboBox<String> p_state;
	private JTextArea p_description;
	private Product productResponse;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			UpdateProductGUI<Object> dialog = new UpdateProductGUI<Object>(new Object());
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	public UpdateProductGUI(JFrame owner, Product product) {
		super(owner);
		setSize(600, 460);
//		setLocation(getX()+100, getY()+70);
		setLocation(600, 140);
		productId = product.getId();
		
		System.out.println("X= " + (getX() + 100) + "Y= " + (getY()+70));
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(184, 11, 85, 22);
		JLabel title = this.createDefaultHeader("Cập Nhật Sản Phẩm", 200, 10);
		pane.add(title);

		JLabel lblNewLabel_1 = new JLabel("Tên Sản Phẩm:");
		lblNewLabel_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(160, 50, 120, 30);
		pane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Loại Sản Phẩm:");
		lblNewLabel_1_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1.setBounds(160, 110, 120, 30);
		pane.add(lblNewLabel_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Đơn GIá:");
		lblNewLabel_1_1_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_2.setBounds(160, 150, 120, 30);
		pane.add(lblNewLabel_1_1_2);

		JLabel lblNewLabel_1_1_2_1 = new JLabel("Số Lượng:");
		lblNewLabel_1_1_2_1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_1_1_2_1.setBounds(160, 190, 120, 30);
		pane.add(lblNewLabel_1_1_2_1);

		JLabel lblNewLabel_2 = new JLabel("Mô Tả:");
		lblNewLabel_2.setFont(new Font("SansSerif", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(160, 231, 120, 30);
		pane.add(lblNewLabel_2);

		p_image = this.createDefaultJLabel(20, 50, 130, 130);
		if (product.getImage() != null) {
			try {
				p_image.setIcon(new ImageIcon(Utils.toBufferedImage(product.getImage())));
			} catch (IOException e1) {
				//Không đọc được file ảnh từ CSDL
			}
		}
		pane.add(p_image);

		p_name = this.createDefaultJTextField(274, 50, 300, 30);
		p_name.setText(product.getName());
		pane.add(p_name);

		p_price = this.createDefaultJTextField(274, 150, 300, 30);
		p_price.setText(String.format("%.2f", product.getPrice()));
		pane.add(p_price);

		p_quantity = this.createDefaultJTextField(274, 190, 300, 30);
		p_quantity.setText(String.format("%d", product.getQuantity()));
		pane.add(p_quantity);

		p_type = new JComboBox<String>();
		this.decorateJComboBox(p_type, 274, 110, 200, 30);
		p_type.setModel(new DefaultComboBoxModel<String>(ProductView.TYPES));
		p_type.setSelectedItem(product.getType());
		pane.add(p_type);

		JButton p_chooseImgBtn = this.createDefaultJButton("Chọn Ảnh", 20, 190, 130, 30);
		p_chooseImgBtn.addMouseListener(new ChooseImageBtnHandler());
		pane.add(p_chooseImgBtn);

		p_state = new JComboBox<String>();
		this.decorateJComboBox(p_state, 20, 230, 130, 30);
		p_state.setModel(new DefaultComboBoxModel<String>(ProductView.STATES));
		p_state.setSelectedItem(product.getState());
		pane.add(p_state);

		p_description = this.createDefaultJTextArea(274, 230, 300, 100);
		JScrollPane scrollDescription = this.createDefaultJScrollPane(p_description, 274, 230, 300, 100);
		p_description.setText(product.getDescription());
		pane.add(scrollDescription);

		JButton updateProduct = this.createDefaultJButton("Cập Nhật", 80, 380, 120, 30);
		pane.add(updateProduct);
		updateProduct.addMouseListener(new UpdateButtonPressHandler());

		JButton goBack = this.createDefaultJButton("Trở Lại", 414, 380, 100, 34);
		pane.add(goBack);
		goBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				productResponse = null;
				dispose();
			}
		});
	}

	private class UpdateButtonPressHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			String name = p_name.getText();
			String type = p_type.getSelectedItem().toString();
			String price = p_price.getText();
			String quantity = p_quantity.getText();
			String state = p_state.getSelectedItem().toString();
			String desc = p_description.getText();
			boolean isValid = ProductView.checkInputString(thisDialog, null, name, type, price, quantity, state, null,
					desc);
			if (isValid) {
				byte[] byteImg=null;
				if(p_image.getIcon()!=null) {
					try {
						byteImg = Utils.toByteArray(Utils.toBufferedImage(p_image.getIcon()), "png");
					} catch (IOException e1) {
						byteImg = null;
						e1.printStackTrace();
					}
				}
				productResponse = new Product(productId, name, type, Float.parseFloat(price),
						Integer.parseInt(quantity), byteImg, state, desc);
				thisDialog.dispose();
			}
		}
	}

	private class ChooseImageBtnHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			ArrayList<FileTypeFilter> ftf = new ArrayList<FileTypeFilter>();
			ftf.add(new FileTypeFilter(".png", "PNG"));
			ftf.add(new FileTypeFilter(".gif", "GIF"));
			ftf.add(new FileTypeFilter(".jpg", "JPG"));
			String filePath = thisDialog.getFileChooser(thisDialog, ftf, new CheckingFile() {
				@Override
				public boolean check(File f) {
					if (Utils.isImage(f)) {
						return true;
					}
					return false;
				}
			});
			if (filePath != null) {
				p_image.setIcon(new ImageIcon(Utils.toBufferedImage(Utils.getImage(filePath, 130, 130))));
			}
		}
	}

	public Product getDialogResponse() {
		return productResponse;
	}
}
