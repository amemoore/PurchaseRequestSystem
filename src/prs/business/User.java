package prs.business;

public class User {
	private int uID;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private boolean manager;

public User(int u, String uN, String pW, String fN, String lN, String p, String e, boolean m){
	uID = u;
	userName = uN;
	password = pW;
	firstName = fN;
	lastName = lN;
	phone = p;
	email = e;
	manager = m;
}
public User(String uN, String pW, String fN, String lN, String p, String e, boolean m){
	userName = uN;
	password = pW;
	firstName = fN;
	lastName = lN;
	phone = p;
	email = e;
	manager = m;
}
	
public User(){
}


public int getuID() {
	return uID;
}

public void setuID(int uID) {
	this.uID = uID;
}

public String getUserName() {
	return userName;
}

public void setUserName(String userName) {
	this.userName = userName;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFirstName() {
	return firstName;
}

public void setFirstName(String firstName) {
	this.firstName = firstName;
}

public String getLastName() {
	return lastName;
}

public void setLastName(String lastName) {
	this.lastName = lastName;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public boolean isManager() {
	return manager;
}

public void setManager(boolean manager) {
	this.manager = manager;
}
}