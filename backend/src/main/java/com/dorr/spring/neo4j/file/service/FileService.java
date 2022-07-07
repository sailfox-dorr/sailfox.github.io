package com.dorr.spring.neo4j.file.service;

import com.dorr.spring.neo4j.common.utils.FileUtils;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class FileService {
    private final Neo4jClient neo4jClient;

    public FileService(Neo4jClient neo4jClient) {
        this.neo4jClient = neo4jClient;
    }

    public void initBooks(String fileType,String  basePath) {
        // 清空已有数据
        neo4jClient.query("match (b:"+fileType+") delete b").run();
        String sql = "";
        Map<String, String> map = new HashMap<>();
        FileUtils.foreachPath(basePath,map);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] split = key.split("\\.");
            sql = sql + "create (b"+ UUID.randomUUID().toString().replaceAll("-","") +":"+fileType+"{name:'" + split[0].trim() + "',type:'" + split[split.length-1].trim() + "',path:'" + entry.getValue() + "'}) \n ";
        }
        neo4jClient.query(sql).run();

    }

}
