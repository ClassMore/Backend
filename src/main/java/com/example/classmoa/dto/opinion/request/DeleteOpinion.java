package com.example.classmoa.dto.opinion.request;

import com.example.classmoa.domain.entity.Opinion;
import lombok.*;

import java.time.LocalDateTime;
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
