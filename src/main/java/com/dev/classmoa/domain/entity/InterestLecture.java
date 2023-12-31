package com.dev.classmoa.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class InterestLecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private boolean canceled = false;

    @Builder
    public InterestLecture(Member member, Lecture lecture) {
        this.member = member;
        this.lecture = lecture;
    }

    public void setLecture(Lecture lecture) {
    	this.lecture = lecture;
    }

    public void setMember(Member member) {
    	this.member = member;
    }

    public void updateIsCanceled(boolean isCanceled){
        this.canceled = isCanceled;
    }

}
