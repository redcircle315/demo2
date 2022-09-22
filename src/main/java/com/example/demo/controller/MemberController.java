package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;


@Controller // @Controller라고 해야 Controller로 인식
@Slf4j // Simple Logging Facade for Java

public class MemberController {

    @Autowired // 자동 객체 생성
    private MemberService memberService;

    // main.html DB연동 X
    @RequestMapping("/")
    public ModelAndView main() {
        log.info("========================== MemberController(/) ==================================");
        ModelAndView mv=new ModelAndView("/main");
        return mv;
    }

    // @RequestMapping("/member/list") = SimpleUrlHandlerMapping
    // localhost:8081/member/list 를 입력하면 해당 메서드 동작
    @RequestMapping("/member/list")
    public ModelAndView memberList() { // Controller 메서드명은 아무거나 해도 상관없다
        log.info("========================== MemberController(/member/list) ==================================");
        // 다음 화면 ---> src/main/resources/templates/member/list.html
        ModelAndView mv = new ModelAndView("/member/list");
        List<MemberDTO> list = memberService.findMemberList(); // @Autowired에서 객체 생성
        mv.addObject("list", list);
        return mv;
    }

    @RequestMapping("/member/detail")
    public ModelAndView memberdetail(
            @RequestParam String id
    ) {
        log.info("========================== MemberController(/member/detail) ==================================");
        ModelAndView mv = new ModelAndView("/member/detail");
        MemberDTO member = memberService.findMemberDetail(id); // @Autowired에서 객체 생성
        mv.addObject("member", member);
        return mv;
    }

    // 회원가입폼 / DB연동 X
    @RequestMapping("/member/register-form") // main.html ---> url 연결
    public ModelAndView registerForm() {
        log.info("========================== MemberController(/member/register-form) ==================================");
        ModelAndView mv=new ModelAndView("/member/registerForm"); // 다음 화면
        return mv;
    }

    @RequestMapping("/member/register")
    public ModelAndView registerMember(
            MemberDTO memberDTO, HttpServletRequest request
    ) {
        log.info("========================== MemberController(/member/register) ==================================");
        memberService.registerMember(memberDTO);
        ModelAndView mv=new ModelAndView("redirect:" + request.getContextPath() + "/member/list");
        return mv;
    }

    @RequestMapping("/member/remove")
    public ModelAndView deleteMember(
            @RequestParam String id,
            HttpServletRequest request
    ) {
        log.info("========================== MemberController(/member/remove) ==================================");
        memberService.removeMember(id);
        ModelAndView mv=new ModelAndView("redirect:" + request.getContextPath() + "/member/list");
        return mv;
    }
}