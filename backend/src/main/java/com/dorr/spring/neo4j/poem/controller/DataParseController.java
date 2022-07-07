package com.dorr.spring.neo4j.poem.controller;

import com.dorr.spring.neo4j.common.aop.LogType;
import com.dorr.spring.neo4j.common.entity.WebJsonInfo;
import com.dorr.spring.neo4j.poem.service.DataParseService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.dorr.spring.neo4j.common.entity.WebJsonInfo.success;

@RestController
public class DataParseController {

    private static HashMap<String, String> paths = new HashMap<>();
    @Value("${spring.data.poems.path}")
    private static String basePath;

    static {
        paths.put("诗经", basePath + "/shijing/shijing.json");
        paths.put("全唐诗", basePath + "/quan_tang_shi/json");
        paths.put("诗歌", basePath + "/json");
        paths.put("词话", basePath + "/ci");
        paths.put("韵律", basePath + "/strains/json");
        paths.put("楚辞", basePath + "/chuci/chuci.json");
        paths.put("论语", basePath + "/lunyu/lunyu.json");
        paths.put("元曲", basePath + "/yuanqu/yuanqu.json");
        paths.put("曹操", basePath + "/caocaoshiji/caocao.json");
    }

    // 数据中词是简体 ， 诗繁体

    private final DataParseService service;

    public DataParseController(DataParseService service) {
        this.service = service;
    }

    @GetMapping("/parse/author")
    public void parseAuthor() {
        service.parseAuthor();
    }

    @GetMapping("/parse/poems")
    @LogType("导入诗歌数据")
    public WebJsonInfo parsePoems(@RequestParam("source") String source) {
        service.parsePoems(paths.get(source), source);
        return success("200");
    }

    @GetMapping("/parse/strains")
    @LogType("导入词牌信息数据")
    public WebJsonInfo parseStrains() {

        service.parsePoems(paths.get("韵律"), "韵律");
        return success("200");
    }

    @GetMapping("/parse/metrics")
    public WebJsonInfo parseMetrics() {

        service.saveMetric2Neo4j();
        return success("200");
    }


}
