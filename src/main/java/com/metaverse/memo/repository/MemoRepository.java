package com.metaverse.memo.repository;

import com.metaverse.memo.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemoRepository extends JpaRepository<Memo, Long> {
    List<Memo> findAllByOrderByModifiedAtDesc();
    List<Memo> findByContentsContainingOrderByModifiedAtDesc(String keyword);
}
