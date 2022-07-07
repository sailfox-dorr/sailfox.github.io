package com.dorr.spring.neo4j.poem.repository;


import com.dorr.spring.neo4j.poem.etl.load.entity.Metric;
import com.dorr.spring.neo4j.poem.etl.load.entity.Poem;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface  PoemRepository extends Repository<Poem, String> {

        @Query("MATCH (poem:Poem) WHERE poem.author CONTAINS $author RETURN poem")
        abstract List<Poem> findSearchResults(@Param("author") String author);

        @Query("MATCH (poem:Poem) WHERE poem.author CONTAINS $author and poem.title=$title RETURN poem")
        abstract List<Poem> findSearchResultWithTitleAndName(@Param("author") String author,@Param("title") String title);

        @Query("MATCH (poem:Poem) WHERE poem.author CONTAINS $keyWord or poem.title CONTAINs $keyWord or poem.paragraphs contains $keyWord RETURN poem")
        abstract List<Poem> findSearchResultWithKeyWord(@Param("keyWord") String keyWord);


}


