package com.dev.classmoa.dto.Lecture.response;

import com.dev.classmoa.domain.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindLectureResponse {

    private Long id;
    private String lectureId;
    private String imageLink;
    private String title;
    private String instructor;
    private int ordinaryPrice;
    private int salePrice;

    public FindLectureResponse(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureId = lecture.getLectureId();
        this.imageLink = lecture.getImageLink();
        this.title = lecture.getTitle();
        this.instructor = lecture.getInstructor();
        this.ordinaryPrice = lecture.getOrdinaryPrice();
        this.salePrice = lecture.getSalePrice();
    }
}
