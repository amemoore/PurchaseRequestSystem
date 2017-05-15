package product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prs.business.Product;
import prs.util.DBUtil;

public class ProductDB implements ProductDAO {
	ArrayList<Product>products = null;

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
}
