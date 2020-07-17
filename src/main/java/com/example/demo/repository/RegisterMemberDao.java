package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Account;
import com.example.demo.entity.MemberRegistrationEntity;
import com.example.demo.entity.Task;

public interface RegisterMemberDao {
	//会員情報をUSERテーブルにinsertする。
		void insertMemberInfo(MemberRegistrationEntity entity);
		
		Optional<Account> findUserName(String userName);
		
		List<Account> findAllAccount();
		
		Optional<Account> findById(int id);
		
		int update(Account account);
		
		int deleteById(int id);
}
