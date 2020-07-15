package com.example.demo.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;
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
	
	@Override
	public Optional<Account> findUserName(String userName)  {
		
		String sql = "SELECT ID , NAME FROM USER WHERE NAME = ?  ";
		
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, userName);
		
		Account account = new Account();
		account.setId((int)result.get("id"));
		account.setName((String)result.get("name"));
		
		Optional<Account> userOpt = Optional.ofNullable(account);
		
		return userOpt;
		
	}

}
