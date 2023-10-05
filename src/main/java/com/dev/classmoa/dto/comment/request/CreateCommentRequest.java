package com.dev.classmoa.dto.comment.request;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Opinion;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {

    @Size(max = 255)
    private String content;
    private Long opinionId;

    public Comment toEntity() {
        return Comment.creater()
                .content(content)
                .opinion(Opinion.finder().id(opinionId).find())
                .create();
    }
}
