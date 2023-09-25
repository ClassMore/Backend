package com.dev.classmoa.dto.comment.request;

import com.dev.classmoa.domain.entity.Comment;
import com.dev.classmoa.domain.entity.Opinion;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FindComment {

    private Long opinionId;

    public Comment toEntity() {
        return Comment.finder()
                .opinion(Opinion.builder().id(opinionId).build())
                .find();
    }
}
