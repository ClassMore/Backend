package com.dev.classmoa.dto.Lecture.response;

import com.dev.classmoa.domain.entity.Lecture;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindLectureDetailResponse{

    private Long id;
    private String lectureId;
    private String companyName;
    private String imageLink;
    private String title;
    private String instructor;
    private String siteLink;
    private String salePercent;
    private int ordinaryPrice;
    private int salePrice;

    public FindLectureDetailResponse(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureId = lecture.getLectureId();
        this.companyName = lecture.getCompanyName();
        this.imageLink = lecture.getImageLink();
        this.title = lecture.getTitle();
        this.instructor = lecture.getInstructor();
        this.siteLink = lecture.getSiteLink();
        this.ordinaryPrice = lecture.getOrdinaryPrice();
        this.salePrice = lecture.getSalePrice();
        this.salePercent = lecture.getSalePercent();
    }
}
