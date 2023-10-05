package com.dev.classmoa.dto.alarm.response;

import com.dev.classmoa.domain.entity.Lecture;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAlarmLecturesResponse {

    private Long id;

    @Size(max = 255)
    @NotNull
    private String lectureId;

    @NotNull
    private String imageLink;

    @NotNull
    private String title;

    @NotNull
    private String instructor;

    public FindAlarmLecturesResponse(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureId = lecture.getLectureId();
        this.imageLink = lecture.getImageLink();
        this.title = lecture.getTitle();
        this.instructor = lecture.getInstructor();
    }
}
