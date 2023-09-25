package com.example.classmoa.dto.opinion.request;

import com.example.classmoa.domain.entity.Comment;
import com.example.classmoa.domain.entity.Member;
import com.example.classmoa.domain.entity.Opinion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOpinion {

    private String content;

    public Opinion toEntity() {
        return Opinion.creater()
                .content(content)
                .create();
    }
}
