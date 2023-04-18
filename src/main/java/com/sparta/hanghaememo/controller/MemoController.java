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

    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("index");
    }

    //메모 생성
    @PostMapping("/api/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto requestDto){
        return memoService.createMemo(requestDto);
    }

    //메모 전체 조회
    @GetMapping("/api/memos")
    public List<MemoResponseDto> getMemos(){
        return memoService.getMemos();
    }

    //메모 상세 조회
    @GetMapping("/api/memos/{id}")
    public MemoResponseDto getMemo(@PathVariable Long id){
        return memoService.getMemo(id);
    }

    //메모 수정
    @PutMapping("/api/memos/{id}")
    public MemoResponseDto updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto){
        return memoService.update(id, requestDto);
    }

    //메모 삭제
    @DeleteMapping("/api/memos/{id}")
    public String deleteMemo(@PathVariable Long id, @RequestBody Map<String, String> password){
        return memoService.deleteMemo(id, password);
    }
}
