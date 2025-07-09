package com.metaverse.memo.controller;

import com.metaverse.memo.domain.Memo;
import com.metaverse.memo.dto.MemoRequestDto;
import com.metaverse.memo.dto.MemoResponseDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        //Map to List
        List<MemoResponseDto> responseList = memoList.values().stream().map(MemoResponseDto::new).toList();

        return responseList;
    }

    @PutMapping("/memos/{id}")
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
        // 해당 id의 메모가 존재하는지 확인
        if(memoList.containsKey(id)){
            //해당 메모 가져오기
            Memo memo = memoList.get(id);

            //메모 수정
            memo.update(memoRequestDto);
            return memo.getId();
        }else{
            throw new IllegalArgumentException("Memo not found");
        }
    }
}
