package com.mysite.sbb;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Optional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class QnaApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @BeforeEach
    void beforeEach(){
        questionRepository.deleteAll();
        questionRepository.clearAutoIncrement();

        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);  // 첫번째 질문 저장

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);  // 두번째 질문 저장

    }
//    @Test
//    @DisplayName("데이터 저장")
//    void t001() {
//        // 질문 1개 생성
//        Question q = new Question();
//        q.setSubject("세계에서 가장 부유한 국가가 어디인가요?");
//        q.setContent("알고 싶습니다.");
//        q.setCreateDate(LocalDateTime.now());
//        questionRepository.save(q);
//
//        assertEquals("세계에서 가장 부유한 국가가 어디인가요?", questionRepository.findById(3).get().getSubject());
//    }

    @Test
    void testJpa1 () {
        List<Question> all = this.questionRepository.findAll();
        assertEquals(2, all.size());

        Question q = all.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    void testJpa2 () {
        Question q = this.questionRepository.findBySubject("sbb가 무엇인가요?");
        assertEquals(1, q.getId());
    }

    @Test
    void testJpa3 () {
        Optional<Question> oq = this.questionRepository.findById(1);
        if (oq.isPresent()) {
            Question q = oq.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }
    @Test
    void testJpa4(){
        Question q = this.questionRepository.findBySubjectAndContent(
                "sbb가 무엇인가요?","sbb에 대해서 알고 싶습니다.");
            assertEquals(1,q.getId());
    }
    @Test
    void testJpa5(){
        List<Question> qList = this.questionRepository.findBySubjectLike("sbb%");
        Question q = qList.get(0);
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }
    @Test
    void testJpa7(){
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        this.questionRepository.save(q);
    }

    @Test
    void testJpa6(){
        assertEquals(2,questionRepository.count());
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        this.questionRepository.delete(q);
        assertEquals(1,this.questionRepository.count());
    }

}
