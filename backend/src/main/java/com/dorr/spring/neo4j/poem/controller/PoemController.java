package com.dorr.spring.neo4j.poem.controller;


import com.dorr.spring.neo4j.common.aop.LogType;
import com.dorr.spring.neo4j.common.entity.WebPageInfo;
import com.dorr.spring.neo4j.poem.service.QueryPoemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PoemController {

    QueryPoemService service;

    public PoemController(QueryPoemService service) {
        this.service = service;
    }

    @GetMapping("/poem/queryPoemByPoet")
    @LogType("根据诗人查询诗")
    public WebPageInfo queryPoemByPoetName(@RequestParam("poet") String poet, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
//        return service.queryPoemByPoetName(poet);
        return WebPageInfo.success(service.queryPoemByPoetName(poet), pageSize, pageNum);
    }

    @GetMapping("/poem/queryPoemByKeyword")
    @LogType("根据诗人 诗名查询诗")
    public WebPageInfo queryPoemByKeyWord(@RequestParam("keyWord") String keyWord, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
//        return service.queryPoemByPoetName(poet);
        return WebPageInfo.success(service.queryPoemByKeyWord(keyWord), pageSize, pageNum);
    }

    @GetMapping("/poem/queryPoemByPoetAndTitle")
    @LogType("根据关键字查询诗")
    public WebPageInfo queryPoemByPoemAndTitle(@RequestParam("poet") String poet, @RequestParam("title") String title, @RequestParam("pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize) {
//        return service.queryPoemByPoetName(poet);
        return WebPageInfo.success(service.queryPoemByPoetNameAndTitle(poet, title), pageSize, pageNum);
    }
}
