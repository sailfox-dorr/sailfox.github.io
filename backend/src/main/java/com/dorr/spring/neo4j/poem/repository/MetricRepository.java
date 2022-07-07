package com.dorr.spring.neo4j.poem.repository;

import com.dorr.spring.neo4j.poem.etl.load.entity.Metric;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MetricRepository extends Repository<Metric, String> {
    @Query("match (m:指标) where m.name=$name return m ")
    abstract List<Metric> findType(@Param("name") String name);

}
