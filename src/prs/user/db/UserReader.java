package prs.user.db;

import prs.business.User;

public interface UserReader {
	
	public User getUser(String un);
}
