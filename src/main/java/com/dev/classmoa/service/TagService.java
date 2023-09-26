package com.dev.classmoa.service;

import com.dev.classmoa.domain.entity.Tag;
import com.dev.classmoa.domain.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final TagRepository tagRepository;

    public List<Tag> getTagList(Pageable pageable) {
        return tagRepository.findAll(pageable).getContent();
    }

}
