package com.pyoh07.simpleboard.service;

import com.pyoh07.simpleboard.domain.Member;
import com.pyoh07.simpleboard.mapper.MemberMapper;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberMapper memberMapper;
    //private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    // 회원가입
    public void register(Member member) {
        //String encodedPassword = passwordEncoder.encode(member.getPassword());
        //member.setPassword(encodedPassword);
        member.setPassword(member.getPassword());
        memberMapper.insert(member);
    }

    // 로그인
    public boolean login(String username, String rawPassword) {
        Member member = memberMapper.findByUsername(username);
        if (member == null) return false;
        //return passwordEncoder.matches(rawPassword, member.getPassword());
        return rawPassword.equals(member.getPassword());
    }

}
