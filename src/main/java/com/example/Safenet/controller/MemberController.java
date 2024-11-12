package com.example.Safenet.controller;

import com.example.Safenet.dto.MemberDTO;
import com.example.Safenet.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 페이지 이동
    @GetMapping("/signup")
    public String signup() {
        log.info("회원가입 페이지 이동");
        return "signup";  // 회원가입 페이지 경로
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String register(@RequestBody MemberDTO dto, RedirectAttributes redirectAttributes) {
        log.info("회원가입 요청: " + dto);
        String id = memberService.register(dto);
        if (id != null && !id.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg", "회원가입이 성공적으로 완료되었습니다.");
            log.info("회원가입이 성공했다링");
        } else {
            redirectAttributes.addFlashAttribute("msg", "회원가입에 실패했습니다.");
            log.info("회원가입이 실패했어연");
        }
        return "redirect:/login";  // 로그인 페이지로 리다이렉트
    }

    // 로그인 페이지 이동
    @GetMapping("/login")
    public String login() {
        log.info("로그인 페이지 이동");
        return "/login";  // 로그인 페이지 경로
    }

    // 로그인 처리
    @PostMapping("/login")
    public String doLogin(@RequestBody MemberDTO dto, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        log.info("로그인 요청: " + dto);
        MemberDTO memberDTO = memberService.login(dto);
        if (memberDTO == null) {
            redirectAttributes.addFlashAttribute("msg", "로그인에 실패했습니다.");
            return "redirect:/login";
        }
        log.info("로그인 성공");
        // 세션에 사용자 정보 저장
        HttpSession session = request.getSession();
        session.setAttribute("user", memberDTO);
        return "redirect:/home";  // 로그인 후 이동할 페이지 경로
    }

    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        log.info("로그아웃 요청");

        HttpSession session = request.getSession();
        session.invalidate();  // 세션 초기화
        return "redirect:/home";  // 로그아웃 후 이동할 페이지 경로
    }
}
