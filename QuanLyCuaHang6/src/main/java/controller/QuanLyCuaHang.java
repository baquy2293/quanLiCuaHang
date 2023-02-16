package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

import gui.MainPageGUI;
import gui.Utils;
import gui.login.Account;
import gui.login.LoginGUI;
import mau.PaneMau;
import model.LoginDAO;

public final class QuanLyCuaHang {
	MainPageGUI mainPageGUI;
	LoginGUI loginGUI;
	
	private QuanLyCuaHang() {
		loginGUI = new LoginGUI();
		loginGUI.addEventForLoginBtn(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Account account = loginGUI.getResponse();
				LoginDAO loginDAO = new LoginDAO();
				Account srv_account = loginDAO.getUser(account);
				if(srv_account != null) {
					showMainPage(srv_account);
				}else {
					JOptionPane.showMessageDialog(loginGUI, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		loginGUI.setVisible(true);
	}
	
	private void showMainPage(Account srv_account) {
		loginGUI.clear();
		mainPageGUI = new MainPageGUI();
		mainPageGUI.setLogoutMouseClickedEvent(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainPageGUI.clear();
				mainPageGUI.setVisible(false);
				loginGUI.setVisible(true);
			}
			
		});
		if(srv_account.getPos().equals("Nhân Viên")) {
			showEmployeeView(srv_account);
		}else if(srv_account.getPos().equals("Quản Trị")) {
			showAdministratorView(srv_account);
		}
		loginGUI.clear();
		loginGUI.setVisible(false);
	}
	
	private void showEmployeeView(Account srv_account) {
		MainPageGUI.ControlItem productControl = mainPageGUI.new ControlItem("Sản Phẩm", 
				Utils.getImageIcon("src/main/resources/logo/products.png", 42, 42));
		MainPageGUI.ControlItem receiptControl = mainPageGUI.new ControlItem("Hóa Đơn", 
				Utils.getImageIcon("src/main/resources/logo/receipt (1).png", 42, 42));
		MainPageGUI.ControlItem chartControl = mainPageGUI.new ControlItem("Thống Kê", 
				Utils.getImageIcon("src/main/resources/logo/pie-chart.png", 42, 42));
		chartControl.setSeparator(true);
		ProductController productController = new ProductController(mainPageGUI, srv_account);
		ReceiptController receiptController = new ReceiptController(mainPageGUI, srv_account);
		
		getDefaultControlItem();
		mainPageGUI.addContent(productController.getPanel(), productControl, 1);
		mainPageGUI.addContent(receiptController.getPanel(), receiptControl, 2);
		mainPageGUI.addContent(new PaneMau(), chartControl, 3);
		mainPageGUI.setVisible(true);
	}
	
	private void showAdministratorView(Account srv_account) {
		MainPageGUI.ControlItem productControl = mainPageGUI.new ControlItem("Sản Phẩm", 
				Utils.getImageIcon("src/main/resources/logo/products.png", 42, 42));
		MainPageGUI.ControlItem employeeControl = mainPageGUI.new ControlItem("Nhân Viên", 
				Utils.getImageIcon("src/main/resources/logo/employee.png", 42, 42));
		MainPageGUI.ControlItem customerControl = mainPageGUI.new ControlItem("Khách Hàng", 
				Utils.getImageIcon("src/main/resources/logo/Customer.png", 42, 42));
		MainPageGUI.ControlItem receiptControl = mainPageGUI.new ControlItem("Hóa Đơn", 
				Utils.getImageIcon("src/main/resources/logo/receipt (1).png", 42, 42));
		MainPageGUI.ControlItem chartControl = mainPageGUI.new ControlItem("Thống Kê", 
				Utils.getImageIcon("src/main/resources/logo/pie-chart.png", 42, 42));
		chartControl.setSeparator(true);
		ProductController productController = new ProductController(mainPageGUI, srv_account);
		CustomerController customerController = new CustomerController(mainPageGUI, srv_account);
		ReceiptController receiptController = new ReceiptController(mainPageGUI, srv_account);
		
		getDefaultControlItem();
		mainPageGUI.addContent(productController.getPanel(), productControl, 1);
		mainPageGUI.addContent(new PaneMau(), employeeControl, 2);
		mainPageGUI.addContent(customerController.getPanel(), customerControl, 3);
		mainPageGUI.addContent(receiptController.getPanel(), receiptControl, 4);
		mainPageGUI.addContent(new PaneMau(), chartControl, 5);
		//	addControlItem("Thống Kê", "src/main/resources/logo/pie-chart.png", true);
		mainPageGUI.setVisible(true);
	}
	
	private void getDefaultControlItem() {
		MainPageGUI.ControlItem account = mainPageGUI.new ControlItem("Tài Khoản", 
				Utils.getImageIcon("src/main/resources/logo/user.png", 42, 42));
		account.setSeparator(true);
		MainPageGUI.ControlItem settings = mainPageGUI.new ControlItem("Cài Đặt",  
				Utils.getImageIcon("src/main/resources/logo/settings.png", 42, 42));
		MainPageGUI.ControlItem logOut = mainPageGUI.new ControlItem("Thoát",  
				Utils.getImageIcon("src/main/resources/logo/logout(2).png", 42, 42));
		
		mainPageGUI.addContent(null, account, 0);
		mainPageGUI.addContent(null, settings, 1);
		mainPageGUI.addContent(null, logOut, 2);
		//addControlItem("Đăng Xuất", "src/main/resources/logo/logout(2).png",2 ,false);
	}
	
	public final static QuanLyCuaHang run() {
		return new QuanLyCuaHang();
	}
}
