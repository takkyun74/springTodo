package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.MemberRegistrationEntity;

@Repository
public class RegisterMemberDaoImpl implements RegisterMemberDao {
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public RegisterMemberDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void insertMemberInfo(MemberRegistrationEntity entity) {
		jdbcTemplate.update("INSERT INTO USER(NAME, PASSWORD) VALUES(?, ?)",
				entity.getUserName(), entity.getPassword() );

	}

}
