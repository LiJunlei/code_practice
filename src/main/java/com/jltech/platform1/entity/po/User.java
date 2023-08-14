package com.jltech.platform1.entity.po;

import com.jltech.core.entity.po.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * @author 李俊磊 
 * @title 用户表
 * @date 2023年08月15日 01:43:45
 * @description 存放平台用户表数据
 */

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "jl_platform_user")
@Where(clause = "if_deleted = 0")
public class User extends BaseEntity{


    /**
     * 用户年龄
     */
    @Column(columnDefinition = "INTEGER(2) COMMENT '用户年龄'")
    private Integer age ;

    /**
     * 名称
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '名称'")
    private String name ;

    /**
     * 性别
     */
    @Column(columnDefinition = "BIT(1) COMMENT '性别'")
    private Boolean gender ;

    /**
     * 邮箱
     */
    @Column(columnDefinition = "VARCHAR(255) COMMENT '邮箱'")
    private String email ;

}