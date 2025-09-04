package com.pyoh07.simpleboard.mapper;

import com.pyoh07.simpleboard.domain.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    void insert(Member member);
    Member findByUsername(String username);
}
