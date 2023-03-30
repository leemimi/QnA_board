package com.mysite.sbb.question;


import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;
    @Column(length=200)
    private String subject;
    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;
    private LocalDateTime modifyDate;

    @OneToMany(mappedBy = "question",cascade = CascadeType.REMOVE)
    private List<Answer> answerList=new ArrayList<>();

    public void addAnswer (Answer a) {
        a.setQuestion(this);
        answerList.add(a);

    }
    @ManyToOne //여러개의 질문이 한개의 사용자에게 작성 될 수 있다
    private SiteUser author;

    @ManyToMany
    Set<SiteUser> voter;

    private int viewCount = 0;

}
