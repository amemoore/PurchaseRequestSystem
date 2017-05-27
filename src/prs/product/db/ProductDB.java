package prs.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prs.business.Product;
import prs.util.DBUtil;

public class ProductDB implements ProductDAO {
	ArrayList<Product>products = null;
	Product p = null;

//GET ALL PRODUCTS
	public ArrayList<Product> getAllProducts() { 
		products = new ArrayList<>();
		String sql = " SELECT * from products ";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery()) {
        	while (rs.next()){
        		int productId = rs.getInt(1);
                String name = rs.getString(2);
				String partNumber = rs.getString(3);
				double price = rs.getDouble(4);
				String unit = rs.getString(5);
				int vendorID = rs.getInt(6);
				String photoPath = rs.getString(7);
                Product p = new Product(productId, name, partNumber, price, unit, vendorID, photoPath);
                products.add(p);
        	}
        } 
		catch (SQLException e) {
            System.out.println(e);
        }
        return products;
    }
//GET PRODUCTS BY VENDOR ID
	public ArrayList<Product> getProductsForVendor(int ven) { 
		products = new ArrayList<>();
		String sql = " SELECT * from products " +
						" WHERE vendorID = ? ";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setInt(1, ven); 
				ResultSet rs = ps.executeQuery();
	    	while (rs.next()){
	    		int productId = rs.getInt(1);
	            String name = rs.getString(2);
				String partNumber = rs.getString(3);
				double price = rs.getDouble(4);
				String unit = rs.getString(5);
				int vendorID = rs.getInt(6);
				String photoPath = rs.getString(7);
	            Product p = new Product(productId, name, partNumber, price, unit, vendorID, photoPath);
	            products.add(p);
	    	}
	    } 
		catch (SQLException e) {
	        System.out.println(e);
	    }
	    return products;
	}

//GET PRODUCTS BY VENDOR NAME
	public Product getProduct(String nm) { 
		String sql = " SELECT * from products " +
						" WHERE Name = ?";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
				ps.setString(1, nm); 
				ResultSet rs = ps.executeQuery();
	    	while (rs.next()){
	    		int productId = rs.getInt(1);
	            String name = rs.getString(2);
				String partNumber = rs.getString(3);
				double price = rs.getDouble(4);
				String unit = rs.getString(5);
				int vendorID = rs.getInt(6);
				String photoPath = rs.getString(7);
	            p = new Product(productId, name, partNumber, price, unit, vendorID, photoPath);
	    	}
	    } 
		catch (SQLException e) {
	        System.out.println(e);
	    }
	    return p;
	}
	
//GET PRODUCTS BY PRODUCT ID
		public Product getProductByProductNo(int i) { 
			String sql = " SELECT * from products " +
							" WHERE Id = ? ";
			try (  Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)){
					ps.setInt(1, i); 
					ResultSet rs = ps.executeQuery();
		    	while (rs.next()){
		    		int productId = rs.getInt(1);
		            String name = rs.getString(2);
					String partNumber = rs.getString(3);
					double price = rs.getDouble(4);
					String unit = rs.getString(5);
					int vendorID = rs.getInt(6);
					String photoPath = rs.getString(7);
		            p = new Product(productId, name, partNumber, price, unit, vendorID, photoPath);
		    	}
		    } 
			catch (SQLException e) {
		        System.out.println(e);
		    }
		    return p;
		}
}

