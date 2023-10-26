package com.dev.classmoa.dto.opinion.request;

import com.dev.classmoa.domain.entity.Opinion;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOpinionRequest {

    @Positive(message = "유효 하지 않은 값 입니다.")
    private Long id;

    @NotNull(message = "유효 하지 않은 값 입니다.")
    private String lectureId;

}
