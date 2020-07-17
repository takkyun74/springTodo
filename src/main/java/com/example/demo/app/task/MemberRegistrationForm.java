package com.example.demo.app.task;

/**
 * 会員登録フォームに入力された値を格納するためのクラス。
 */
public class MemberRegistrationForm {
	private int id;
	
	private String userName;

	private String password;
	
	private String roles;
	
	private int enable_flag;
	
	
	public MemberRegistrationForm() {}
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public int getEnable_flag() {
		return enable_flag;
	}

	public void setEnable_flag(int enable_flag) {
		this.enable_flag = enable_flag;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userId) {
		this.userName = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}