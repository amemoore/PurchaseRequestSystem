package prs.request.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import prs.business.Request;
import prs.business.User;
import prs.util.DBUtil;

public class RequestDB implements RequestDAO {
	ArrayList<User> requests;
	
		public void addRequest(Request r) {
			String sql
	                = "INSERT INTO requests "
	                	+ " (Description, Justification, DateNeeded, UserId, DeliveryMode, DocAttached, Status, Total, SubmittedDate) "
	                + " VALUES (?,?,?,?,?,?,?,?,?) ";
	        try ( Connection connection = DBUtil.getConnection();
	        		PreparedStatement ps = connection.prepareStatement(sql)) {
	            ps.setString(1, r.getDescription());
	            ps.setString(2, r.getJustification());
	            ps.setDate(3, r.getDateNeeded());
	            ps.setInt(4, r.getUserID());
	            ps.setString(5, r.getDeliveryMode());
	            ps.setBoolean(6, r.isDocAttached());
	            ps.setString(7, r.getStatus());
	            ps.setDouble(8, r.getTotal());
	            ps.setDate(9, r.getDateSubmitted());
	            ps.executeUpdate();
	            
	        } 
	        catch (SQLException e) {
	            System.out.println(e);
	        }
    }
		
		public int getRequestId() { 
			int rId=0;
			String sql = "SELECT    * "
							+ " FROM      requests "
							+ " ORDER BY  ID DESC "
							+ " LIMIT     1 ";
			try (  Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)) {
					ResultSet rs = ps.executeQuery();
				rs.next();
		    		rId = rs.getInt(1);
		    } 
			catch (SQLException e) {
		        System.out.println(e);
		    }
		    return rId;
	}		
}
