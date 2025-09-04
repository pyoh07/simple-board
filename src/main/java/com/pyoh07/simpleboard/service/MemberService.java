package com.pyoh07.simpleboard.service;

import com.pyoh07.simpleboard.domain.Member;
import com.pyoh07.simpleboard.mapper.MemberMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class MemberService implements UserDetailsService {
    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public MemberService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    public void register(Member member) {
        String encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);
        memberMapper.insert(member);
    }

    // UserDetailsService 구현
    @Override
    public UserDetails loadUserByUsername(String username) {
        Member member = memberMapper.findByUsername(username);
        if (member == null) {
            throw new RuntimeException("사용자를 찾을 수 없습니다.");
        }
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        // 필요하면 roles 추가 가능: authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        return new User(member.getUsername(), member.getPassword(), authorities);
    }

}
