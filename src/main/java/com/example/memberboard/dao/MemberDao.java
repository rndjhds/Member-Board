package com.example.memberboard.dao;

import org.apache.ibatis.annotations.Mapper;

import com.example.memberboard.model.Member;

@Mapper
public interface MemberDao {

    Member findByUsername(String username);

    void insert(Member member);

}
