package com.xiang.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.xiang.dao.EsDao;
import com.xiang.dao.StudentsDao;
import com.xiang.entity.Students;
import com.xiang.service.StudentsService;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * (Students)表服务实现类
 *
 * @author Array
 * @since 2020-08-29 10:59:59
 */
@Service
public class StudentsServiceImpl implements StudentsService {
    @Resource
    private StudentsDao studentsDao;
    @Resource
    private EsDao esDao;

    @Resource
    private RestHighLevelClient client;

    /**
     * 保存所有的学生信息到 Es 中
     *
     * @author Xiang想
     * @date 2020/8/29 11:20
     */
    @Override
    public void saveAllStudentInfo() {
        List<Students> students = studentsDao.selectAllStudent();
        Long i = 1L;
        for (Students student : students) {
            student.setId(i);
            i++;
        }
        esDao.saveAll(students);
    }

    /**
     * 高级查询
     *
     * @param text 查询文本
     * @return java.util.List<com.xiang.entity.Students>
     * @author Xiang想
     * @date 2020/8/29 14:06
     */
    @Override
    public List<Students> improveSreach(String text) {
        // 构建 es 搜索请求
        SearchRequest searchRequest = new SearchRequest("rsdoct");
        // 指定类型
        searchRequest.types("rsbean");
        // 构造搜索源对象
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 搜索方式，多关键字 搜索
        MultiMatchQueryBuilder matchQueryBuilder = QueryBuilders.multiMatchQuery(text,"name")
                .field("name",10)
                .field("desc",5);
        // 添加条件到布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(matchQueryBuilder);
        searchSourceBuilder.query(boolQueryBuilder);

        searchSourceBuilder.size(100);
        searchSourceBuilder.from(0);
        // 配置高亮查询
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        // 高亮前缀
        highlightBuilder.preTags("<span style='color:red'>");
        // 高亮后缀
        highlightBuilder.postTags("</span>");

        // 高亮字段
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("desc"));
        searchSourceBuilder.highlighter(highlightBuilder);

        // 添加高亮查询到搜索源
        searchRequest.source(searchSourceBuilder);

        // 执行搜索   向 es 发起 http 请求
        SearchResponse searchResponse = null;

        try {
            searchResponse = client.search(searchRequest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Students> allInfo = obTain(searchResponse);

        return allInfo;
    }

    private List<Students> obTain(SearchResponse searchResponse) {
        // 搜索结果
        SearchHits hits = searchResponse.getHits();
        // 匹配到的总记录数
        long totalHits = hits.getTotalHits();
        // 得到匹配高度的文档
        SearchHit[] searchHits = hits.getHits();

        List<Students> allInfoBeans = new ArrayList<>();

        for (SearchHit hit : searchHits) {
            // 使用 Es 的 java 接口将实体类 对应的内容转换为  JSON
            String content = hit.getSourceAsString();

            // 生成 pojo 对象
            Students students = JSONObject.parseObject(content,Students.class);
            // 获取高亮查询内容，如果存在  则替换
            Map<String, HighlightField> highlightFieldMap = hit.getHighlightFields();

            if (highlightFieldMap != null){
                HighlightField nameField = highlightFieldMap.get("name");
                HighlightField descField = highlightFieldMap.get("desc");
                if (nameField != null){
                    Text[] fragments = nameField.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text fragment : fragments) {
                        stringBuffer.append(fragment.string());
                    }
                    String name = stringBuffer.toString();
                    students.setName(name);
                }
                if (descField != null){
                    Text[] fragments = descField.getFragments();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Text fragment : fragments) {
                        stringBuffer.append(fragment.string());
                    }
                    String desc = stringBuffer.toString();
                    students.setDesc(desc);
                }
            }
            allInfoBeans.add(students);
        }
        System.out.println("总记录数  --->  "+totalHits);
        return allInfoBeans;
    }

}