package com.xiang.service;

import com.xiang.entity.Students;

import java.util.List;

/**
 * (Students)表服务接口
 *
 * @author Array
 * @since 2020-08-29 10:59:59
 */
public interface StudentsService {

    /**
     * 保存所有的学生信息到 Es 中
     *
     * @author Xiang想
     * @date 2020/8/29 11:20
     */
    void saveAllStudentInfo();


    /**
     * 高级查询
     *
     * @param text 查询文本
     * @return java.util.List<com.xiang.entity.Students>
     * @author Xiang想
     * @date 2020/8/29 14:06
     */
    List<Students> improveSreach(String text);
}