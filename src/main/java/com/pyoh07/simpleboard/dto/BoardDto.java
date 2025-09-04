package com.pyoh07.simpleboard.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdAt;
}
