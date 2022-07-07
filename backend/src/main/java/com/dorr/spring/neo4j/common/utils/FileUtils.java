package com.dorr.spring.neo4j.common.utils;

import java.io.File;
import java.util.Map;

public class FileUtils {

    public static void foreachPath(String path, Map map){
        File file = new File(path);
        if (file.isDirectory()){
            for (File listFile : file.listFiles()) {
               foreachPath(listFile.getAbsolutePath(),map);
            }
        }else if (!file.getAbsolutePath().endsWith(".DS_Store")){
           map.put(file.getName(),file.getAbsolutePath());
        }
    }

}
