package com.dorr.spring.neo4j.poem.etl.load.entity;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node
public class Poem {
    @Id
    private String Id;
    private String title;
    private String author;
    private String biography;
    private String paragraphs;
    private String notes;
    private String volume;
    private String no;
    private String source;
    private String chapter;
    private String section;
    private String rhythmic;

    public Poem() {
    }

    public Poem(String id, String title, String author, String paragraphs, String source) {
        Id = id;
        this.title = title;
        this.author = author;
        this.paragraphs = paragraphs;
        this.source = source;
    }

    public Poem(String id, String title, String author, String biography, String paragraphs, String notes, String volume, String no, String source) {
        Id = id;
        this.title = title;
        this.author = author;
        this.biography = biography;
        this.paragraphs = paragraphs;
        this.notes = notes;
        this.volume = volume;
        this.no = no;
        this.source = source;
    }

    public Poem(String id, String title, String paragraphs, String source, String chapter, String section) {
        Id = id;
        this.title = title;
        this.paragraphs = paragraphs;
        this.source = source;
        this.chapter = chapter;
        this.section = section;
    }

    public String getId() {
        return Id;
    }


    public void setId(String id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(String paragraphs) {
        this.paragraphs = paragraphs;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }
}
