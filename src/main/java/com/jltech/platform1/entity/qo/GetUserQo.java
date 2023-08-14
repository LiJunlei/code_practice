package com.jltech.platform1.entity.qo;

import com.jltech.core.entity.qo.PagedAndSortRequestQo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 李俊磊 
 * @title 获取用户表列表查询
 * @date 2023年08月15日 01:43:45
 * @description 存放平台用户表数据
 */
@Getter
@Setter
@Schema(description = "获取用户表列表查询")
public class GetUserQo extends PagedAndSortRequestQo {

}