package subclass;

import java.util.ArrayList;
import java.util.List;

import gui.login.Account;
import model.Product;
import model.ReceiptDAO;

public class GetProductObject{
	private String receiptId;
	private List<Product> products;
	public GetProductObject(String receiptId) {
		this.receiptId = receiptId;
		products = new ArrayList<Product>();
	}
	private void getProductList() {
		Account account = new Account("sa", "123456a@".toCharArray());
		ReceiptDAO receiptDAO = new ReceiptDAO(account);
		products = receiptDAO.getPurchasedProduct(receiptId);
	}
	
	public List<Product> get(){
		getProductList();
		return products;
	}
}