package me.test.weixin.service;

import me.test.weixin.pojo.Users;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
public interface IUsersService {
    List<Users> findUser(String username);
}
