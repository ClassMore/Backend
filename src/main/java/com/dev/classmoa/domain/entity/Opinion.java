package com.dev.classmoa.domain.entity;

import com.dev.classmoa.dto.opinion.request.DeleteOpinionRequest;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Opinion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")
    private Lecture lecture;

    @OneToMany(mappedBy = "opinion", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    private String content;

    @CreationTimestamp
    private LocalDateTime writeDate;

    private Boolean isModified = false;
    private Boolean isDeleted = false;

    @Builder
    public Opinion(String content, Member member, Lecture lecture) {
        this.content = content;
        this.member = member;
        this.lecture = lecture;
    }

     public void editOpinion(String content){
        this.content = content;
        this.isModified = true;
    }

    public void deleteOpinion(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }

}
