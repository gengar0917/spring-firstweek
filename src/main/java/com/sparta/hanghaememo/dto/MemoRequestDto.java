package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private String title;
    private String author;
    private String contents;
    private String password;

}
