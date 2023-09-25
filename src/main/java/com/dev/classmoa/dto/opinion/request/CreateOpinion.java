package com.dev.classmoa.dto.opinion.request;

import com.dev.classmoa.domain.entity.Opinion;
import lombok.*;

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
