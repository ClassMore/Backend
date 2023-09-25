package com.example.classmoa.domain.entity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Alarm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")
    private Lecture lecture;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int customPrice;

    public Alarm(int customPrice) {
        this.customPrice = customPrice;
    }

    public void setLecture(Lecture lecture){
        this.lecture = lecture;
    }

    public void setMember(Member member){
        this.member = member;
    }

    @Builder(builderMethodName = "canceler", buildMethodName = "cancel")
    public Alarm(Long id) { this.id = id; }
}
