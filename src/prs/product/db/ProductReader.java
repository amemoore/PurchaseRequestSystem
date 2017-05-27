package prs.product.db;

import java.util.ArrayList;

import prs.business.Product;

public interface ProductReader {
	
	public ArrayList<Product> getAllProducts();
	
	public ArrayList<Product> getProductsForVendor(int ven);
	
	public Product getProduct(String nm);
	
	//public ArrayList<Product> getTopProducts();
}
