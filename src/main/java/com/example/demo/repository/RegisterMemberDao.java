package com.example.demo.repository;

import java.util.Optional;

import com.example.demo.entity.Account;
import com.example.demo.entity.MemberRegistrationEntity;

public interface RegisterMemberDao {
	//会員情報をUSERテーブルにinsertする。
		void insertMemberInfo(MemberRegistrationEntity entity);
		
		Optional<Account> findUserName(String userName);
}
