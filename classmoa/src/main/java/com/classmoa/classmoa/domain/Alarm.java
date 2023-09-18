package com.classmoa.classmoa.domain;

import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Alarm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private Long customPrice;

    public Alarm(){}
    public Alarm(Long customPrice) {
        this.customPrice = customPrice;
    }

    public void setLecture(Lecture lecture){
        this.lecture = lecture;
    }

    public void setMember(Member member){
        this.member = member;
    }
}
