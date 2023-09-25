package com.dev.classmoa.dto.opinion.request;

import com.dev.classmoa.domain.entity.Opinion;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOpinion {

    private Long id;

    public Opinion toEntity() {
        return Opinion.finder()
                .id(id)
                .find();
    }
}
