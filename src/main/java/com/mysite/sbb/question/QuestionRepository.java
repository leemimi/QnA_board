package com.mysite.sbb.question;

import com.mysite.sbb.question.Question;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.domain.Specification;
import java.util.List;


public interface QuestionRepository extends JpaRepository<Question,Integer> {

    Question findBySubject (String s);
    Question findBySubjectAndContent(String subject, String contnet);
    List<Question> findBySubjectLike(String subject);
    Page<Question> findAll(Specification<Question> spec, Pageable pageable);

    @Override
    Page<Question> findAll (Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "ALTER TABLE question AUTO_INCREMENT = 1",nativeQuery = true)
    void clearAutoIncrement();
}
