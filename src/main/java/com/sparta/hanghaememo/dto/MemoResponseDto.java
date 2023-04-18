package com.sparta.hanghaememo.dto;

import com.sparta.hanghaememo.entity.Memo;
import lombok.Getter;

@Getter
public class MemoResponseDto {

    private String createdAt;
    private String modifiedAt;
    private Long id;
    private String title;
    private String content;
    private String author;

    public MemoResponseDto(Memo memo) {
        this.createdAt = String.valueOf(memo.getCreatedAt());
        this.modifiedAt = String.valueOf(memo.getModifiedAt());
        this.id = memo.getId();
        this.title = memo.getTitle();
        this.content = memo.getContents();
        this.author = memo.getAuthor();
    }

}
