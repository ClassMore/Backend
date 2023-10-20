package com.dev.classmoa.dto.opinion.response;

import com.dev.classmoa.domain.entity.Opinion;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditOpinionResponse {

    @Builder.Default
    private Boolean isModified = false;
}
