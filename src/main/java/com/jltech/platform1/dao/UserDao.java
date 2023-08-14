package com.jltech.platform1.dao;

import com.jltech.platform1.entity.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @author 李俊磊 
 * @title 用户表数据库交互
 * @date 2023年08月15日 01:43:45
 * @description 存放平台用户表数据
 */
public interface UserDao extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    // 根据ids查询
    List<User> findByIdIn(List<String> ids);

}