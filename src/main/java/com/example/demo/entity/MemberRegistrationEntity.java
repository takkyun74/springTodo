package com.example.demo.entity;

/**
 * DBに入れる値を格納するクラス。
 */
public class MemberRegistrationEntity {

	private String userName;

	private String password;
	
	private String roles;

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
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

}