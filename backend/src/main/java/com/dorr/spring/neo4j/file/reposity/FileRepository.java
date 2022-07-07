package com.dorr.spring.neo4j.file.reposity;

import com.dorr.spring.neo4j.file.entity.File;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FileRepository extends Repository<File, Long> {
    @Query("match (m:$type) where m.name=$name return m ")
    abstract List<File> findType(@Param("name") String name,@Param("type") String type);

}
