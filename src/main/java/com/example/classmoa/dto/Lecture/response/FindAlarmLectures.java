package com.example.classmoa.dto.Lecture.response;

import com.example.classmoa.domain.entity.Lecture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindAlarmLectures {

    private Long id;
    private String lectureId;
    private String imageLink;
    private String title;
    private String instructor;

    public FindAlarmLectures(Lecture lecture) {
        this.id = lecture.getId();
        this.lectureId = lecture.getLectureId();
        this.imageLink = lecture.getImageLink();
        this.title = lecture.getTitle();
        this.instructor = lecture.getInstructor();
    }
}
