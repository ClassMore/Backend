package com.dev.classmoa.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    private Boolean isModified = false;
    private Boolean isDeleted = false;

    public Comment(String content) {
        this.content = content;
    }

    @Builder
    public Comment(String content, Member member, Opinion opinion) {
        this.content = content;
        this.member = member;
        this.opinion = opinion;
    }

    public void editComment(String content) {
        this.content = content;
        this.isModified = true;
    }

    public void deleteComment(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
