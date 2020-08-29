package com.xiang.controller;

import com.xiang.dao.EsDao;
import com.xiang.entity.Students;
import com.xiang.service.StudentsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Students)表控制层
 *
 * @author Array
 * @since 2020-08-29 10:59:59
 */
@RestController
public class StudentsController {
    /**
     * 服务对象
     */
    @Resource
    private StudentsService studentsService;

    @Resource
    private EsDao esDao;

    /**
     * 把 数据库 学生表数据 插入到 es 中
     * @author Xiang想
     * @date 2020/8/29 15:00
     */
    @RequestMapping("/saveInfoToEs")
    public void getTitle(){
        System.out.println("======");
        studentsService.saveAllStudentInfo();
    }

    @RequestMapping("/findEs")
    public Iterable<Students> findEs(){
        Iterable<Students> all = esDao.findAll();
        return all;
    }

    /**
     *  高级查询 高亮索引字段
     * @param text 输入文本
     * @return java.util.List<com.xiang.entity.Students>
     * @author Xiang想
     * @date 2020/8/29 15:04
     */
    @GetMapping("/search")
    public List<Students> search(String text){
        return studentsService.improveSreach(text);
    }

}