package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.login.Account;
import gui.product.ProductView;
import model.Product;
import model.ProductDAO;

public class ProductController {
	private JFrame owner;
	private ProductView productView;
	private ProductDAO productDAO;
	
	public ProductController(JFrame owner, Account account){
		this.owner = owner;
		productView = new ProductView(owner);
		productDAO = new ProductDAO(account);
		
		productView.addProductReloadListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reloadProductView();
			}
		});
		
		productView.addProductSearchListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchProduct();
			}
		});
		
		productView.addProductAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addProduct();
			}
			
		});
		
		productView.addProductUpdateListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Update action perform!");
				updateProduct();
			}
			
		});
		productView.addProductRemoveListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				removeProduct();
			}
		});
		
	}
	
	/*
	 * Hàm được gọi khi khởi động chương trình
	 */
	void showProductView() {
		List<Product> products = productDAO.getListProduct();
		productView.showListProduct(products);
		productView.setVisible(true);
	}
	
	/*
	 * Hàm được gọi khi nút "Tải lại" của ProductView được ấn
	 */
	void reloadProductView() {
		List<Product> products = productDAO.getListProduct();
		productView.showListProduct(products);
	}
	
	
	/*
	 * Hàm được gọi khi Nút "Tìm Kiếm" trong ProductView được ấn
	 */
	protected void searchProduct() {
		Product keyWord = productView.showSearchGUI();
		if(keyWord!=null) {
			List<Product> products = productDAO.search(keyWord);
			productView.showListProduct(products);
		}
	}
	
	/*
	 * Hàm được gọi khi Nút "Thêm" trong ProductView được ấn
	 */
	private void addProduct() {
		Product productResponse = productView.showAddGUI();
		if(productResponse!=null) {
			boolean success = productDAO.add(productResponse);
			if(success) {
				reloadProductView();
			}else {
				JOptionPane.showMessageDialog(owner, "Không thể thêm! Dữ liệu vi phạm rằng buộc dưới CSDL", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/*
	 * Hàm được gọi khi Nút "Cập Nhật" trong ProductView được ấn
	 */
	private void updateProduct() {
		Product updatedProduct = productView.showUpdateGUI();
		if(updatedProduct != null) {
			boolean success = productDAO.update(updatedProduct);
			if(!success) {
				JOptionPane.showMessageDialog(owner, "Không thể cập nhật! Dữ liệu vi phạm rằng buộc dưới CSDL", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		reloadProductView();
		}
	}
	
	private void removeProduct() {
		String id = productView.showRemoveGUI(owner);
		if(id != null) {
			boolean success = productDAO.remove(id);
			if(success) {
				reloadProductView();
				JOptionPane.showMessageDialog(owner, "Xóa sản phẩm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(owner, "Xóa sản phẩm thất bại do rằng buộc CSDL!", "Thông báo", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	/*
	 * Hàm được gọi ở class App 
	 * Để thêm Panel ProductView vào TabbedPane của class MainPage
	 */
	public JPanel getPanel() {
		showProductView();
		return productView;
	}
}
