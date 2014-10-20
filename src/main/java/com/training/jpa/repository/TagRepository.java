package com.training.jpa.repository;
/**
 * @COPYRIGHT (C) 2014 Schenker AG
 *
 * All rights reserved
 */

import com.training.jpa.model.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * TODO The class TagRepository is supposed to be documented...
 *
 * @author Tomasz Bialecki
 */
@Repository
public class TagRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Tag tag) {
        entityManager.persist(tag);
    }

    public Tag getByName(String name) {

        List<Tag> tags =
                entityManager.createQuery("SELECT t FROM Tag t WHERE t.name = :name", Tag.class)
                        .setParameter("name", name).getResultList();
        return tags.size() > 0 ? tags.get(0) : null;
    }
}
