package com.dorr.spring.neo4j.poem.etl.load.entity;


public class PoemShort {
    private String name;
    private String title;
    private String paragraphs;
    private String decades;

    public String getName() {
        return name;
    }

    public PoemShort() {
    }

    public PoemShort(String name, String title, String paragraphs, String decades) {
        this.name = name;
        this.title = title;
        this.paragraphs = paragraphs;
        this.decades = decades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getDecades() {
        return decades;
    }

    public void setDecades(String decades) {
        this.decades = decades;
    }
}
