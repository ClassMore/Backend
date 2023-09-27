package com.dev.classmoa.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "opinion_id")
    private Opinion opinion;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;
    private LocalDateTime writeDate;
    private Boolean isModified;
    private Boolean isDeleted;


    public Comment(String content) {
        this.content = content;
    }

    @Builder(builderMethodName = "creater", buildMethodName = "create")
    public Comment(String content, Opinion opinion, Boolean isModified) {
        this.content = content;
        this.opinion = opinion;
        this.isModified = isModified;
    }

    @Builder(builderMethodName = "deleter", buildMethodName = "delete")
    public Comment(Long id) {
        this.id = id;
    }

    @Builder(builderMethodName = "editer", buildMethodName = "edit")
    public Comment(Long id, String content, Boolean isModified) {
        this.id = id;
        this.content = content;
        this.isModified = isModified;
    }

    @Builder
    public Comment(String content, boolean isModified, Member member, Opinion opinion) {
        this.content = content;
        this.isModified = isModified;
        this.member = member;
        this.opinion = opinion;
    }

    public void deleteComment(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public void editComment(String content) {
        this.content = content;
        this.isModified = true;
    }


    // public void setOpinion(Opinion opinion){
    //     this.opinion = opinion;
    //     opinion.getComments().add(this);
    // }

}
