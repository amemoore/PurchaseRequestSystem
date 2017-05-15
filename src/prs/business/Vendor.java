package prs.business;

public class Vendor {

	private int vId;
	private String code;
	private String name;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private String email;
	private boolean preapproved;

public Vendor(int v, String cd, String nm, String add, String cty, String st, String zp, String ph, String e, boolean pre){
	vId = v;
	code = cd;
	name = nm;
	address = add;
	city = cty;
	state = st;
	zip = zp;
	phone = ph;
	email = e;
	preapproved = pre;
}
public Vendor(){
}
public Vendor(String nm){
	name = nm;
}

public int getvId() {
	return vId;
}
public void setvId(int vId) {
	this.vId = vId;
}
public String getCode() {
	return code;
}
public void setCode(String code) {
	this.code = code;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getZip() {
	return zip;
}
public void setZip(String zip) {
	this.zip = zip;
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
public boolean isPreapproved() {
	return preapproved;
}
public void setPreapproved(boolean preapproved) {
	this.preapproved = preapproved;
}
@Override
public String toString(){
	return vId + " " + name +"\t"+ phone +"\t"+ email +"\t"+ preapproved;
}
}
