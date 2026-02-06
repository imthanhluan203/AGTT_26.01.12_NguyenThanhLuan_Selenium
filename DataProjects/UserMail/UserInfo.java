package UserMail;

import Constant.Constant;

public class UserInfo {
	private String name;
	private String password;
	private String pid;
	
	
	public UserInfo(String name,String password) {
		this(name,password,password);
	}
	public UserInfo(String name,String password,String pid) {
		this.name = name;
		this.password = password;
		this.pid = pid;
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

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
	
	
	
}
