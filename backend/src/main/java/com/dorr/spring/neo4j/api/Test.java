package com.dorr.spring.neo4j.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {


        System.out.println(URLEncoder.encode("苏", "utf-8"));

        String s =
                "综合成本率（COR）\n" +
                        "综合成本\n" +
                        "衍生关系（分子）\n" +
                        "综合成本率（COR）\n" +
                        "净已赚保费\n" +
                        "衍生关系（分母）\n" +
                        "综合成本\n" +
                        "综合赔付\n" +
                        "包含关系（和）\n" +
                        "综合成本\n" +
                        "综合费用\n" +
                        "包含关系（和）\n" +
                        "综合赔付\n" +
                        "再保前已报告赔付\n" +
                        "包含关系（和）\n" +
                        "综合赔付\n" +
                        "再保对赔付影响\n" +
                        "包含关系（和）\n" +
                        "综合赔付\n" +
                        "间接理赔费用（含工\n" +
                        "包含关系（和）\n" +
                        "综合赔付\n" +
                        "间接理赔费用\n" +
                        "包含关系（和）\n" +
                        "综合赔付\n" +
                        "理赔人员工资\n" +
                        "包含关系（和）\n" +
                        "综合赔付\n" +
                        "IBNR提转差\n" +
                        "包含关系（和）\n" +
                        "综合赔付\n" +
                        "风险准备金\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "销售费用\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "人力费用\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "销售人力\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "运营人力\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "管理人力\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "科技人力\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "管理日常\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "两金及两税\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "资产减值\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "再保对费用影响\n" +
                        "包含关系（和）\n" +
                        "综合费用\n" +
                        "已赚影响\n" +
                        "包含关系（和）\n" +
                        "综合赔付率（CLR）\n" +
                        "综合赔付\n" +
                        "衍生关系（分子）\n" +
                        "综合赔付率（CLR）\n" +
                        "净已赚保费\n" +
                        "衍生关系（分母）\n" +
                        "综合费用率（CER）\n" +
                        "综合费用\n" +
                        "衍生关系（分子）\n" +
                        "综合费用率（CER）\n" +
                        "净已赚保费\n" +
                        "衍生关系（分母）";

        String[] splits = s.split("\n");
        for (int i = 0; i < splits.length - 2; i += 3) {

            System.out.println(splits[i] + "|" + splits[i + 1] + "|" + splits[i + 2]);
        }
    }
}
