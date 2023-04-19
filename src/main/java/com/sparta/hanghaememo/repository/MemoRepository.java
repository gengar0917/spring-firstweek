package com.sparta.hanghaememo.repository;


import com.sparta.hanghaememo.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {

    //메모를 수정시간 기준으로 내림차순으로 정렬하는 메서드를 만듦
    List<Memo> findAllByOrderByModifiedAtDesc();
}