package gui.product;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.JTableHeader;

import gui.Utils;
import model.Product;

/*
 * Là 1 JPanel quản lý giao diện Sản Phẩm
 * Cung cấp các hàm
 * addProductReloadListener(ActionListener listener)
 * addProductSearchListener(ActionListener listener)
 * addProductAddListener(ActionListener listener)
 * addProductUpdateListener(ActionListener listener)
 * addProductRemoveListener(ActionListener listener)
 * addProductGoHomeListener(ActionListener listener)
 * 
 * showListProduct();
 * showSearchGUI()
 * showAddGUI()
 * showUpdateGUI()
 * showRemoveGUI()
 */
public class ProductView extends JPanel {
	private static final long serialVersionUID = 1L;
	private JFrame owner;
	private JTextField productId;
	private JTextField productName;
	private JTextField productType;
	private JLabel productImage;
	private JTextField productPrice;
	private JTextField productQuantity;
	private JTextField productState;
	private JTextArea description;

	private JTable listProduct;
	private ProductTableModel productTableModel;
	private JButton productSearch;
	private JButton productAdd;
	private JButton productUpdate;
	private JButton productDelete;
	private JButton goHome;

	public final static String[] TYPES = { "Đồ Ăn", "Sách, Truyện", "Quần Áo", "Giầy, Dép", "Đồ Điện Tử", "Điện Thoại",
			"Máy Tính", "Đồ Gia Dụng", "Đồ Trang Trí", "Y Tế" };
	public final static String[] STATES = { "Đang Bán", "Chưa Bán" };
	public final static String anyType = "Bất Kỳ";
	public final static String anyState = "Bất Kỳ";

	private JButton productReload;

