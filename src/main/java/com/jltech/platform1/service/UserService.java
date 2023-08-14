package com.jltech.platform1.service;

import com.jltech.core.entity.dto.DeletedInput;
import com.jltech.core.entity.dto.PagedResultDto;
import com.jltech.platform1.entity.dto.user.CreateOrUpdateUserInput;
import com.jltech.platform1.entity.dto.user.GetUserForEditOutput;
import com.jltech.platform1.entity.dto.user.UserItemDto;
import com.jltech.platform1.entity.qo.GetUserQo;

/**
 * @author 李俊磊 
 * @title 用户表服务接口
 * @date 2023年08月15日 01:43:45
 * @description 存放平台用户表数据
 */
public interface UserService {

    /**
     * 获取用户表列表
     *
     * @param qo 用户表列表查询
     * @return 用户表列表
     */
    PagedResultDto<UserItemDto> getUserList(GetUserQo qo);

    /**
     * 获取用户表用于编辑
     *
     * @param id id
     * @return 用户表编辑
     */
    GetUserForEditOutput getUserForEdit(String id);

    /**
     * 添加用户表
     *
     * @param input 添加用户表输入
     */
    void createUser(CreateOrUpdateUserInput input);

    /**
     * 编辑用户表
     *
     * @param input 编辑用户表输入
     */
    void updateUser(CreateOrUpdateUserInput input);

    /**
     * 删除用户表
     *
     * @param input 删除输入
     */
    void deletedUser(DeletedInput input);
}