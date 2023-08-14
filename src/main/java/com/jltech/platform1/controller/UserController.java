package com.jltech.platform1.controller;

import com.jltech.core.entity.dto.DeletedInput;
import com.jltech.core.entity.dto.PagedResultDto;
import com.jltech.platform1.entity.dto.user.CreateOrUpdateUserInput;
import com.jltech.platform1.entity.dto.user.GetUserForEditOutput;
import com.jltech.platform1.entity.dto.user.UserItemDto;
import com.jltech.platform1.entity.qo.GetUserQo;
import com.jltech.platform1.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李俊磊 
 * @title 用户表控制器
 * @date 2023年08月15日 01:43:45
 * @description 存放平台用户表数据
 */
@Tag(name = "用户表控制器")
@RestController
@RequestMapping("/api/platform/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 获取用户表列表
     *
     * @param qo 获取用户表列表输入
     * @return 用户表列表
     */
    @Operation(summary = "获取用户表列表")
    @GetMapping("/getUserList")
//    @PreAuthorize("hasAnyAuthority('" + AppPermissions.PlatformManager.UserManager.view + "')")
    public PagedResultDto<UserItemDto> getUserList(GetUserQo qo) {
        return userService.getUserList(qo);
    }

    /**
     * 获取用户表编辑信息
     *
     * @param id 用户表id
     * @return 用户表编辑信息
     */
    @Operation(summary = "获取用户表编辑信息")
    @GetMapping("/getUserForEdit")
//    @PreAuthorize("hasAnyAuthority('" + AppPermissions.PlatformManager.UserManager.create + "','" + AppPermissions.PlatformManager.UserManager.edit + "')")
    public GetUserForEditOutput getUserForEdit(String id) {
        return userService.getUserForEdit(id);
    }

    /**
     * 保存用户表
     *
     * @param input 保存用户表输入
     */
    @Operation(summary = "保存用户表")
    @PostMapping("/createOrUpdateUser")
//    @PreAuthorize("hasAnyAuthority('" + AppPermissions.PlatformManager.UserManager.create + "','" + AppPermissions.PlatformManager.UserManager.edit + "')")
    public void createOrUpdateUser(@RequestBody CreateOrUpdateUserInput input) {
        if (StringUtils.hasText(input.getId())) {
            // 修改
            userService.updateUser(input);
        } else {
            // 新增
            userService.createUser(input);
        }
    }

    /**
     * 删除用户表
     *
     * @param input 删除用户表输入
     */
    @Operation(summary = "删除用户表")
    @PostMapping("/deleteUser")
//    @PreAuthorize("hasAnyAuthority('" + AppPermissions.PlatformManager.UserManager.del + "')")
    public void deleteUser(@RequestBody DeletedInput input) {
        userService.deletedUser(input);
    }
}
