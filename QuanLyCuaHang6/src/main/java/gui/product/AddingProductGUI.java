package gui.product;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.Utils;
import mau.SubDialog;
import model.Product;
import subclass.FileTypeFilter;

public class AddingProductGUI extends SubDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// private JFrame parent;
	private AddingProductGUI thisDialog = this;
	private JTextField productId;
	private JTextField productName;
	private JComboBox<String> productType;
	private JTextField productPrice;
	private JTextField productQuantity;
	private JTextField imgSrc;
	private JComboBox<String> productState;
	private JTextArea description;
	private Product productResponse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddingProductGUI dialog = new AddingProductGUI(new JFrame());
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the Dialog.
	 */
	public AddingProductGUI(JFrame owner) { // product nay moi chi khoi tao, chua co du lieu
		super(owner);
//		setLocation(getX() + 100, getY());
//		System.out.println("X= " + (getX() + 100) + " Y= " + (getY()));
		this.fontSize = 18;
		setLocation(600, 130);
		JLabel title = this.createDefaultHeader("Thêm Sản Phẩm", 200, 10);
		pane.add(title);
		
		JLabel lblNewLabel = this.createDefaultJLabel("Tên Sản Phẩm" ,20, 60, 130, 30);
		pane.add(lblNewLabel);

		JLabel lblMSnPhm = new JLabel("Mã Sản Phẩm:");
		lblMSnPhm.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblMSnPhm.setBounds(20, 100, 130, 30);
		pane.add(lblMSnPhm);

		JLabel lblLoiSnPhm = new JLabel("Hình Ảnh:");
		lblLoiSnPhm.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblLoiSnPhm.setBounds(20, 140, 130, 30);
		pane.add(lblLoiSnPhm);

		JLabel lblHnhnh = new JLabel("Loại Sản Phẩm:");
		lblHnhnh.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblHnhnh.setBounds(20, 180, 130, 30);
		pane.add(lblHnhnh);

		JLabel lblnGi = new JLabel("Đơn Giá:");
		lblnGi.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblnGi.setBounds(20, 220, 130, 30);
		pane.add(lblnGi);

		JLabel lblSLng = new JLabel("Số Lượng:");
		lblSLng.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblSLng.setBounds(20, 260, 130, 30);
		pane.add(lblSLng);

		JLabel lblTrngThi = new JLabel("Trạng Thái:");
		lblTrngThi.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblTrngThi.setBounds(20, 300, 130, 30);
		pane.add(lblTrngThi);

		JLabel lblTrngThi_1 = new JLabel("Mô Tả:");
		lblTrngThi_1.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblTrngThi_1.setBounds(20, 340, 130, 30);
		pane.add(lblTrngThi_1);

		productName = this.createDefaultJTextField(150, 60, 400, 30);
		productName.setHorizontalAlignment(SwingConstants.LEFT);
		pane.add(productName);
		
		productId = this.createDefaultJTextField(150, 100, 400, 30);
		productId.setHorizontalAlignment(SwingConstants.LEFT);
		pane.add(productId);

		imgSrc = this.createDefaultJTextField(150, 140, 300, 30);
		imgSrc.setHorizontalAlignment(SwingConstants.LEFT);
		imgSrc.setEditable(false);
		pane.add(imgSrc);

		JButton chooseImageButton = this.createDefaultJButton("Chọn Ảnh", 448, 140, 102, 30);
		pane.add(chooseImageButton);

		productType = new JComboBox<String>();
		this.decorateJComboBox(productType, 150, 180, 200, 30);
		productType.setModel(new DefaultComboBoxModel<String>(ProductView.TYPES));
		pane.add(productType);

		productPrice = this.createDefaultJTextField(150, 220, 200, 30);
		pane.add(productPrice);

		productQuantity = this.createDefaultJTextField(150, 260, 200, 30);
		pane.add(productQuantity);

		productState = new JComboBox<String>();
		productState.setModel(new DefaultComboBoxModel<String>(ProductView.STATES));
		this.decorateJComboBox(productState, 150, 300, 200, 30);
		pane.add(productState);

		JButton addButton = new JButton("Thêm");
		addButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		addButton.setBounds(70, 490, 120, 40);
		pane.add(addButton);

		JButton goBackButton = new JButton("Quay Lại");
		goBackButton.setFont(new Font("SansSerif", Font.BOLD, 18));
		goBackButton.setBounds(390, 490, 120, 40);
		pane.add(goBackButton);

		description = this.createDefaultJTextArea(150, 350, 400, 100);
		JScrollPane scrollDescription = this.createDefaultJScrollPane(description, 150, 350, 400, 100);
		description.setToolTipText("Giới hạn 200 từ");
		pane.add(description);
		pane.add(scrollDescription);
		// ---------------------------------EVENT------------------------------------------------------
		chooseImageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooseImageDialog = new JFileChooser();
				chooseImageDialog.setDialogTitle("Hãy chọn hình ảnh");
				chooseImageDialog.setMultiSelectionEnabled(false);
				chooseImageDialog.setFileFilter(new FileTypeFilter(".png", "PNG"));
				chooseImageDialog.setFileFilter(new FileTypeFilter(".gif", "GIF"));
				chooseImageDialog.setFileFilter(new FileTypeFilter(".jpg", "JPG"));
				int returnVal = chooseImageDialog.showOpenDialog(thisDialog);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					java.io.File file = chooseImageDialog.getSelectedFile();
					if (Utils.isImage(file)) {
						imgSrc.setText(file.getAbsolutePath());
					} else {
						JOptionPane.showMessageDialog(thisDialog, "File ảnh không hợp lệ!", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				} else {
					// statusLabel.setText("Open command cancelled by user.");
				}
			}
		});
		addButton.addMouseListener(new AddButtonPressHandler());
		goBackButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				productResponse = null;
				dispose();
			}
		});
	}

	private class AddButtonPressHandler extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			String id = productId.getText();
			String name = productName.getText();
			String type = productType.getSelectedItem().toString();
			String price = productPrice.getText();
			String quantity = productQuantity.getText();
			String state = productState.getSelectedItem().toString();
			String desc = description.getText();
			boolean isValid = ProductView.checkInputString(thisDialog, id, name, type, price, quantity, state, null,
					desc);
			String imgPath = imgSrc.getText();
			byte[] byteImg = null;
			if (!imgPath.isBlank()) {
				try {
					byteImg = Utils.toByteArray(Utils.toBufferedImage(Utils.getImage(imgPath, 130, 130)), "png");
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(thisDialog, "Hình ảnh không hợp lệ!\nChỉ có thể nhận định dạng file .jpg hoặc .png", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
			if (isValid) {
				productResponse = new Product(id, name, type, Float.parseFloat(price), Integer.parseInt(quantity),
						byteImg, state, desc);
				thisDialog.dispose();
			}
		}
	}

	public Product getDialogResponse() {
		return this.productResponse;
	}
}
