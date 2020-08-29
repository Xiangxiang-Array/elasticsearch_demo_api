package com.xiang.dao;

import com.xiang.entity.Students;

import java.util.List;

/**
 * (Students)表数据库访问层
 *
 * @author Array
 * @since 2020-08-29 10:59:59
 */
public interface StudentsDao {

    /**
     * 获得所有学生信息
     * @return com.xiang.entity.Students
     * @author Xiang想
     * @date 2020/8/29 11:17
     */
    List<Students> selectAllStudent();

}