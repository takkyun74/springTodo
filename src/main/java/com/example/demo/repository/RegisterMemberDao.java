package com.example.demo.repository;

import com.example.demo.entity.MemberRegistrationEntity;

public interface RegisterMemberDao {
	//会員情報をUSERテーブルにinsertする。
		void insertMemberInfo(MemberRegistrationEntity entity);
}
