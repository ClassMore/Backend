package com.dev.classmoa.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class ViewCount {

    @Id
    @Column(name = "lecture_id")
    private String lectureId;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "lecture_id", referencedColumnName = "lecture_id")
    @MapsId
    private Lecture lecture;

    private int viewCount;
}
