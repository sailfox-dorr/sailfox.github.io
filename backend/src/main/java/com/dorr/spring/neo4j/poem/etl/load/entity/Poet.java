package com.dorr.spring.neo4j.poem.etl.load.entity;


import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Poet {
    @Id
    private String id;
    private String description;
    private String tag;
    private String name;

    public Poet(String id, String description, String tag, String name) {
        this.id = id;
        this.description = description;
        this.tag = tag;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
