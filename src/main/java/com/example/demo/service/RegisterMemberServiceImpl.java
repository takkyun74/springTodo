package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Account;
import com.example.demo.entity.MemberRegistrationEntity;
import com.example.demo.entity.Task;
import com.example.demo.repository.RegisterMemberDao;

@Service
public class RegisterMemberServiceImpl implements RegisterMemberService {
	
	private final RegisterMemberDao registerMemberDao;
	
	@Autowired
	public RegisterMemberServiceImpl(RegisterMemberDao registerMemberDao) {
		this.registerMemberDao = registerMemberDao;
	}
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	
	//会員情報をDBに登録
	@Override
	public void registerMember(MemberRegistrationEntity entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		
		registerMemberDao.insertMemberInfo(entity);
	}
	
	
	//会員情報取得
	@Override
	public Optional<Account> getAccount(String userName) {
		// Optional<Account>一件を取得 userNameが無ければ例外発生
		try {
			return registerMemberDao.findUserName(userName);
		} catch (EmptyResultDataAccessException e) {
			throw new TaskNotFoundException("指定されたアカウントが存在しません");
	}
	}

}
