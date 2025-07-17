package com.metaverse.memo.service;

import com.metaverse.memo.domain.Memo;
import com.metaverse.memo.dto.MemoRequestDto;
import com.metaverse.memo.dto.MemoResponseDto;
import com.metaverse.memo.repository.MemoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public MemoResponseDto createMemo(MemoRequestDto memoRequestDto) {
        //RequestDto -> Entity 변환
        Memo memo = new Memo(memoRequestDto);
        Memo savedMemo = memoRepository.save(memo);
        //Entity -> ResponseDto 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(savedMemo);
        return memoResponseDto;
    }

    public List<MemoResponseDto> getMemos(){
        //기본 조회 메서드 호출
        //List<MemoResponseDto> responseList = memoRepository.findAll().stream().map(MemoResponseDto::new).toList();
        //내림차순 조회 메서드 호출
        List<MemoResponseDto> responseList = memoRepository.findAllByOrderByModifiedAtDesc().stream().map(MemoResponseDto::new).toList();
        return responseList;
    }


    @Transactional
    public Long updateMemo(Long id, MemoRequestDto memoRequestDto) {
        Memo memo = findMemo(id);
        memo.update(memoRequestDto);
        return id;
    }

    public Long deleteMemo(Long id){
        Memo memo = findMemo(id);
        memoRepository.delete(memo);
        return id;
    }

    private Memo findMemo(Long id){
        return memoRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 메모는 존재하지 않습니다.")
        );
    }

    public List<MemoResponseDto> getMemosByKeyword(String keyword){
        return memoRepository.findByContentsContainingOrderByModifiedAtDesc(keyword).stream().map(MemoResponseDto::new).toList();
    }
}
