package com.dorr.spring.neo4j.poem.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dorr.spring.neo4j.poem.etl.load.entity.Poem;
import com.dorr.spring.neo4j.poem.etl.load.entity.Poet;
import com.dorr.spring.neo4j.poem.etl.load.entity.Strain;
import com.dorr.spring.neo4j.poem.repository.MetricRepository;
import com.dorr.spring.neo4j.poem.repository.PoemRepository;
import org.neo4j.driver.Driver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.neo4j.core.DatabaseSelectionProvider;
import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.UUID;
import java.util.stream.Stream;


@Service
public class DataParseService {

    @Value("${spring.data.poems.path}")
    private static String basePath;


    private final Neo4jClient neo4jClient;

    private final Driver driver;

    private final DatabaseSelectionProvider databaseSelectionProvider;

    private final PoemRepository poemRepository;

    public DataParseService(Neo4jClient neo4jClient, Driver driver, DatabaseSelectionProvider databaseSelectionProvider, PoemRepository poemRepository, MetricRepository metricRepository) {
        this.neo4jClient = neo4jClient;
        this.driver = driver;
        this.databaseSelectionProvider = databaseSelectionProvider;
        this.poemRepository = poemRepository;
        this.metricRepository = metricRepository;
    }

    private final MetricRepository metricRepository;


    private String database() {
        return databaseSelectionProvider.getDatabaseSelection().getValue();
    }

    public void parseAuthor() {
        String path_tang = basePath +"/json/authors.tang.json";
        String path_ci = basePath +"/ci/author.song.json";
        String path_song = basePath +"/json/authors.song.json";
        Stream<Poet> concat = Stream.concat(
                parseJson2Authors(path_tang, "desc", "唐朝"),
                parseJson2Authors(path_ci, "description", "宋朝")
        );
        Stream<Poet> poets = Stream.concat(concat,
                parseJson2Authors(path_song, "description", "宋朝")
        );

        savaPoetsNeo4j(poets.iterator());
    }

