package com.classmoa.classmoa.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Lecture {

    @Id
    @Column(name = "lecture_id")
    private String id;

    private String title;
    private String instructor;
    private String companyName;
    private int viewCount;
    private String ordinaryPrice;
    private String salePrice;
    private String salePercent;
    private LocalDate date;
    private String description;
    private String siteLink;
    private String imageLink;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<LectureTag> lectureTags = new ArrayList<>();

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.ALL)
    private List<Opinion> opinions = new ArrayList<>();

    //연관관계 메서드 작성

}