	/**
	 * Create the panel.
	 */
	// Sau quá trình khởi tạo trừ các trước thuộc tính khai báo ở trên tất cả sẽ bị
	// drop
	public ProductView(JFrame owner) {
		this.owner = owner;
		JPanel productPanel = this;
		JLabel lblNewLabel = new JLabel("Dánh Sách Sản Phẩm");
		JLabel lblNewLabel_4;
		JLabel lblNewLabel_5;
		JLabel lblNewLabel_6;
		JLabel lblNewLabel_9;
		JLabel lblNewLabel_10;

		productPanel.setSize(new Dimension(980, 640));
		productPanel.setLayout(null); // absoluteLayout
		setLayout(null);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblNewLabel.setBounds(370, 10, 240, 30);
		productPanel.add(lblNewLabel);

		listProduct = new JTable();
		listProduct.setFont(new Font("SansSerif", Font.PLAIN, 18));
		listProduct.setBounds(20, 220, 800, 430);
		listProduct.setRowHeight(28);
		// An column hinh anh di
		// Lay du lieu hinh anh table.getModel().getValueAt(row, 7)
		JTableHeader listProductHeader = listProduct.getTableHeader();
		listProductHeader.setFont(new Font("SansSerif", Font.BOLD, 20));
		listProductHeader.setBackground(Color.yellow);
		JScrollPane scrollPane = new JScrollPane(listProduct);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(20, 220, 800, 430);
		productPanel.add(scrollPane);

		productSearch = new JButton("Tìm Kiếm");
		productSearch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		productSearch.setBounds(830, 270, 126, 40);
		productPanel.add(productSearch);

		productAdd = new JButton("Thêm");
		productAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		productAdd.setBounds(830, 321, 126, 40);
		productPanel.add(productAdd);

		productUpdate = new JButton("Sửa");
		productUpdate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		productUpdate.setBounds(830, 372, 126, 40);
		productPanel.add(productUpdate);

		productDelete = new JButton("Xóa");
		productDelete.setFont(new Font("Tahoma", Font.PLAIN, 18));
		productDelete.setBounds(830, 423, 126, 40);
		productPanel.add(productDelete);

		goHome = new JButton("Trở Về");
		goHome.setFont(new Font("Tahoma", Font.PLAIN, 18));
		goHome.setBounds(830, 610, 126, 40);
		productPanel.add(goHome);

		productImage = new JLabel(Utils.getImageIcon("src/main/resources/logo/products.png", 130, 130));
		productImage.setBounds(20, 64, 130, 130);
		productPanel.add(productImage);

		lblNewLabel_4 = new JLabel("Tên Sản Phẩm:");
		lblNewLabel_4.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(160, 64, 130, 30);
		productPanel.add(lblNewLabel_4);

		lblNewLabel_5 = new JLabel("Mã Sản Phẩm:");
		lblNewLabel_5.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(160, 124, 130, 30);
		productPanel.add(lblNewLabel_5);

		lblNewLabel_6 = new JLabel("Loại Sản Phẩm:");
		lblNewLabel_6.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_6.setBounds(160, 164, 130, 30);
		productPanel.add(lblNewLabel_6);

		productName = new JTextField();
		productName.setEditable(false);
		productName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		productName.setBounds(300, 64, 334, 30);
		productPanel.add(productName);
		productName.setColumns(10);

		lblNewLabel_9 = new JLabel("Đơn Giá:");
		lblNewLabel_9.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_9.setBounds(540, 124, 96, 30);
		productPanel.add(lblNewLabel_9);

		lblNewLabel_10 = new JLabel("Số Lượng:");
		lblNewLabel_10.setFont(new Font("SansSerif", Font.PLAIN, 18));
		lblNewLabel_10.setBounds(540, 164, 96, 30);
		productPanel.add(lblNewLabel_10);

		productId = new JTextField();
		productId.setEditable(false);
		productId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productId.setColumns(10);
		productId.setBounds(300, 124, 200, 30);
		productPanel.add(productId);

		productPrice = new JTextField();
		productPrice.setEditable(false);
		productPrice.setFont(new Font("SansSerif", Font.PLAIN, 16));
		productPrice.setBounds(646, 125, 120, 30);
		productPanel.add(productPrice);
		productPrice.setColumns(10);

		productQuantity = new JTextField();
		productQuantity.setEditable(false);
		productQuantity.setFont(new Font("SansSerif", Font.PLAIN, 16));
		productQuantity.setColumns(10);
		productQuantity.setBounds(646, 164, 120, 30);
		productPanel.add(productQuantity);

		description = new JTextArea();
		description.setBackground(new Color(240, 240, 240));
		description.setEditable(false);
		description.setFont(new Font("SansSerif", Font.PLAIN, 16));
		description.setLineWrap(true);
		description.setWrapStyleWord(true);
		description.setBounds(776, 64, 180, 131);
		JScrollPane productDescription = new JScrollPane(description);
		productDescription.setBounds(776, 64, 180, 131);
		productDescription.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		productPanel.add(productDescription);

		productType = new JTextField();
		productType.setFont(new Font("Tahoma", Font.PLAIN, 16));
		productType.setEditable(false);
		productType.setColumns(10);
		productType.setBounds(300, 164, 200, 30);
		add(productType);

		productState = new JTextField();
		productState.setFont(new Font("SansSerif", Font.PLAIN, 16));
		productState.setEditable(false);
		productState.setColumns(10);
		productState.setBounds(646, 64, 120, 30);
		add(productState);

		productReload = new JButton("Tải lại");
		productReload.setFont(new Font("Tahoma", Font.PLAIN, 18));
		productReload.setBounds(830, 220, 126, 40);
		add(productReload);
		/*----------------------------Bổ sung -----------------------------------------------------*/
		productTableModel = new ProductTableModel();
		listProduct.setModel(productTableModel);
		listProduct.getColumnModel().removeColumn(listProduct.getColumnModel().getColumn(7));
		listProduct.getColumnModel().removeColumn(listProduct.getColumnModel().getColumn(6));// Xóa cao xuống thấp OK
		
		/*---------------------------EVENT-------------------------------------------------------- */
		listProduct.getSelectionModel().addListSelectionListener(new RowSelectionListener());
	}
	
	/*
	 * Được gọi từ Controller nhận vào 1 list các đối tượng products
	 * Để setData cho model của JTable
	 */
	public void showListProduct(List<Product> products) {
		productTableModel.setData(products);
	}

	public Product showSearchGUI() {
		SearchProductGUI searchGUI = new SearchProductGUI(owner);
		searchGUI.setVisible(true);
		return searchGUI.getProductResponse();
	}

	public Product showAddGUI() {
		AddingProductGUI addingGUI = new AddingProductGUI(owner);
		addingGUI.setVisible(true);
		return addingGUI.getDialogResponse();
	}

	public Product showUpdateGUI() {
		Product p = getSelectedProduct();
		if(p!=null) {
			UpdateProductGUI updateGUI = new UpdateProductGUI(owner, p);
			updateGUI.setVisible(true);
			return updateGUI.getDialogResponse();
		}
		return null;
	}
	
