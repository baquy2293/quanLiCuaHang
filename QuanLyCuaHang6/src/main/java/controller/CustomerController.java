package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import gui.account.AccountView;
import gui.login.Account;
import model.Customer;
import model.CustomerDAO;

public class CustomerController {
	private JFrame owner;
	private AccountView accountView;
	private CustomerDAO customerDAO;
	//private Account account;
	
	public CustomerController(JFrame owner, Account account) {
		this.owner = owner;
		accountView = new AccountView(owner);
		customerDAO = new CustomerDAO(account);
		accountView.addCustomerSearchUserListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					Object source = e.getSource();
					if (source instanceof JTextField) {
						 searchUser(((JTextField) source).getText());
					}
				}
			}
			
		});
		
		accountView.addCustomerSearchListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				searchCustomer();
			}
			
		});
		
		accountView.addCustomerAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCustomer();
			}
		});
		
		accountView.addCustomerReloadListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				reloadAccountView();
			}
			
		});
		
		accountView.addCustomerUpdateListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				updateCustomer();
			}
		});
		
		accountView.addCustomerRemoveListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				removeCustomer();
			}
		});
	}
	
	private void showAccountView() {
		List<Customer> customerList = customerDAO.getListCustomer();
		accountView.showListCustomer(customerList);
		accountView.setVisible(true);
	}
	
	private void reloadAccountView() {
		List<Customer> customerList = customerDAO.getListCustomer();
		accountView.showListCustomer(customerList);
	}
	
	private void searchUser(String user) {
		Customer keyWord = new Customer(null, user, null, null, null, null, null, null, null);
		List<Customer> results = customerDAO.search(keyWord);
		accountView.showListCustomer(results);
	}
	
	private void searchCustomer() {
		Customer keyWord = accountView.showSearchGUI(owner);
		if(keyWord!= null) {
			List<Customer> results = customerDAO.search(keyWord);
			accountView.showListCustomer(results);
		}
	}
	
	private void updateCustomer() {
		Customer c = accountView.showUpdateGUI(owner);
		if(c==null) {return;}
		boolean success = customerDAO.update(c);
		if(success) {
			reloadAccountView();
		}else {
			JOptionPane.showMessageDialog(owner, "Không thể cập nhật! Dữ liệu vi phạm rằng buộc dưới CSDL", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void addCustomer() {
		Customer newCustomer = accountView.showAddGUI(owner);
		if(newCustomer!=null) {
			boolean success = customerDAO.add(newCustomer);
			if(success) {
				reloadAccountView();
			}else {
				
			}
		}
	}
	
	private void removeCustomer() {
		String id = accountView.showRemoveGUI();
		if(id!=null) {
			boolean success = customerDAO.remove(id);
			if(success) {
				reloadAccountView();
				JOptionPane.showMessageDialog(owner, "Xóa khách hàng thành công", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(owner, "Không thể xóa do vi phạm rằng buộc dưới CSDL", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	public JPanel getPanel() {
		showAccountView();
		return accountView;
	}
	
	
}
