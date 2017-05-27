package prs.request.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import prs.business.Request;
import prs.business.User;
import prs.util.DBUtil;

public class RequestDB implements RequestDAO {
	ArrayList<User> requests;
	private static Request r ;
	
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
		
		public ArrayList<String> checkRequest() { 
//			String firstName;
//			String lastName;
//    		String productName;
//    		String price;
//    		int quantity;
//    		Date dateNeeded;
//    		Date dateSubmitted;
//    		String status;
    		ArrayList<String> requestStatus = new ArrayList<>();
//			String sql = " SELECT u.FirstName, u.LastName, p.Name, p.Price, l.Quantity, r.DateNeeded, r.SubmittedDate, r.Status "
//							+  " FROM users u, requests r, lineitems l, products p "
//							+ " where u.Id = r.UserId "
//							+ " and r.Id = l.RequestId "
//    						+ " and l.ProductId = p.Id "
//    						+ " Order by r.SubmittedDate ";
//			try (  Connection connection = DBUtil.getConnection();
//					PreparedStatement ps = connection.prepareStatement(sql)) {
//					ResultSet rs = ps.executeQuery();
//				while (rs.next()){
//		    		firstName = rs.getString(1);
//		    		lastName = rs.getString(2);
//		    		productName = rs.getString(3);
//		    		price = (String)rs.getString(4);
//		    		quantity = rs.getInt(5);
//		    		dateNeeded = rs.getDate(6);
//		    		dateSubmitted = rs.getDate(7);
//		    		status = rs.getString(8);
//		    		
//		    		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
//	                String dateNeededString = dateFormat.format(dateNeeded);
//	                String dateSubmittedString = dateFormat.format(dateSubmitted);
//		    		String reqString = new String();
//		    				reqString.add(firstName);
//		    		 requestStatus.add(lastName);
//		    		 requestStatus.add(price);
//		    		 requestStatus.add(productName);
//		    		 requestStatus.add(Integer.toString(quantity));
//		    		 requestStatus.add(dateNeededString);
//		    		 requestStatus.add(dateSubmittedString);
//		    		 requestStatus.add(status);
//		     }
//			}
//			catch (SQLException e) {
//		        System.out.println(e);
//		    }
		    return requestStatus;
	}	
		public int approveRequest(int requestId) { 
			int count = 0;
			String sql = "Update requests "	
							+ "set status = 'approved' "
							+ " where id = ? ";
			try (  Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)) {
					ps.executeUpdate();
					ps.setInt(1, requestId);
		    } 
			
			catch (SQLException e) {
		        System.out.println(e);
		    }
			return count;
		}	   
		
		public int approveRequestUnderFifty(int rId) { 
			int count = 0;
			String sql = " Update requests "	
						+ " set status = 'approved' "
						+ " where id = ? ";
			try (  Connection connection = DBUtil.getConnection();
					PreparedStatement ps = connection.prepareStatement(sql)) {
					ps.setInt(1, rId);
					count = ps.executeUpdate();
		    } 
			catch (SQLException e) {
		        System.out.println(e);
		    }
			return count;
		}	    
}
