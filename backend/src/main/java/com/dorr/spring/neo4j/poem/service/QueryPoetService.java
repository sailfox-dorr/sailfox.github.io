package com.dorr.spring.neo4j.poem.service;

import com.dorr.spring.neo4j.common.utils.TraditionSimpleWordUtils;
import com.dorr.spring.neo4j.poem.etl.load.entity.Poet;
import com.dorr.spring.neo4j.poem.repository.PoetRepository;
import org.neo4j.driver.Driver;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QueryPoetService {

    private final PoetRepository poetRepository;
    private final Neo4jClient neo4jClient;

    private final Driver driver;

    private final DatabaseSelectionProvider databaseSelectionProvider;

    public QueryPoetService(PoetRepository poetRepository, Neo4jClient neo4jClient, Driver driver, DatabaseSelectionProvider databaseSelectionProvider) {
        this.poetRepository = poetRepository;
        this.neo4jClient = neo4jClient;
        this.driver = driver;
        this.databaseSelectionProvider = databaseSelectionProvider;
    }

    private String database() {
        return databaseSelectionProvider.getDatabaseSelection().getValue();
    }


    public List<Poet> queryPoetsByKeyWord(String keyword, int limit) {
        return poetRepository.queryPoetByKeyWord(TraditionSimpleWordUtils.simple2Tradition(keyword), limit)
                .stream().map(poet -> new Poet(poet.getId(),
                        TraditionSimpleWordUtils.tradition2Simple(poet.getDescription()),
                        TraditionSimpleWordUtils.tradition2Simple(poet.getTag()),
                        TraditionSimpleWordUtils.tradition2Simple(poet.getName())
                )).collect(Collectors.toList());
    }


}
