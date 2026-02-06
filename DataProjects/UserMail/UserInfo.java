package UserMail;

import Constant.Constant;

public class UserInfo {
	private String name;
	private String password;
	
	
	
	public UserInfo(String name,String password) {
		this.name = name;
		this.password = password;
	}
	
	public String getFullEmailName() {
		return String.format("%s%s",name,Constant.MAIL_TYPE);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
