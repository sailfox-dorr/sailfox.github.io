package com.dorr.spring.neo4j.poem.etl.load.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Strain {
    @Id
    String id;


    public Strain(String id, String strains) {
        this.id = id;
        this.strains = strains;
    }

    String strains;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStrains() {
        return strains;
    }

    public void setStrains(String strains) {
        this.strains = strains;
    }
}
