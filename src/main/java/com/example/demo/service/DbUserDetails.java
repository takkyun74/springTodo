package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.example.demo.entity.Account;

public class DbUserDetails extends User {
	private static final long serialVersionUID = 1L;
    //ユーザ情報。
    private final Account account;

    public DbUserDetails(Account account,
            Collection<GrantedAuthority> authorities) {

        super(account.getName(), account.getPassword(),
                true, true, true, true, convertGrantedAuthorities(account.getRoles()));

//        super(account.getName(), account.getPassword(),
//                account.getEnable_flag(), true, true, true, authorities);
        
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    
    static Set<GrantedAuthority> convertGrantedAuthorities(String roles) {
        if (roles == null || roles.isEmpty()) {
          return Collections.emptySet();
        }
        Set<GrantedAuthority> authorities = Stream.of(roles.split(","))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toSet());
        return authorities;
      }
}