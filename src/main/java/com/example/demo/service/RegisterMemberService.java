package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.MemberRegistrationEntity;
import com.example.demo.repository.RegisterMemberDao;

@Service
@Transactional
public class RegisterMemberService {
	
	@Autowired
	RegisterMemberDao registerMemberDao;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	
	
	
	//会員情報をDBに登録
	public void registerMember(MemberRegistrationEntity entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		
		registerMemberDao.insertMemberInfo(entity);
	}
	
	

}
