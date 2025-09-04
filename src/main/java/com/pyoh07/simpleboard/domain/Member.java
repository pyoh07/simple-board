package com.pyoh07.simpleboard.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Member {
    private Long id;
    private String username;
    private String password;
    private String email;
    private LocalDateTime createdAt;
}
