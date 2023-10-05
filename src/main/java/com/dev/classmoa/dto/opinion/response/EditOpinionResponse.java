package com.dev.classmoa.dto.opinion.response;

import com.dev.classmoa.domain.entity.Opinion;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditOpinionResponse {

    private Boolean isModified = false;
}
