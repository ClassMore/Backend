package com.dev.classmoa.dto.interest.response;

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
public class FindInterestLecturesResponse {

    private Long id;
    private String lectureId;
    private String imageLink;
    private String title;
    private String instructor;

    public FindInterestLecturesResponse(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureId = lecture.getLectureId();
        this.imageLink = lecture.getImageLink();
        this.title = lecture.getTitle();
        this.instructor = lecture.getInstructor();
    }
}
