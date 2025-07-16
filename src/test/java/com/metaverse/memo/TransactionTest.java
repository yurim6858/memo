package com.metaverse.memo;

import com.metaverse.memo.domain.Memo;
import com.metaverse.memo.repository.MemoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
public class TransactionTest {
    @PersistenceContext
    EntityManager em;

    @Autowired
    MemoRepository memoRepository;

    @Test
    @DisplayName("메모 생성 성공")
    @Transactional
    @Rollback(false)
    void test1(){
        Memo memo = new Memo();
        memo.setUsername("test");
        memo.setContents("@Transactional 동작 테스트 중");

        em.persist(memo);
    }

    @Test
    @DisplayName("메모 생성 실패")
    void test2(){
        Memo memo = new Memo();
        memo.setUsername("test");
        memo.setContents("메모 생성 테스트 실패");

        em.persist(memo);
    }
}
