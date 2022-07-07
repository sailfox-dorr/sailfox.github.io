package com.dorr.spring.neo4j.poem.etl.load.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Metric {
    private String name;
    @Id
    private String id;

    public Metric() {
    }

    public String getName() {
        return name;
    }

    public Metric(String name) {
        this.name = name;
    }

    public Metric(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
