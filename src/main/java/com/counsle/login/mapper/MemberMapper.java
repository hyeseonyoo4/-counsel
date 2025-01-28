package com.nuriapp.login.mapper;

import com.nuriapp.login.domain.Member;

import java.util.List;

import com.nuriapp.login.dto.MemberDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    Member findByIdandPassword(@Param("id") String id, @Param("password") String password);

    List<Member> findMembersByNameAndTel(@Param("userName") String userName, @Param("tel") String tel);
    List<Member> findMembersByIdAndTel(@Param("id") String id, @Param("tel") String tel);
    Member findById(@Param("id") String id);
    int changePassword(@Param("password") String password, @Param("id") String id);
}
