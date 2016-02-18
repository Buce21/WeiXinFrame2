package me.test.weixin.service;

import me.test.weixin.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2016/2/17.
 */
public interface IUserService {
    /**
     * 查找所有用户
     * @return
     * @throws Exception
     */
    List<User> findUser()throws Exception;
}
