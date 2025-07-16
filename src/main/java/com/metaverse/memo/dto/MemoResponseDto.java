package com.metaverse.memo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.metaverse.memo.domain.Memo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemoResponseDto {
    private Long id;
    private String username;
    private String contents;
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd/hh-mm-ss")
    private LocalDateTime modifiedAt;

    public MemoResponseDto(Memo memo) {
        this.id = memo.getId();
        this.username = memo.getUsername();
        this.contents = memo.getContents();
        this.modifiedAt = memo.getModifiedAt();
    }
}
