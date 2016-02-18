package me.test.weixin.service;

import me.test.weixin.mapper.UserMapper;
import me.test.weixin.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Buce on 2016/2/17.
 */
@Service
public class UserServiceImpl implements IUserService {

    //User接口
    @Autowired
    private UserMapper userMapper;

    public List<User> findUser() throws Exception {
        //调用mapper类中的selectByExample方法，如果传入类型为null，则表示无条件查找
        List<User> users = userMapper.selectByExample(null);
        return users;
    }
}
