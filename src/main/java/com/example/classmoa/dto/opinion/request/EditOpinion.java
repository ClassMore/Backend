package com.example.classmoa.dto.opinion.request;

import com.example.classmoa.domain.entity.Opinion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditOpinion {

    private Long id;
    private String content;

    public Opinion toEntity() {
        return Opinion.editer()
                .id(id)
                .content(content)
                .edit();
    }
}
