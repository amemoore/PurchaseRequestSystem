package prs.user.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import prs.business.User;
import prs.business.Vendor;
import prs.request.db.RequestDAO;
import prs.util.DBUtil;

public class UserDB implements RequestDAO, UserDAO {
	ArrayList<User> users;
	
	public User getUser(String un){
		//users = new ArrayList<User>();
		User user = new User();
		String sql = " SELECT * FROM users "
					+ " where UserName = ? ";
		try (Connection connection = DBUtil.getConnection();
				PreparedStatement ps = connection.prepareStatement(sql)){
					ps.setString(1,  un); 
					ResultSet rs = ps.executeQuery();
        	while (rs.next()){
        		int uId = rs.getInt(1);
        		String userName = rs.getString(2);
                String password = rs.getString(3);
				String firstName = rs.getString(4);
				String lastName = rs.getString(5);
				String phone = rs.getString(6);
				String email = rs.getString(7);
				boolean manager = rs.getBoolean(8);
                user = new User(uId, userName, password, firstName, lastName, phone, email, manager);
                //users.add(u);
        	}
        } 
		catch (SQLException e) {
            System.out.println(e);
        }
		return user;
	}
	
	//add user registering info to the db
	public void addUser(User u) {
		String sql
                = "INSERT INTO users (UserName, Password, FirstName, LastName, Phone, Email, Manager) "
                + "VALUES (?,?,?,?,?,?,?)";
        try ( Connection connection = DBUtil.getConnection();
        		PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getPhone());
            ps.setString(6, u.getEmail());
            ps.setBoolean(7, u.isManager());
            ps.executeUpdate();
        } 
        catch (SQLException e) {
            System.out.println(e);
        }
    }
	
}
