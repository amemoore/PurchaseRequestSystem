package prs.lineitem.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import prs.business.LineItem;
import prs.business.User;
import prs.util.DBUtil;

public class LineItemDB implements LineItemDAO {
	
	ArrayList<User> users;
	
		public void addLineItem(LineItem l) {
			String sql
		            = "INSERT INTO lineitems (RequestId, ProductId, Quantity)"
		            + "VALUES (?,?,?)";
		    try ( Connection connection = DBUtil.getConnection();
		    		PreparedStatement ps = connection.prepareStatement(sql)) {
		        ps.setInt(1, l.getRequestID());
		        ps.setInt(2, l.getProductID());
		        ps.setInt(3, l.getQuantity());
		        ps.executeUpdate();
		    } 
		    catch (SQLException e) {
		        System.out.println(e);
		    }
		}
}
