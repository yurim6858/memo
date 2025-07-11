package com.metaverse.memo.controller;

import com.metaverse.memo.domain.Memo;
import com.metaverse.memo.dto.MemoRequestDto;
import com.metaverse.memo.dto.MemoResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

@RestController
@RequestMapping("/api")
public class MemoController {
    // 임시 데이터베이스(내장 메모리 배열)
    private final JdbcTemplate jdbcTemplate;

    public MemoController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/memos")
    public MemoResponseDto createMemo(@RequestBody MemoRequestDto memoRequestDto) {
        //RequestDto -> Entity 변환
        Memo memo = new Memo(memoRequestDto);

        //DB저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환 받기 위한 객체

        String sql = "INSERT INTO memo (username, contents) VALUES (?, ?)";
        jdbcTemplate.update(con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, memo.getUsername());
            preparedStatement.setString(2, memo.getContents());
            return preparedStatement;
        }, keyHolder);

        //DB INSERT 후 받아온 기본 키 확인
        Long id = keyHolder.getKey().longValue();
        memo.setId(id);

        //Entity -> ResponseDto 변환
        MemoResponseDto memoResponseDto = new MemoResponseDto(memo);
        return memoResponseDto;
    }


    @GetMapping("/memos")
    public List<MemoResponseDto> getMemos(){
        // DB 조회
        String sql = "SELECT * FROM memo";
        List<MemoResponseDto> responseList = jdbcTemplate.query(sql,new RowMapper<MemoResponseDto>() {
           @Override
           public MemoResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException{
               Long id = rs.getLong("id");
               String username = rs.getString("username");
               String contents = rs.getString("contents");
               return new MemoResponseDto(id,username,contents);
           }
        });

        return responseList;
    }

//    @PutMapping("/memos/{id}")
//    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto memoRequestDto) {
//        // 해당 id의 메모가 존재하는지 확인
//        if(memoList.containsKey(id)){
//            //해당 메모 가져오기
//            Memo memo = memoList.get(id);
//
//            //메모 수정
//            memo.update(memoRequestDto);
//            return memo.getId();
//        }else{
//            throw new IllegalArgumentException("Memo not found");
//        }
//    }
//
//    @DeleteMapping("/memos/{id}")
//    public Long deleteMemo(@PathVariable Long id) {
//        if(memoList.containsKey(id)){
//            // 메모 삭제
//            memoList.remove(id);
//            return id;
//        } else {
//            throw new IllegalArgumentException("Memo not found");
//        }
//    }
}
