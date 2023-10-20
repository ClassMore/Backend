package com.dev.classmoa.dto.Lecture.response;

import com.dev.classmoa.domain.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindLectureChartResponse {

    private String lectureId;
    private int salePrice;
    private LocalDate date;

    public FindLectureChartResponse(Lecture lecture) {
        this.lectureId = lecture.getLectureId();
        this.salePrice = lecture.getSalePrice();
        this.date = lecture.getDate();
    }
}
