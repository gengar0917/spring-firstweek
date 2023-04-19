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
    //@Transactional을 이용해 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
    //Memo (entitiy) 에 인자로 requestDto를 전달해 메모의 생성자로 초기화 한다.
    //memoRepository를 이용해 해당 메모를 저장하고
    //memo를 다시 MemoResponsoDto의 인자로 전달해 memo와 같은 내용을 가진 ResponseDto를 반환한다.
    @Transactional
    public MemoResponseDto createMemo(MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        memoRepository.save(memo);
        return new MemoResponseDto(memo);
    }

    //메모 전체 조회
    //@Transactional을 이용해 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
    //memoRepository에 만들어둔 findAllByOrderByModifiedAtDesc 메서드를 이용해 수정 시간을 기준으로
    //내림차순으로 정렬해 메모 배열인 lists에 저장한다.
    //MemoResponseDto 배열인 memos를 새로 생성하고
    //for문을 이용해 lists를 하나씩 꺼내 memos에 저장해 memos를 반환한다.
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
    //@Transactional을 이용해 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
    //memoRepository.findById에 인자값으로 id를 전달해 id가 일치할 시 memo 객체를 생성하고
    //일치하지 않을 시 예외 처리를 통해 에러 메세지를 출력한다.
    //전달받은 requestDto에서 getPassword() 메서드를 통해 해당 패스워드와 memo 객체의 password가 같은지 비교,
    //결과를 boolean 타입의 isEquals 변수에 담고 해당 변수가 true라면 memo.update()메서드를 실행하고 (인자값 requestDto)
    //false라면 MessageService.updateMessage() 메서드를 실행해 에러 메세지를 반환하게 한다. (인자값 isEquals)
    //마지막으로 memo 객체와 동일한 MemoResponseDto를 반환하여 끝낸다.
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
    //@Transactional을 이용해 모든 작업들이 성공해야만 최종적으로 데이터베이스에 반영하도록 한다.
    //memoRepository.findById에 인자값으로 id를 전달해 id가 일치할 시 memo 객체를 생성하고
    //일치하지 않을 시 예외 처리를 통해 에러 메세지를 출력한다.
    //Map 타입의 password 중 value 값을 꺼내 String 타입의 realPw 변수에 저장한다.
    //저장한 해당 패스워드와 memo 객체의 password가 같은지 비교,
    //결과를 boolean 타입의 isEquals 변수에 담고 해당 변수가 true라면 memoRepository.deleteById()메서드를 실행하여 (인자값 id)
    //해당 메모를 삭제하고 마지막으로 MessageService.deleteMessage() 메서드를 실행해 에러 메세지를 반환하게 한다. (인자값 isEquals)
    //isEquals의 값에 따라 true면 삭제 성공을, false면 비밀번호 불일치를 반환한다.
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

    //메모 상세 조회
    //memoRepository에 만들어둔 findAllByOrderByModifiedAtDesc 메서드를 이용해 수정 시간을 기준으로
    //내림차순으로 정렬해 메모 배열인 lists에 저장한다.
    //MemoResponseDto 배열인 memos를 새로 생성하고
    //MemoResponseDto 타입인 selectedMemo 변수를 생성한다.
    //for문을 이용해 lists에서 하나씩 꺼내 비교하며 id가 같을시 selectedMemo에 넣어 반환하고
    //selectedMemo가 null일시 MessageService.getMemo를 이용해 에러 메세지를 출력한다.
    public MemoResponseDto getMemo(Long id) {
        List<Memo> lists = memoRepository.findAllByOrderByModifiedAtDesc();

        List<MemoResponseDto> memos = new ArrayList();
        MemoResponseDto selectedMemo = null;

        for (Memo memo : lists) {
            if (memo.getId() == id) {
                selectedMemo = new MemoResponseDto(memo);
            }

            if(selectedMemo == null){
                MessageService.getMemo();
            }

        }
        return selectedMemo;
    }
}
