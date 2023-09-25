package com.example.classmoa.dto.comment.request;

import com.example.classmoa.domain.entity.Comment;
import com.example.classmoa.domain.entity.Opinion;
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
