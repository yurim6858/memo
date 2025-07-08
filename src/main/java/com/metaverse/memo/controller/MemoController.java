package com.metaverse.memo.controller;

import com.metaverse.memo.domain.Memo;
import com.metaverse.memo.dto.MemoRequestDto;
import com.metaverse.memo.dto.MemoResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MemoController {
    // 임시 데이터베이스(내장 메모리 배열)
    private final Map<Long,Memo> memoList = new HashMap<>();

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        //RequestDto -> Entity 변환
        Memo memo = new Memo(memoRequestDto);

        //(임시) 현재 Memo들의 최대 id 체크
        Long maxId = memoList.size() > 0 ? Collections.max(memoList.keySet())+1 : 1;
        memo.setId(maxId);

        //DB저장
        memoList.put(memo.getId(), memo);

        //Entity -> ResponseDto 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return memoResponseDto;
    }
}
