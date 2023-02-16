package gui.receipt;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import gui.Utils;
import gui.login.Account;
import gui.product.ProductTableModel;
import gui.product.ProductView;
import mau.SubDialog;
import model.Product;
import subclass.SearchProductObject;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CartGUI extends SubDialog {

	private static final long serialVersionUID = 1L;
	private CartGUI thisDialog = this;
	
	private JTextField p_id;
	private JTextField p_name;
	private JComboBox<String> p_type;
	private ProductTableModel productModel;
	private JTable productTable;
	private ProductTableModel cartModel;
	private JTable cartTable;
	private List<Product> productList;

	public static void main(String[] args) {
		try {
			CartGUI dialog = new CartGUI(new JFrame(), new ArrayList<>());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CartGUI(JFrame owner, List<Product> productList) {
		super(owner);
		setSize(500, 640);
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
		
		p_type = new JComboBox<String>(new DefaultComboBoxModel<String>(ProductView.TYPES));
		p_type.addItem(ProductView.anyType);
		p_type.setSelectedItem(ProductView.anyState);
		p_type.setFont(new Font("SansSerif", Font.PLAIN, 16));
		p_type.setBounds(130, 115, 160, 24);
		pane.add(p_type);
		
		productModel = new ProductTableModel();
		productTable= this.createDefaultJTable(20, 153, 454, 200);
		productTable.setModel(productModel);
		productTable.getColumnModel().removeColumn(productTable.getColumnModel().getColumn(7));
		productTable.getColumnModel().removeColumn(productTable.getColumnModel().getColumn(6));
		productTable.getColumnModel().removeColumn(productTable.getColumnModel().getColumn(3));
		JScrollPane productTableScroll = this.createDefaultJScrollPane(productTable, 20, 153, 454, 200);
		pane.add(productTableScroll);
		
		cartModel = new ProductTableModel();
		cartModel.setData(productList);
		cartTable = this.createDefaultJTable(20, 434, 454, 120);
		cartTable.setModel(cartModel);
		cartTable.getColumnModel().removeColumn(cartTable.getColumnModel().getColumn(7));
		cartTable.getColumnModel().removeColumn(cartTable.getColumnModel().getColumn(6));
		cartTable.getColumnModel().removeColumn(cartTable.getColumnModel().getColumn(3));
		cartTable.getColumnModel().removeColumn(cartTable.getColumnModel().getColumn(2));
		JScrollPane cartTableScroll = this.createDefaultJScrollPane(cartTable, 20, 434, 454, 120);
		pane.add(cartTableScroll);
		
		JButton p_cart_add = new JButton("");
		p_cart_add.setIcon(Utils.getImageIcon("src/main/resources/logo/ToCart.png", 50, 50));
		p_cart_add.setBounds(100, 363, 60, 60);
		pane.add(p_cart_add);
		
		JButton p_cart_remove = new JButton("");
		p_cart_remove.setBounds(320, 363, 60, 60);
		p_cart_remove.setIcon(Utils.getImageIcon("src/main/resources/logo/OutCart.png", 60, 60));
		pane.add(p_cart_remove);
		
		JButton c_confirm = new JButton("Xác Nhận");
		c_confirm.setFont(new Font("SansSerif", Font.PLAIN, 16));
		c_confirm.setBounds(185, 560, 120, 30);
		pane.add(c_confirm);
		JButton p_searchBtn = new JButton("");
		p_searchBtn.setIcon(Utils.getImageIcon("src/main/resources/logo/search.png", 60, 60));
		p_searchBtn.setBounds(414, 52, 60, 60);
		pane.add(p_searchBtn);
	/*--------------------------------------- EVENT ------------------------------------*/
		p_searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				searchProductHandler();
			}
		});
		p_cart_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addToCartHandler();
			}
		});
		p_cart_remove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				removeFromCartHandler();
			}
		});
		c_confirm.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmButtonHandler();
			}
		});
		productModel.setData(getProductList());
	}
	
	List<Product> getProductListResponse(){
		return productList;
	}
	
	private void searchProductHandler() {
		String id = p_id.getText().isBlank() ? null : p_id.getText();
		String name = p_name.getText().isBlank() ? null : p_name.getText();
		String type = p_type.getSelectedItem().toString().equals(ProductView.anyType) ? null : p_type.getSelectedItem().toString();
		Product keyword =  new Product(id, name, type, null, null, null, null, null );
		SearchProductObject searchProductObject = new  SearchProductObject(keyword);
		List<Product> productList = searchProductObject.getListProductResult();
		productModel.setData(productList);
	}
	
	private List<Product> getProductList() {
		SearchProductObject searchProductObject = new SearchProductObject(null);
		return searchProductObject.getListProduct();
		
	}
	
	private void addToCartHandler() {
		int selectedRow = productTable.getSelectedRow();
		if(selectedRow < 0) { // =-1
			return;
		}
		Product selectedProduct = productModel.getProduct(selectedRow);
		boolean hasExisted= existsInCart(selectedProduct.getId());
		if(hasExisted) {
			JOptionPane.showMessageDialog(thisDialog, "Sản phẩm đã có trong giỏ hàng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		String quantityStr = JOptionPane.showInputDialog(thisDialog, "Hãy nhập số lượng muốn mua: ");
		if(!quantityStr.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(thisDialog, "Số lượng không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}else {
			int quantity = Integer.parseInt(quantityStr);
			if(quantity != 0 && quantity <= selectedProduct.getQuantity()) {
				Product cartItem = new Product(selectedProduct.getId(), selectedProduct.getName(), null,
									selectedProduct.getPrice(), quantity, null, null, null);
				cartModel.getListProduct().add(cartItem);
				cartModel.reloadModel();
			}
		}
	}
	
	private void removeFromCartHandler() {
		int selectedRow = cartTable.getSelectedRow();
		cartModel.getListProduct().remove(selectedRow);
		cartModel.reloadModel();
		
	}
	
	private boolean existsInCart(String id) {//true nếu sản phẩm đã có trong giỏ hàng
		return cartModel.getListProduct().stream().anyMatch((p)->{
			return p.getId().equals(id);
		});
	}
	
	private void confirmButtonHandler() {
		productList = cartModel.getListProduct();
		thisDialog.dispose();
	}
	
	
}
