package com.example.classmoa.dto.tag.request;

import com.example.classmoa.domain.entity.Tag;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTag {

    private Long id;

    public Tag toEntity() {
        return Tag.searcher()
                .id(id)
                .search();
    }
}
