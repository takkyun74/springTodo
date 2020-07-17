package com.example.demo.entity;

/**
 * ログインに必要な会員情報を格納するクラス。
 */
public class Account {
	
	private int id;

    private String name;

    private String password;
    
    private String roles;
    
    private int enable_flag;

    
    
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
	public int getId() {
    	return id;
    }
    public void setId(int id) {
    	this.id = id;
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