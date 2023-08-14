package com.jltech.platform1.entity.dto.user;

import com.jltech.core.entity.dto.EntityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


/**
 * @author 李俊磊 
 * @title 获取用户表信息
 * @date 2023年08月15日 01:43:45
 * @description 存放平台用户表数据
 */
@Data
@Schema(description = "获取用户表信息")
public class GetUserForEditOutput extends EntityDto {
    /**
     * 用户年龄
     */
    @Schema(description = "用户年龄")
    private Integer age ;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name ;

    /**
     * 性别
     */
    @Schema(description = "性别")
    private Boolean gender ;

    /**
     * 邮箱
     */
    @Schema(description = "邮箱")
    private String email ;

}