    public void parsePoems(String path, String source) {
//        String shijing = basePath +"/shijing/shijing.json";
//        String quantangshi_json = basePath +"/quan_tang_shi/json";

        File file = new File(path);

        Stream<JSONObject> stream = Stream.empty();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File file1 : files) {
                String s = readFile(file1.getAbsolutePath());
                JSONArray arr = JSONArray.parseArray(s);
                stream = Stream.concat(stream, arr.stream().map(json -> (JSONObject) json));
            }

        } else {
            String s = readFile(file.getAbsolutePath());

            JSONArray arr = JSONArray.parseArray(s);

            stream = arr.stream().map(json -> (JSONObject) json);
        }
        parseJson2Poems(stream, source);
    }

    private void savaPoetsNeo4j(Iterator<Poet> poets) {
        while (poets.hasNext()) {
            Poet poet = poets.next();
//            System.out.println("CREATE (n:Part {name: {name},title: {title}})",
//                    parameters( "name", "Arthur001", "title", "King001" );
            try {
                neo4jClient.query("CREATE ( poet:Poet" + "{id:\'" + poet.getId() + "\', name:\'" + poet.getName() + "\',description: \'" + poet.getDescription() + "\',tag: \'" + poet.getTag() + "\'})").run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    private void createRelationShip() {


    }


    public static Stream<Poet> parseJson2Authors(String path, String desc, String tag) {

        String s = readFile(path);
        JSONArray arr = JSONArray.parseArray(s);
        return arr.stream().map(json -> (JSONObject) json)
                .map(json -> new Poet(json.getString("id"), json.getString(desc), tag, json.getString("name")));
    }

    public void parseJson2Poems(Stream<JSONObject> stream, String source) {
        Iterator<Poem> poems;
        String sql = "";
        HashSet<Poem> poem_set = new HashSet<>();
        int a = 0;
        switch (source) {
            case "诗经":
                poems = stream
                        .map(json -> new Poem(UUID.randomUUID().toString(), json.getString("title"), json.getString("content"), source, json.getString("chapter"), json.getString("section"))).iterator();

                while (poems.hasNext()) {
                    Poem poem = poems.next();
                    try {
                        neo4jClient.query("CREATE ( poem:Poem" + "{id:\'" + poem.getId() + "\', title:\'" + poem.getTitle() + "\',paragraphs: \'" + poem.getParagraphs() + "\',source: \'" + poem.getSource() + "\',chapter: \'" + poem.getChapter() + "\',section: \'" + poem.getSection() + "\'})").run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "楚辞":
                poems = stream
                        .map(json -> new Poem(UUID.randomUUID().toString(), json.getString("title"), json.getString("content"), source, json.getString("chapter"), json.getString("author"))).iterator();

                while (poems.hasNext()) {
                    Poem poem = poems.next();
                    try {
                        neo4jClient.query("CREATE ( poem:Poem" + "{id:\'" + poem.getId() + "\', title:\'" + poem.getTitle() + "\',paragraphs: \'" + poem.getParagraphs() + "\',source: \'" + poem.getSource() + "\',chapter: \'" + poem.getChapter() + "\',author: \'" + poem.getSection() + "\'})").run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "全唐诗":
                poems = stream
                        .map(json -> new Poem(UUID.randomUUID().toString(), json.getString("title"), json.getString("author"), json.getString("biography"), json.getString("paragraphs"), json.getString("notes"), json.getString("volume"), json.getString("no"), source)).iterator();

                while (poems.hasNext()) {

                    Poem poem = poems.next();
//                "title": "句",
//        "author": "李世民",
//        "biography": "",
//        "paragraphs": [
//            "雪恥酬百王，除凶報千古。",
//            "昔乘匹馬去，今驅萬乘來。",
//            "近日毛雖暖，聞弦心已驚。"
//        ],
//        "notes": [
//            ""
//        ],
//        "volume": "卷一",
//        "no#": 88,
                    try {
                        neo4jClient.query("CREATE ( poem:QuanTangShi" + "{id:\'" + poem.getId() + "\', title:\'" + poem.getTitle() + "\', biography:\'" + poem.getBiography() + "\',paragraphs: \'" + poem.getParagraphs() + "\',author: \'" + poem.getAuthor() + "\',notes: \'" + poem.getNotes() + "\',volume: \'" + poem.getVolume() + "\',no: \'" + poem.getNotes() + "\',source: \'" + poem.getSource() + "\'})").run();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "诗歌":
            case "元曲":
                poems = stream.map(json -> new Poem(
                        json.getString("id"),
                        json.getString("title"),
                        json.getString("author"),
                        json.getString("paragraphs"),
                        source)).iterator();
                while (poems.hasNext()) {
                    if (poem_set.size() < 1000) {
                        Poem poem = poems.next();
                        a++;
                        poem_set.add(poem);
                    } else {
                        for (Poem poem : poem_set) {
                            String id = poem.getId() == null ? UUID.randomUUID().toString() : poem.getId();
                            sql = sql + "CREATE ( poem" + UUID.randomUUID().toString().substring(1, 6) + UUID.randomUUID().toString().substring(1, 6) + ":Poem" + "{id:\'" + id + "\', title:\'" + poem.getTitle() + "\',paragraphs: \'" + poem.getParagraphs() + "\',author: \'" + poem.getAuthor() + "\',source: \'" + poem.getSource() + "\'})  \n ";
                        }
                        neo4jClient.query(sql).run();
                        poem_set.clear();
                        sql = "";
                    }
                }
                for (Poem poem : poem_set) {
                    String id = poem.getId() == null ? UUID.randomUUID().toString() : poem.getId();
                    sql = sql + "CREATE ( poem" + UUID.randomUUID().toString().substring(1, 6) + UUID.randomUUID().toString().substring(1, 6) + ":Poem" + "{id:\'" + id + "\', title:\'" + poem.getTitle() + "\',paragraphs: \'" + poem.getParagraphs() + "\',author: \'" + poem.getAuthor() + "\',source: \'" + poem.getSource() + "\'})  \n ";
                }
                neo4jClient.query(sql).run();
                poem_set.clear();
                break;
            case "韵律":
                Iterator<Strain> res = stream.map(json -> new Strain(json.getString("id"), json.getString("strains"))).iterator();
                HashSet<Strain> set1 = new HashSet<>();
                while (res.hasNext()) {
                    Strain strain = res.next();
                    if (set1.size() < 100) {
                        set1.add(strain);
                    } else {
                        for (Strain strain1 : set1) {
                            sql = sql + "CREATE ( strain" + UUID.randomUUID().toString().substring(1, 6) + UUID.randomUUID().toString().substring(1, 6) + ":Strain" + "{id:\'" + strain1.getId() + "\', title:\'" + strain1.getStrains() + "\'})  \n ";
                        }
                        neo4jClient.query(sql).run();
                        set1.clear();
                        sql = "";
                    }

                }
                for (Strain strain1 : set1) {
                    sql = sql + "CREATE ( strain" + UUID.randomUUID().toString().substring(1, 6) + UUID.randomUUID().toString().substring(1, 6) + ":Strain" + "{id:\'" + strain1.getId() + "\', title:\'" + strain1.getStrains() + "\'})  \n ";
                }
                neo4jClient.query(sql).run();
                break;
            case "词话":
//                "author": "吴文若",
//                    "paragraphs": [
//                "玉宇生凉秋恰半。",
//                        "月到今霄，分外清光满。",
//                        "兔魄呈祥冰烂。",
//                        "广寒仙子生华旦。",
//                        "聪慧风流天与擅，淑质冰婆，本是飞琼伴。",
//                        "□领彩衣椿祝劝。",
//                        "蟠桃待熟瑶池宴。"
//    ],
//                "rhythmic": "蝶恋花"
                poems = stream.map(json -> new Poem(
                        json.getString("id"),
                        json.getString("rhythmic"),
                        json.getString("author"),
                        json.getString("paragraphs"),
                        source)).iterator();
                while (poems.hasNext()) {
                    if (poem_set.size() < 1000) {
                        Poem poem = poems.next();
                        a++;
                        poem_set.add(poem);
                    } else {
                        for (Poem poem : poem_set) {
                            sql = sql + "CREATE ( poem" + UUID.randomUUID().toString().substring(1, 6) + UUID.randomUUID().toString().substring(1, 6) + ":Poem" + "{id:\'" + poem.getId() + "\', rhythmic:\'" + poem.getTitle() + "\',paragraphs: \'" + poem.getParagraphs() + "\',author: \'" + poem.getAuthor() + "\',source: \'" + poem.getSource() + "\'})  \n ";
                        }
                        System.out.println(a);
                        neo4jClient.query(sql).run();
                        poem_set.clear();
                        sql = "";
                    }
                }
                for (Poem poem : poem_set) {
                    sql = sql + "CREATE ( poem" + UUID.randomUUID().toString().substring(1, 6) + UUID.randomUUID().toString().substring(1, 6) + ":Poem" + "{id:\'" + poem.getId() + "\', rhythmic:\'" + poem.getTitle() + "\',paragraphs: \'" + poem.getParagraphs() + "\',author: \'" + poem.getAuthor() + "\',source: \'" + poem.getSource() + "\'})  \n ";
                }
                neo4jClient.query(sql).run();
                poem_set.clear();
                a = 0;
                break;

        }

    }


    public static String readFile(String Path) {
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new
                    InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr;
    }


    // 前序指标	后续指标	关系		id
    private static String CBF = "企划增速|企划保费|衍生关系（分子）\n" +
            "企划增速|企划保费（同期累计）|衍生关系（分母）\n" +
            "企划保费|超大企划保费|包含关系（客群）\n" +
            "企划保费|大中企划保费|包含关系（客群）\n" +
            "企划保费|小微企划保费|包含关系（客群）\n" +
            "小微企划保费|疑似个人|包含关系（客群）\n" +
            "小微企划保费|小微企业|包含关系（客群）\n" +
            "小微企划保费|个人|包含关系（客群）\n" +
            "企划件均|企划保费|衍生关系（分子）\n" +
            "企划件均|企划保单数|衍生关系（分母）\n" +
            "时间进度达成率|企划保费|衍生关系（分子）\n" +
            "时间进度达成率|目标保费|衍生关系（分母）\n" +
            "目标缺口|企划保费|衍生关系（减数）\n" +
            "目标缺口|目标保费|衍生关系（被减数）\n" +
            "企划保费|团财|包含关系（产品）\n" +
            "企划保费|团意|包含关系（产品）\n" +
            "企划保费|农险|包含关系（产品）\n" +
            "团非车|团财|包含关系（产品）\n" +
            "团非车|团意|包含关系（产品）\n" +
            "团非车|农险|包含关系（产品）\n" +
            "团财|企财险|包含关系（产品）\n" +
            "团财|工程险|包含关系（产品）\n" +
            "团财|责任险|包含关系（产品）\n" +
            "团财|船舶险|包含关系（产品）\n" +
            "团财|货运险|包含关系（产品）\n" +
            "团财|特殊风险|包含关系（产品）\n" +
            "团意|团体意外险|包含关系（产品）\n" +
            "团意|团体健康险|包含关系（产品）" +
            "综合成本率（COR）|综合成本|衍生关系（分子）\n" +
            "综合成本率（COR）|净已赚保费|衍生关系（分母）\n" +
            "综合成本|综合赔付|包含关系（和）\n" +
            "综合成本|综合费用|包含关系（和）\n" +
            "综合赔付|再保前已报告赔付|包含关系（和）\n" +
            "综合赔付|再保对赔付影响|包含关系（和）\n" +
            "综合赔付|间接理赔费用（含工|包含关系（和）\n" +
            "综合赔付|间接理赔费用|包含关系（和）\n" +
            "综合赔付|理赔人员工资|包含关系（和）\n" +
            "综合赔付|IBNR提转差|包含关系（和）\n" +
            "综合赔付|风险准备金|包含关系（和）\n" +
            "综合费用|销售费用|包含关系（和）\n" +
            "综合费用|人力费用|包含关系（和）\n" +
            "综合费用|销售人力|包含关系（和）\n" +
            "综合费用|运营人力|包含关系（和）\n" +
            "综合费用|管理人力|包含关系（和）\n" +
            "综合费用|科技人力|包含关系（和）\n" +
            "综合费用|管理日常|包含关系（和）\n" +
            "综合费用|两金及两税|包含关系（和）\n" +
            "综合费用|资产减值|包含关系（和）\n" +
            "综合费用|再保对费用影响|包含关系（和）\n" +
            "综合费用|已赚影响|包含关系（和）\n" +
            "综合赔付率（CLR）|综合赔付|衍生关系（分子）\n" +
            "综合赔付率（CLR）|净已赚保费|衍生关系（分母）\n" +
            "综合费用率（CER）|综合费用|衍生关系（分子）\n" +
            "综合费用率（CER）|净已赚保费|衍生关系（分母）";


    public void saveMetric2Neo4j() {
        // 前序指标	后续指标	关系
        for (String s : CBF.split("\n")) {
            String[] metrics = s.split("\\|");
//            List<Metric> afters = metricRepository.findType(metrics[1]);
//            List<Metric> befores = metricRepository.findType(metrics[0]);
//            if (befores == null || befores.size() <= 0 || befores.get(0) == null) {
//                // 创建before node
//                neo4jClient.query("create (metric: 指标{编号:'" + UUID.randomUUID().toString() + "',指标名称:'" + metrics[0] + "'})").run();
//            }
            neo4jClient.query("merge (metric: 指标{指标名称:'" + metrics[0] + "'})").run();
            neo4jClient.query("merge (metric:指标{指标名称:'" + metrics[1] + "'})").run();

//            if (afters == null || afters.size() <= 0 || afters.get(0) == null) {
//                // 创建after node
//                neo4jClient.query("create (metric:指标{编号:'" + UUID.randomUUID().toString() + "',指标名称:'" + metrics[1] + "'})").run();
//            }
            String relation = metrics[2].replaceAll("）","").replaceAll("（","_");

            String sql = "match (before:指标{指标名称:'" + metrics[0] + "'}) \n" +
                    "match (after:指标{指标名称:'" + metrics[1] + "'}) \n" +
                    "create (before)-[r:" + relation + "{关系名称:'" + metrics[2] + "'}]->(after) \n";
            neo4jClient.query(sql).run();


        }
    }

}
