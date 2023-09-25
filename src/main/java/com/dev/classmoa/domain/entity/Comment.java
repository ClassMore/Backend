package com.dev.classmoa.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    private String content;
    private LocalDateTime writeDate;
    private Boolean isModified;

    public Comment(String content) {
        this.content = content;
    }

    @Builder(builderMethodName = "creater", buildMethodName = "create")
    public Comment(String content, Opinion opinion) {
        this.content = content;
        this.opinion = opinion;
    }

    @Builder(builderMethodName = "deleter", buildMethodName = "delete")
    public Comment(Long id) {
        this.id = id;
    }

    @Builder(builderMethodName = "editer", buildMethodName = "edit")
    public Comment(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    @Builder(builderMethodName = "finder", buildMethodName = "find")
    public Comment(Opinion opinion) {
        this.opinion = opinion;
    }


    // public void setOpinion(Opinion opinion){
    //     this.opinion = opinion;
    //     opinion.getComments().add(this);
    // }
}
