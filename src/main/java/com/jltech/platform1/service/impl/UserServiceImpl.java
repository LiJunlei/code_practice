package com.jltech.platform1.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.jltech.core.entity.dto.DeletedInput;
import com.jltech.core.entity.dto.PagedResultDto;
import com.jltech.util.SortUtil;
import com.jltech.platform1.dao.UserDao;
import com.jltech.platform1.entity.dto.user.CreateOrUpdateUserInput;
import com.jltech.platform1.entity.dto.user.GetUserForEditOutput;
import com.jltech.platform1.entity.dto.user.UserItemDto;
import com.jltech.platform1.entity.po.User;
import com.jltech.platform1.entity.qo.GetUserQo;
import com.jltech.platform1.manager.UserManager;
import com.jltech.platform1.service.UserService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * @author 李俊磊 
 * @title 用户表服务实现
 * @date 2023年08月15日 01:43:45
 * @description 存放平台用户表数据
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final UserManager userManager;

    public UserServiceImpl(UserDao userDao, UserManager userManager) {
        this.userDao = userDao;
        this.userManager = userManager;
    }

    /**
     * 获取用户表列表
     *
     * @param qo 用户表列表查询
     * @return 用户表列表
     */
    public PagedResultDto<UserItemDto> getUserList(GetUserQo qo) {
        // 排序
        Sort sort = SortUtil.getDefaultSort(qo.getSortField(), qo.getSortType());
        // 分页
        Pageable pageable = PageRequest.of(qo.getPage() - 1, qo.getLimit(), sort);
        // 用户表查询
        Specification<User> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> list = new ArrayList<>();
            if (CollectionUtil.isNotEmpty(list)) {
                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
            return null;
        };
        // 数据库查询
        Page<User> userPage = userDao.findAll(specification, pageable);
        LinkedList<UserItemDto> items = new LinkedList<>();
        for (User user : userPage.getContent()) {
            // dto赋值
            UserItemDto item = new UserItemDto();
            BeanUtils.copyProperties(user, item);
            items.add(item);
        }
        return new PagedResultDto<>(userPage.getTotalElements(), items);
    }

    /**
     * 获取用户表用于编辑
     *
     * @param id 用户表id
     * @return 用户表编辑
     */
    public GetUserForEditOutput getUserForEdit(String id) {
        // 初始化空对象
        GetUserForEditOutput output = new GetUserForEditOutput();
        // 如果id存在，从数据库中获取
        if (StringUtils.hasText(id)) {
            Optional<User> userOptional = userDao.findById(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                BeanUtils.copyProperties(user, output);
            } else {
                throw new RuntimeException("id为【" + id + "】的用户表不存在或被删除");
            }
        }
        return output;
    }

    /**
     * 添加用户表
     *
     * @param input 添加用户表输入
     */
    // @OperationLog(message = "'用户表【'+  +'】创建成功'", operationType = OperationTypeConstant.INSERT)
    public void createUser(CreateOrUpdateUserInput input) {
        User user = new User();
        // 赋值
        BeanUtils.copyProperties(input, user);
        userDao.save(user);
    }


    /**
     * 编辑用户表
     *
     * @param input 编辑用户表输入
     */
    // @OperationLog(message = "'用户表【'+ ,,, +'】(id:'+#input.id+')更新成功'", operationType = OperationTypeConstant.UPDATE)
    public void updateUser(CreateOrUpdateUserInput input) {
        Optional<User> userOptional = userDao.findById(input.getId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            BeanUtils.copyProperties(input, user);
            userDao.save(user);
        } else {
            throw new RuntimeException("id为【" + input.getId() + "】的用户表不存在或被删除");
        }
    }

    /**
     * 删除用户表
     *
     * @param input 删除输入
     */
    public void deletedUser(DeletedInput input) {
        // 如果id存在，则根据id删除
        if (StringUtils.hasText(input.getId())) {
            userManager.deleteUser(input.getId());
        }
        // 如果ids数组不为空,则根据Id批量删除
        if (CollectionUtil.isNotEmpty(input.getIds())) {
            for (String id : input.getIds()) {
                userManager.deleteUser(id);
            }
        }
    }
}