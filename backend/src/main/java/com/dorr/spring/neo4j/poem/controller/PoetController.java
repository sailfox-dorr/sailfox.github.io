package com.dorr.spring.neo4j.poem.controller;

import com.dorr.spring.neo4j.common.aop.LogType;
import com.dorr.spring.neo4j.common.entity.WebPageInfo;
import com.dorr.spring.neo4j.poem.service.QueryPoetService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoetController {


    private final QueryPoetService service;

    public PoetController(QueryPoetService queryPoetService) {
        this.service = queryPoetService;
    }

    @GetMapping("/poet/queryPoetByKeyWords")
    @LogType("根据关键字查询诗人")
    public WebPageInfo queryPoemByPoetName(@RequestParam("keyword") String keyword, @RequestParam("limit") Integer limit) {
//        return service.queryPoemByPoetName(poet);
        return WebPageInfo.success(service.queryPoetsByKeyWord(keyword,limit),10,3);
    }
}
