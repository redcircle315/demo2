package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.dto.MemberDTO;
import com.example.demo.mapper.MemberMapper;


@Service
public class MemberServiceImpl implements MemberService {
    @Autowired // 자동 객체 생성
    private MemberMapper memberMapper; // Mapper = DAO

    @Override
    public List<MemberDTO> findMemberList() {
        return memberMapper.selectMemberList();
    }

    @Override
    public MemberDTO findMemberDetail(String id) {
        return memberMapper.selectMemberDetail(id);
    }

    @Override
    public void registerMember(MemberDTO memberDTO) {
        memberMapper.insertMember(memberDTO);
    }

    @Override
    public void removeMember(String id) {
        memberMapper.deleteMember(id);
    }
}