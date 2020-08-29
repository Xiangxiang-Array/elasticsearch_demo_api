package com.xiang.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * (Students)实体类
 *
 * @author Array
 * @since 2020-08-29 10:59:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
/**
 * 指定我们操作的 索引 和 表
 */
@Document(indexName = "rsdoct",type = "rsbean")
public class Students implements Serializable {
    private static final long serialVersionUID = 218678033963973737L;
    /**
    * 标识
    */
    @Id
    private Long id;
    /**
    * 姓名
    */
    @Field(type = FieldType.Text)
    private String name;
    /**
    * 详细信息
    */
    @Field(type = FieldType.Text)
    private String desc;


}