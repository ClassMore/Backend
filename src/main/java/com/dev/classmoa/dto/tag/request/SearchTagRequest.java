package com.dev.classmoa.dto.tag.request;

import com.dev.classmoa.domain.entity.Tag;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTagRequest {

    private Long id;

    public Tag toEntity() {
        return Tag.searcher()
                .id(id)
                .search();
    }
}
