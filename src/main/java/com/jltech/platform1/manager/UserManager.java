package com.jltech.platform1.manager;

import com.jltech.platform1.dao.UserDao;
import com.jltech.platform1.entity.po.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * @author 李俊磊 
 * @title 用户表 方法管理
 * @date 2023年08月15日 01:43:45
 * @description 存放平台用户表数据
 */
@Component
public class UserManager {

    private final UserDao userDao;

    public UserManager(UserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * 根据id删除用户表
     *
     * @param id 用户表id
     */
    @Transactional(rollbackFor = Exception.class)
    // @OperationLog(message = "'用户表【'+ ,,, +'】(id:'+#id+')删除成功'", operationType = OperationTypeConstant.DELETE)
    public void deleteUser(String id) {
        Optional<User> userOptional = userDao.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userDao.delete(user);
        } else {
            throw new RuntimeException("id为【" + id + "】的用户表不存在或被删除");
        }
    }
}
