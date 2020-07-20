//package com.example.demo.app.task;
//
//import java.util.Collection;
//
//import org.springframework.security.core.GrantedAuthority;
//
//import com.example.demo.entity.Account;
//
//public class AuthLoginUser extends org.springframework.security.core.userdetails.User {
//	  // DBより検索したuserエンティティ
//	  // 認証・認可以外でアプリケーションから利用されるのでフィールドに定義
//	  private Account account;
//
//	  /**
//	   * データベースより検索したuserエンティティよりSpring Securityで使用するユーザー認証情報の
//	   * インスタンスを生成する
//	   *
//	   * @param user userエンティティ
//	   */
//	  public AuthLoginUser(Account account, Collection<GrantedAuthority> authorities) {
//	    super(account.getName(), account.getPassword(), account.getEnable_flag(), true, true,
//	        true, authorities);
//	    
////      super(account.getName(), account.getPassword(),
////      account.getEnable_flag(), true, true, true, authorities);
//	    this.account = account;
//	  }
//
//	  public Account getAccount() {
//	    return account;
//	  }
