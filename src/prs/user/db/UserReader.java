package prs.user.db;

import java.util.ArrayList;

import prs.business.User;

public interface UserReader {
	public User getUser(String un);
}
