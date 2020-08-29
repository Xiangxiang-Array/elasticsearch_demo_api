package com.xiang.dao;

import com.xiang.entity.Students;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Xiang想
 * @title: EsDao
 * @projectName elasticsearch_demo
 * @description: TODO
 * @date 2020/8/29  11:18
 */
public interface EsDao extends CrudRepository<Students,Long> {

}
