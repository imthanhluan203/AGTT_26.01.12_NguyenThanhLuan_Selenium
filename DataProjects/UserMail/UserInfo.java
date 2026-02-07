package UserMail;


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
	
	public String getUserName() {
		return name.substring(0, name.indexOf("@"));
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
