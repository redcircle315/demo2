package com.example.demo.mapper;

import java.util.List;

import com.example.demo.dto.MemberDTO;
// MyBatis = ibatis
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
/*
@Mapper
MyBatis(memberMapper.xml)에 Mapper와 연동되는 파일이라는 것을 알려주는 역할
원래 Mapper(=DAO)에 있어야할 쿼리문이 xml 문서에 있다

MemberMapper와 memberMapper.xml을 연결시켜주는 문장
---> application.properties - mybatis.mapper-locations=classpath:/mapper/*.xml
*/
public interface MemberMapper {
    /*
    memberMapper.xml에
    id="selectMemberList" / id="selectMemberDetail"
    과 메서드명이 일치해야 한다(다르면 오류 발생)
    */
    List<MemberDTO> selectMemberList();

    // @Param("paramId")와 memberMapper.xml에 ID=#{paramId}와 일치해야 한다
    MemberDTO selectMemberDetail(@Param("paramId") String id);

    void insertMember(MemberDTO memberDTO);

    void deleteMember(@Param("paramId") String id);
}