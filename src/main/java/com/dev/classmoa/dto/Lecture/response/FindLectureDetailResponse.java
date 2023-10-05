package com.dev.classmoa.dto.Lecture.response;

import com.dev.classmoa.domain.entity.Lecture;
import jakarta.validation.constraints.NotNull;
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

    @Size(max = 255)
    @NotNull
    private String lectureId;

    @Size(max = 255)
    @NotNull
    private String companyName;

    private String imageLink;

    @NotNull
    private String title;

    @NotNull
    private String instructor;

    private String siteLink;

    @Size(max = 255)
    private String salePercent;

    @NotNull
    private int ordinaryPrice;

    @NotNull
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
