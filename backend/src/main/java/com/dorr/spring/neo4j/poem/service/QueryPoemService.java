package com.dorr.spring.neo4j.poem.service;


import com.dorr.spring.neo4j.common.utils.TraditionSimpleWordUtils;
import com.dorr.spring.neo4j.poem.etl.load.entity.Poem;
import com.dorr.spring.neo4j.poem.etl.load.entity.PoemShort;
import com.dorr.spring.neo4j.poem.repository.PoemRepository;

import org.neo4j.driver.Driver;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryPoemService {
    private final PoemRepository poemRepository;

    private final Neo4jClient neo4jClient;

    private final Driver driver;

    private final DatabaseSelectionProvider databaseSelectionProvider;

    private String database() {
        return databaseSelectionProvider.getDatabaseSelection().getValue();
    }


    public QueryPoemService(PoemRepository poemRepository, Neo4jClient neo4jClient, Driver driver, DatabaseSelectionProvider databaseSelectionProvider) {
        this.poemRepository = poemRepository;
        this.neo4jClient = neo4jClient;
        this.driver = driver;
        this.databaseSelectionProvider = databaseSelectionProvider;
    }


    public List<PoemShort> queryPoemByPoetName(String poet) {

        return poemRepository.findSearchResults(TraditionSimpleWordUtils.simple2Tradition(poet))
                .stream().map(this::poemShort).collect(Collectors.toList());
    }


    public List<PoemShort> queryPoemByPoetNameAndTitle(String poet, String title) {

        return poemRepository.findSearchResultWithTitleAndName(TraditionSimpleWordUtils.simple2Tradition(poet), TraditionSimpleWordUtils.simple2Tradition(title))
                .stream().map(this::poemShort).collect(Collectors.toList());
    }

    public List<PoemShort> queryPoemByKeyWord(String keyWord) {

        return poemRepository.findSearchResultWithKeyWord(TraditionSimpleWordUtils.simple2Tradition(keyWord))
                .stream().map(this::poemShort).collect(Collectors.toList());
    }

    private PoemShort poemShort(Poem poem) {
        Poem t = TraditionSimpleWordUtils.tradition2Simple(poem);
        return new PoemShort(
                t.getAuthor(),
                t.getTitle(),
                t.getParagraphs(),
                t.getSource()
        );
    }


}
