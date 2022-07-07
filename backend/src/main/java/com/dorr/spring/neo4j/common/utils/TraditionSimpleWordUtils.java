package com.dorr.spring.neo4j.common.utils;

import com.dorr.spring.neo4j.poem.etl.load.entity.Poet;
import com.github.houbb.opencc4j.util.ZhConverterUtil;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TraditionSimpleWordUtils {

    private final static  Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
    public static String tradition2Simple(String tradition) {
        if (tradition == null ) return null;
        Matcher m = p.matcher(tradition);
        if (m.find()) {//包含中文
            try {
                return ZhConverterUtil.convertToSimple(tradition);
            } catch (Exception e) {
            }
        }
        return null;
    }

    public static void main(String[] args) throws IllegalAccessException {

        Poet poet = new Poet("123", "苏轼", "342sd", "苏轼");


        System.out.println(poet);
        System.out.println(poet.getDescription());



    }

    public static <T> T tradition2Simple(T t)  {

        Field[] fields = t.getClass().getDeclaredFields();
        //遍历
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);//使私有对象可以访问
//            String name = fields[i].getName();//获取属性名
            String typeName = fields[i].getType().getName();//获取类型 例如:java.lang.Integer

            if ("java.lang.String".equals(typeName)) {
                try {
                    fields[i].set(t,tradition2Simple((String)fields[i].get(t)));//给属性类型为Integer的赋值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


        }
        return t;


    }

    public static <T> T simple2Tradition(T t)  {

        Field[] fields = t.getClass().getDeclaredFields();
        //遍历
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);//使私有对象可以访问
//            String name = fields[i].getName();//获取属性名
            String typeName = fields[i].getType().getName();//获取类型 例如:java.lang.Integer

            if ("java.lang.String".equals(typeName)) {
                try {
                    fields[i].set(t,simple2Tradition((String)fields[i].get(t)));//给属性类型为Integer的赋值
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }


        }
        return t;


    }


    public static String simple2Tradition(String simple) {
        if (simple == null) return null;
        Matcher m = p.matcher(simple);
        if (m.find()) {//包含中文
            try {
                return ZhConverterUtil.convertToTraditional(simple);
            } catch (Exception e) {
            }
        }
        return null;
    }
}
