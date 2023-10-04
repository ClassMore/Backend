package com.dev.classmoa.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private LocalDateTime writeDate;
    private Boolean isModified;
    private Boolean isDeleted;


    @Builder(builderMethodName = "creater", buildMethodName = "create")
    public Opinion(String content) {
        this.content = content;
    }

    @Builder(builderMethodName = "finder", buildMethodName = "find")
    public Opinion(Long id) { this.id = id; }

    @Builder(builderMethodName = "editer", buildMethodName = "edit")
    public Opinion(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Builder
    public Opinion(String content, boolean isModified, Member member, Lecture lecture, boolean isDeleted) {
        this.content = content;
        this.isModified = isModified;
        this.isDeleted = isDeleted;
        this.member = member;
        this.lecture = lecture;
    }

    // flag로 삭제처리하기 위한 함수
    public void deleteOpinion(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }

    public void editOpinion(String content){
        this.content = content;
        this.isModified = true;
    }

    // public void setLecture(Lecture lecture){
    //     lecture.getOpinions().add(this);
    //     this.lecture = lecture;
    // }
    //
    // public void setMember(Member member){
    // }
}
