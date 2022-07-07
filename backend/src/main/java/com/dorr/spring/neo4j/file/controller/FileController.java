package com.dorr.spring.neo4j.file.controller;

import com.dorr.spring.neo4j.file.service.FileService;
import com.dorr.spring.neo4j.common.entity.WebJsonInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

import static com.dorr.spring.neo4j.common.entity.WebJsonInfo.success;

@RestController
public class FileController {
    private static HashMap<String, String> map = new HashMap<>();
    static{
        map.put("Book","/Users/dorr/Documents/books");
        map.put("Video","/Users/dorr/Documents/videos");
    }


    private FileService bookService;

    public FileController(FileService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/parse/books")
    public WebJsonInfo parseStrains(@RequestParam("fileType") String fileType) {
        bookService.initBooks(fileType, map.get(fileType));
        return success("200");
    }
}
