package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gui.login.Account;
import gui.receipt.ReceiptView;
import model.Receipt;
import model.ReceiptDAO;

public class ReceiptController {
	//private JFrame owner;
	private ReceiptView receiptView;
	private ReceiptDAO receiptDAO;
	
	public ReceiptController(JFrame owner, Account account) {
		//this.owner = owner;
		receiptView = new ReceiptView(owner);
		receiptDAO = new ReceiptDAO(account);
		
		receiptView.addReceiptReloadListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				reloadReceiptView();
			}
		});
		
		receiptView.addReceiptAddListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Receipt newReceipt = receiptView.showReceiptAddAndGetResponse();
				if(newReceipt!=null) {
					boolean isSuccess = receiptDAO.add(newReceipt);
					if(isSuccess) {
						reloadReceiptView();
					}else {
						JOptionPane.showMessageDialog(owner, "Lỗi! Vi phạm rằng buộc dưới CSDL", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		receiptView.addReceiptSearchListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Receipt keyword = receiptView.showReceiptSearchAndGetResponse();
				if(keyword!=null) {
					List<Receipt> receiptList = receiptDAO.search(keyword);
					receiptView.showListReceipt(receiptList);
				}
			}
		});
		receiptView.addReceiptRemoveListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = receiptView.showReceiptRemoveAndGetResponse();
				if(id==null) {
					return;
				}
				boolean isSuccess = receiptDAO.remove(id);
				if(isSuccess) {
					reloadReceiptView();
				}else {
					JOptionPane.showMessageDialog(owner, "Lỗi! Hóa đơn chưa bị hủy!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		showReceiptView();
	}
	
	private void showReceiptView() {
		List<Receipt> receiptList = receiptDAO.getList();
		receiptView.showListReceipt(receiptList);
		receiptView.setVisible(true);
	}
	
	private void reloadReceiptView() {
		List<Receipt> receiptList = receiptDAO.getList();
		receiptView.showListReceipt(receiptList);
	}
	
	public JPanel getPanel() {
		return this.receiptView;
	}
}
