package com.example.classmoa.dto.opinion.response;

import com.example.classmoa.domain.entity.Member;
import com.example.classmoa.domain.entity.Opinion;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditOpinion {

    private Boolean isModified;

    public EditOpinion(Opinion opinion)  {
        this.isModified = opinion.getIsModified();
    }
}
