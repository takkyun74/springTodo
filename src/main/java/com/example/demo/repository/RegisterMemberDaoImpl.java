package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
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
		jdbcTemplate.update("INSERT INTO USER(NAME, PASSWORD, ROLES) VALUES(?, ?, ?)",
				entity.getUserName(), entity.getPassword(), entity.getRoles());

	}
	
	//リストを返す
	@Override
	public List<Account> findAllAccount() {
		
		String sql = "SELECT * FROM user ";
				
		//タスク一覧をMapのListで取得
		List<Map<String, Object>> resultAccountList = jdbcTemplate.queryForList(sql);
		
		//return用の空のListを用意
		List<Account> list = new ArrayList<Account>();
		
		//二つのテーブルのデータをTaskにまとめる
		for(Map<String, Object> result : resultAccountList) {
			
			Account account = new Account();
			account.setId((int)result.get("id"));
			account.setName((String)result.get("name"));
			account.setPassword((String)result.get("password"));
			account.setRoles((String)result.get("roles"));
			account.setEnable_flag((int)result.get("enable_flag"));
			
			list.add(account);
		}
		return list;
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
	
	@Override
	public Optional<Account> findById(int id) {
		String sql = "SELECT * FROM USER WHERE id = ?";
		
		//タスクを一件取得
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id);
		
		Account account = new Account();
		account.setId((int)result.get("id"));
		account.setName((String)result.get("name"));
		account.setPassword((String)result.get("password"));
		account.setRoles((String)result.get("roles"));
		account.setEnable_flag((int)result.get("enable_flag"));
		
		
		Optional<Account> accountOpt = Optional.ofNullable(account);
		
		return accountOpt;
	}
	
	@Override
	public int update(Account account) {
		if(account.getPassword().isEmpty() || account.getPassword() == null) {
			return jdbcTemplate.update("UPDATE user SET id = ?, name = ?, roles = ?, enable_flag = ? WHERE id = ?",
					account.getId(), account.getName(), account.getRoles(), account.getEnable_flag(),account.getId());
			
		} else {
		
			return jdbcTemplate.update("UPDATE user SET id = ?, name = ?, password = ?,roles = ?, enable_flag = ? WHERE id = ?",
				account.getId(), account.getName(), account.getPassword(), account.getRoles(), account.getEnable_flag(),account.getId() );
		}
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM user WHERE id = ?", id);
	}

}
