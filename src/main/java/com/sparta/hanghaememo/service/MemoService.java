package com.sparta.hanghaememo.service;

import com.sparta.hanghaememo.dto.MemoRequestDto;
import com.sparta.hanghaememo.dto.MemoResponseDto;
import com.sparta.hanghaememo.entity.Memo;
import com.sparta.hanghaememo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    //메모 생성
    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return new MemoResponseDto(memo);
    }

    //메모 전체 조회
    @Transactional(readOnly = true)
    public List<MemoResponseDto> getMemos() {
        List<Memo> lists = memoRepository.findAllByOrderByModifiedAtDesc();

        List<MemoResponseDto> memos = new ArrayList();

        for(Memo memo : lists){
            memos.add(new MemoResponseDto(memo));
        }

        return memos;
    }

    //메모 수정
    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        boolean isEquals = requestDto.getPassword().equals(memo.getPassword());
        if(isEquals == true){
            memo.update(requestDto);
        }else {
            MessageService.updateMessage(isEquals);
        }
        return new MemoResponseDto(memo);
    }

    //메모 삭제
    @Transactional
    public String deleteMemo(Long id, Map password) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        String realPw = String.valueOf(password.get("password"));
        boolean isSuccess = realPw.equals(memo.getPassword());
        if(isSuccess == true){
            memoRepository.deleteById(id);
        }
        return MessageService.deleteMessage(isSuccess);
    }

    //메모 상세 조회 (id 틀릴 때 MessageService.getMemo(); 출력 안됨)
    public MemoResponseDto getMemo(Long id) {
        List<Memo> lists = memoRepository.findAllByOrderByModifiedAtDesc();

        List<MemoResponseDto> memos = new ArrayList();
        MemoResponseDto selectedMemo = null;

        for (Memo memo : lists) {
            if (memo.getId() == id) {
                selectedMemo = new MemoResponseDto(memo);
            } else {
                MessageService.getMemo();
            }
        }
        return selectedMemo;
    }
}
