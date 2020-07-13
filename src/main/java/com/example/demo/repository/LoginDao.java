package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.Account;


public interface LoginDao {
	
	Optional <Account> findAccount(String name);

}
