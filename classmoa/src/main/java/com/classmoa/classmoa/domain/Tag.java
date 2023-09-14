package com.classmoa.classmoa.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<LectureTag> lectureTags = new ArrayList<>();

    //연관관계 메서드 작성
}
