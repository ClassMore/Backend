package com.dev.classmoa.dto.tag.response;

import com.dev.classmoa.domain.entity.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindTagResponse {

    private Long id;

    @Size(max = 255)
    @NotNull
    private String name;

    public FindTagResponse(Tag tag) {
        this.id = getId();
        this.name = getName();
    }
}
