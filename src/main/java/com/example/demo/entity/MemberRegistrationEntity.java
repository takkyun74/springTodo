package com.example.demo.entity;

/**
 * DBに入れる値を格納するクラス。
 */
public class MemberRegistrationEntity {

	private String userName;

	private String password;

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