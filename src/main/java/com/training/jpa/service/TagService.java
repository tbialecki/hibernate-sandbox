package com.training.jpa.service;

import com.training.jpa.model.Tag;
import com.training.jpa.repository.TagRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class TagService {

    @Inject
    private TagRepository tagRepository;

    public Tag getTagByName(String name) {
        return tagRepository.getByName(name);
    }

    public Tag saveTag(Tag tag) {
        Tag t = getTagByName(tag.getName());
        if (t == null) {
            tagRepository.save(tag);
            return tag;
        }
        return t;
    }
}
