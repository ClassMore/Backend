package com.dev.classmoa.dto.opinion.request;

import com.dev.classmoa.domain.entity.Opinion;
import lombok.*;

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
