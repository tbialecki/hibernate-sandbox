package com.training.jpa.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product extends AbstractEntity {

    private String name;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();
    private int price;

    @SuppressWarnings("unused") // required by Hibernate
    private Product() {
    }

    public Product(String name, Tag tag, int price) {
        this.name = name;
        this.price = price;
        this.getTags().add(tag);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
