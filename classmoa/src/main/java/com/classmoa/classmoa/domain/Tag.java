package com.classmoa.classmoa.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Tag {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "tag", cascade = CascadeType.ALL)
    private List<LectureTag> lectureTags = new ArrayList<>();

    public Tag(String name) {
        this.name = name;
    }

    @Builder(builderMethodName = "searcher", buildMethodName = "search")
    public Tag(Long id) {
        this.id = id;
    }

    public void setLectureTags(LectureTag lectureTag){
        this.getLectureTags().add(lectureTag);
        lectureTag.setTag(this);
    }
}