	public String showRemoveGUI(JFrame owner) {
		int selectedRow = listProduct.getSelectedRow();
		Product p = productTableModel.getProduct(selectedRow);
		int result = JOptionPane.showConfirmDialog(owner, 
				"Bạn có chắc là muốn xóa sản phẩm " + p.getName() + " ?", 
				"Xác thực muốn xóa sản phẩm", 
				JOptionPane.YES_NO_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE);
		if(result == JOptionPane.YES_OPTION) {
			return p.getId();
		}
		return null;
	}
	
	/*
	 * Được gọi từ Controller nhận vào 1 listener để thêm Event vào cho nút "Thêm"
	 * Hiểu thị ProductAddGUI -> product
	 * Gọi productDAO.add(product) -> boolean
	 * từ boolean quyết định xem có reload lại hay không hay hiểu thị ERROR Message
	 */
	public void addProductReloadListener(ActionListener listener) {
		productReload.addActionListener(listener);
	}

	public void addProductSearchListener(ActionListener listener) {
		productSearch.addActionListener(listener);
	}

	public void addProductAddListener(ActionListener listener) {
		productAdd.addActionListener(listener);
	}

	public void addProductUpdateListener(ActionListener listener) {
		productUpdate.addActionListener(listener);
	}

	public void addProductRemoveListener(ActionListener listener) {
		productDelete.addActionListener(listener);
	}
	
	public void addProductGoHomeListener(ActionListener listener) {
		goHome.addActionListener(listener);
	}
	
	/*
	 * Phương thức Default Modifier
	 */
	final static boolean checkInputString(JDialog owner, String id, String name, String type, String price, String quantity, String state,
			String imgSrc, String description) {
		if (id != null) {
			if (id.isBlank() || !id.matches("SP[0-9]+")) {
				JOptionPane.showMessageDialog(owner, "Mã sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if(name!=null) {
			if (name.isBlank()) {
				JOptionPane.showMessageDialog(owner, "Tên sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if(type!=null) {
			if (type.isBlank() || !Arrays.asList(ProductView.TYPES).contains(type)) {
				JOptionPane.showMessageDialog(owner, "Loại sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if(price!=null) {
			if (price.isBlank() || !price.matches("[0-9]+(.)[0-9]+")) {
				JOptionPane.showMessageDialog(owner, "Đơn giá sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if(quantity!=null) {
			if (quantity.isBlank() || !quantity.matches("[0-9]+")) {
				JOptionPane.showMessageDialog(owner, "Số lượng sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		if(state!=null) {
			if (state.isBlank() || !Arrays.asList(ProductView.STATES).contains(state)) {
				JOptionPane.showMessageDialog(owner, "Trạng thái sản phẩm không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if(imgSrc!=null) {
			try {
				if (ImageIO.read(new File(imgSrc)) == null) {
					JOptionPane.showMessageDialog(owner, "Không tìm thấy hình ảnh!", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return false;
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(owner, "Hình ảnh không hợp lệ!\nChỉ có thể nhận định dạng file .jpg hoặc .png", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		
		if(description!=null) {
			if(description.length() >= 200) {
				JOptionPane.showMessageDialog(owner, "Mô tả sản phẩm quá 200 từ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	/*
	 * Phương thức private để xử lý trong nội bộ giao diện
	*/
	//Hàm lấy dữ liệu từ JTable để Render lên Các thành phần để đọc
	private class RowSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {
			Product p = getSelectedProduct();
			if (!e.getValueIsAdjusting() && p != null) {
				productName.setText(p.getName());
				productId.setText(p.getId());
				productType.setText(p.getType());
				productPrice.setText(Utils.formatNumber(p.getPrice()));
				productQuantity.setText(String.format("%d", p.getQuantity()));
				productState.setText(p.getState());
				description.setText(p.getDescription());
				if (p.getImage() != null) {
					try {
						ImageIcon image = new ImageIcon(Utils.toBufferedImage(p.getImage()));
						productImage.setIcon(image);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					productImage
							.setIcon(Utils.getImageIcon("src/main/resources/img/DefaultProductImage.png", 130, 130));
				}
			}
		}
	}
	
	//Hàm lấy dữ liệu từ JTable để trả về 1 đối tượng product
	private Product getSelectedProduct() {
		int selectedRow = listProduct.getSelectedRow();
		if (selectedRow != -1) {
			return productTableModel.getProduct(selectedRow);
		}
		return null;
	}
}
