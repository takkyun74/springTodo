package com.example.demo.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.entity.Account;

public class DbUserDetails extends User {
	private static final long serialVersionUID = 1L;
    //ユーザ情報。
    private final Account account;

    public DbUserDetails(Account account,
            Collection<GrantedAuthority> authorities) {

        super(account.getName(), account.getPassword(),
                true, true, true, true, authorities);

        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

}