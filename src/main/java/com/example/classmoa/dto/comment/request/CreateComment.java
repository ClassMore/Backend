package com.example.classmoa.dto.comment.request;

import com.example.classmoa.domain.entity.Comment;
import com.example.classmoa.domain.entity.Opinion;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateComment {

    private String content;
    private Long opinionId;

    public Comment toEntity() {
        return Comment.creater()
                .content(content)
                .opinion(Opinion.finder().id(opinionId).find())
                .create();
    }
}
