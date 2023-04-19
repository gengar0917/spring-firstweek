package com.sparta.hanghaememo.controller;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    //ModelAndView에 데이터와 뷰의 정보 저장
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    //메모 생성
    //@RequestBody로 프론트에서 받는 정보 MemoRequestDto로 받음
    //memoService의 createMemo메서드를 이용해 Service 계층으로 들어감 (매개변수 requestDto)
    @PostMapping("/api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
        return memoService.createMemo(requestDto);
    }

    //메모 전체 조회
    //memoService의 getMemos메서드를 이용해 Service 계층으로 들어감
    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos(){
        return memoService.getMemos();
    }

    //메모 상세 조회
    //@PathVariable로 id를 받음 (url 옆에 붙는 id 값)
    //memoService의 getMemo메서드를 이용해 Service 계층으로 들어감 (매개변수 id)
    @GetMapping("/api/memos/{id}")
    public MemoResponseDto getMemo(@PathVariable Long id){
        return memoService.getMemo(id);
    }

    //메모 수정
    //@PathVariable로 id를 받음 (url 옆에 붙는 id 값)
    //@RequestBody로 프론트에서 받는 정보 MemoRequestDto로 받음
    //memoService의 updateMemo메서드를 이용해 Service 계층으로 들어감 (매개변수 id, requestDto)
    @PutMapping("/api/memos/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.update(id, requestDto);
    }

    //메모 삭제
    //@PathVariable로 id를 받음 (url 옆에 붙는 id 값)
    //@RequestBody로 프론트에서 받는 정보 Map 타입으로 받음
    // (password라 String으로 받아도 가능하지만 그렇게 되면 json 형식 그대로 받아져서 비교가 불가능해짐)
    //memoService의 deleteMemo메서드를 이용해 Service 계층으로 들어감 (매개변수 id, password)
    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody Map<String, String> password){
        return memoService.deleteMemo(id, password);
    }
}
