package com.example.demo.repository;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

@Repository
public class LoginDaoImpl implements LoginDao {

	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
	public LoginDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public Optional<Account> findAccount(String name) {
		String sql =" SELECT ID, NAME , PASSWORD, ROLES, ENABLE_FLAG FROM USER WHERE NAME = ?";
		
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, name);
		
		Account account = new Account();
		account.setId((int)result.get("id"));
		account.setName((String)result.get("name"));
		account.setPassword((String)result.get("password"));
		account.setRoles((String)result.get("roles"));
		account.setEnable_flag((int)result.get("enable_flag"));
		
		Optional<Account> accOpt = Optional.ofNullable(account);
		
		
		return accOpt;
	}

}
