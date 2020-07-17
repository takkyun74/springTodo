package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Account;
import com.example.demo.entity.MemberRegistrationEntity;

public interface RegisterMemberService {

	//会員情報をDBに登録
	void registerMember(MemberRegistrationEntity entity);

	//会員情報取得
	Optional<Account> getAccount(String userName);
	
	List<Account> findAllAccount();
	
	Optional<Account> getAccountId(int id);
	
	void update(Account account);
	
	void deleteById(int id);

}