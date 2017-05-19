package prs.request.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import prs.business.Request;
import prs.business.User;
import prs.util.DBUtil;

public class RequestDB implements RequestDAO {
	ArrayList<User> requests;
	
		public void addRequest(Request r) {
//			String sql
//	                = "INSERT INTO requests (UserName, Password, FirstName, LastName, Phone, Email, Manager) "
//	                + "VALUES (?,?,?,?,?,?,?)";
//	        try ( Connection connection = DBUtil.getConnection();
//	        		PreparedStatement ps = connection.prepareStatement(sql)) {
//	            ps.setString(1, u.getUserName());
//	            ps.setString(2, u.getPassword());
//	            ps.setString(3, u.getFirstName());
//	            ps.setString(4, u.getLastName());
//	            ps.setString(5, u.getPhone());
//	            ps.setString(6, u.getEmail());
//	            ps.setBoolean(7, u.isManager());
//	            ps.executeUpdate();
//	        } 
//	        catch (SQLException e) {
//	            System.out.println(e);
//	        }
    }
}
