package com.dev.classmoa.dto.comment.request;

import com.dev.classmoa.domain.entity.Comment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EditCommentRequest {

    @Positive
    private Long id;

    @Size(max = 100)
    @NotNull
    private String content;

}
