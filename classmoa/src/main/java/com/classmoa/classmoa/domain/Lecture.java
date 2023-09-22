package com.classmoa.classmoa.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lectureId;
    private String title;
    private String instructor;
    private String companyName;
    private int ordinaryPrice;
    private int salePrice;
    private String salePercent;

    @CreatedDate
    private LocalDate date;
    private String siteLink;
    private String imageLink;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<LectureTag> lectureTags = new ArrayList<>();

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Opinion> opinions = new ArrayList<>();

    public Lecture(String title, String instructor, String companyName, int ordinaryPrice, int salePrice,
                   String salePercent, String siteLink, String imageLink) {
        this.title = title;
        this.instructor = instructor;
        this.companyName = companyName;
        this.ordinaryPrice = ordinaryPrice;
        this.salePrice = salePrice;
        this.salePercent = salePercent;
        this.siteLink = siteLink;
        this.imageLink = imageLink;
    }
    //연관관계 메서드 작성

    // public void setLectureTags(LectureTag lectureTag){
    //     this.getLectureTags().add(lectureTag);
    //     lectureTag.setLectures(this);
    // }
}
