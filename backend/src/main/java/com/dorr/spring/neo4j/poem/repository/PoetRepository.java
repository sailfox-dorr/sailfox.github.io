package com.dorr.spring.neo4j.poem.repository;

import com.dorr.spring.neo4j.poem.etl.load.entity.Metric;
import com.dorr.spring.neo4j.poem.etl.load.entity.Poet;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PoetRepository extends Repository<Poet, String> {
    @Query("match (m:Poet) where m.name contains $keyWord or m.tag contains $keyWord or m.description contains $keyWord return m limit $limit ")
    abstract List<Poet> queryPoetByKeyWord(@Param("keyWord") String keyWord,@Param("limit") Integer  limit);
}
