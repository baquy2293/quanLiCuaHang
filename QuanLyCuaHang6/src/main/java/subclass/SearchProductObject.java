package subclass;

import java.util.List;

import gui.login.Account;
import model.Product;
import model.ProductDAO;

public class SearchProductObject{
	private Product product;
	private List<Product> productList;
	private Account account;
	public SearchProductObject(Product p){
		account = new Account("sa", "123456a@".toCharArray());
		this.product = p;
	}
	
	public List<Product> getListProductResult() {
		Product keyWord = this.product;
		if(keyWord!=null) {
			ProductDAO productDAO = new ProductDAO(account);
			this.productList = productDAO.search(keyWord);
			return this.productList;
		}
		return null;
	}
	
	public List<Product> getListProduct() {
			ProductDAO productDAO = new ProductDAO(account);
			return productDAO.getListProduct();
	}
}