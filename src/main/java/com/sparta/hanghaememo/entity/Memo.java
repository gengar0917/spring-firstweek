package com.sparta.hanghaememo.entity;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Memo extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false) //이게 받는 값
    private String author;

    @Column(nullable = false) //이게 받는 값
    private String contents;

    @Column(nullable = false) //이게 받는 값
    private String title;

    @Column(nullable = false) //이게 받는 값
    private String password;

    //MemoService에서 memoCreate 메서드가 requestDto를 인자로 전달하면
    // 해당 매개변수를 이용해 memo를 초기화 한다.
    public Memo(MemoRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    //MemoService.updateMemo 메서드를 사용할 때 인자로 전달되는 requestDto를
    //받아 메모를 수정하기 위한 메서드
    public void update(MemoRequestDto requestDto) {
        this.author = requestDto.getAuthor();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }
}