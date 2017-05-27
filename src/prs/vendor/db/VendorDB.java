package prs.vendor.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import prs.business.Vendor;
import prs.util.DBUtil;

public class VendorDB implements VendorDAO {
	ArrayList<Vendor>vendors = null;
	
/////COMPLETE LIST OF ALL VENDORS
	public ArrayList<Vendor> getAllVendors() { 
		vendors = new ArrayList<>();
		String sql = "SELECT * FROM vendors ";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				 ResultSet rs = ps.executeQuery()) {
        	while (rs.next()){
        		int vId = rs.getInt(1);
        		String code = rs.getString(2);
                String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				String phone = rs.getString(8);
				String email = rs.getString(9);
				boolean preapproved = rs.getBoolean(10);
                Vendor v = new Vendor(vId, code, name, address, city, state, zip, phone, email, preapproved);
                vendors.add(v);
        	}
        } 
		catch (SQLException e) {
            System.out.println(e);
        }
        return vendors;
    }
/////SEARCH VENDORS BY STATE
	public ArrayList<Vendor> getVendorByState(String st) { 
		vendors = new ArrayList<>();
		String sql = "SELECT * FROM vendors "
						+ "Where State = ?";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, st);
				ResultSet rs = ps.executeQuery();
			while (rs.next()){
        		int vId = rs.getInt(1);
        		String code = rs.getString(2);
                String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				String phone = rs.getString(8);
				String email = rs.getString(9);
				boolean preapproved = rs.getBoolean(10);
                Vendor v = new Vendor(vId, code, name, address, city, state, zip, phone, email, preapproved);
                vendors.add(v);
        	}
        } 
		catch (SQLException e) {
            System.out.println(e);
        }
        return vendors;
}
/////SEARCH VENDORS BY NAME
	public Vendor getVendorByName(String nm) { 
		Vendor v = null;
		String sql = "SELECT * FROM vendors "
						+ "Where Name = ?";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setString(1, nm);
				ResultSet rs = ps.executeQuery();
			while (rs.next()){
	    		int vId = rs.getInt(1);
	    		String code = rs.getString(2);
	            String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				String phone = rs.getString(8);
				String email = rs.getString(9);
				boolean preapproved = rs.getBoolean(10);
	            v = new Vendor(vId, code, name, address, city, state, zip, phone, email, preapproved);
	    	}
	    } 
		catch (SQLException e) {
	        System.out.println(e);
	    }
	    return v;
}	
/////SEARCH VENDORS BY ID
	public Vendor getVendorByVendorId(int nm) { 
		Vendor v = null;
		String sql = "SELECT * FROM vendors "
						+ "Where Id = ?";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
				ps.setInt(1, nm);
				ResultSet rs = ps.executeQuery();
			while (rs.next()){
	    		int vId = rs.getInt(1);
	    		String code = rs.getString(2);
	            String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				String phone = rs.getString(8);
				String email = rs.getString(9);
				boolean preapproved = rs.getBoolean(10);
	            v = new Vendor(vId, code, name, address, city, state, zip, phone, email, preapproved);
	    	}
	    } 
		catch (SQLException e) {
	        System.out.println(e);
	    }
	    return v;
}	
/////LIST PREAPPROVED VENDORS
	public ArrayList<Vendor> listPreapprovedVendors() { 
		Vendor v = null;
		String sql = "SELECT * FROM vendors "
						+ "Where Preapproved = true";
		try (  Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)) {
				ResultSet rs = ps.executeQuery();
			while (rs.next()){
	    		int vId = rs.getInt(1);
	    		String code = rs.getString(2);
	            String name = rs.getString(3);
				String address = rs.getString(4);
				String city = rs.getString(5);
				String state = rs.getString(6);
				String zip = rs.getString(7);
				String phone = rs.getString(8);
				String email = rs.getString(9);
				boolean preapproved = rs.getBoolean(10);
	            v = new Vendor(vId, code, name, address, city, state, zip, phone, email, preapproved);
	            vendors.add(v);
	    	}
	    } 
		catch (SQLException e) {
	        System.out.println(e);
	    }
	    return vendors;
}	
}
