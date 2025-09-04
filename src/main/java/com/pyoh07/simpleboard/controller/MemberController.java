package com.pyoh07.simpleboard.controller;

import com.pyoh07.simpleboard.domain.Member;
import com.pyoh07.simpleboard.service.MemberService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    // 회원가입 폼
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("member", new Member());
        return "member-register";
    }

    // 회원가입 처리
    @PostMapping("/register")
    public String register(@ModelAttribute Member member) {
        memberService.register(member);
        return "redirect:/members/login";
    }

    // 로그인 폼
    @GetMapping("/login")
    public String loginForm() {
        return "member-login";
    }

    // 로그인 처리
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        Model model) {
        boolean success = memberService.login(username, password);
        if (success) {
            session.setAttribute("username", username);
            return "redirect:/boards";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "member-login";
        }
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/members/login";
    }
}
