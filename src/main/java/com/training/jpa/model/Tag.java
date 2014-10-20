package com.training.jpa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tag extends AbstractEntity {

    @Column(unique = true)
    String name;

    public List<Product> getProducts() {
        return products;
    }

    @ManyToMany(mappedBy = "tags")
    private List<Product> products = new ArrayList<>();

    @SuppressWarnings("unused") // required by Hibernate
    private Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
