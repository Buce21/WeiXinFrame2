package me.test.weixin.service;

import me.test.weixin.mapper.UsersMapper;
import me.test.weixin.pojo.Users;
import me.test.weixin.pojo.UsersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2016/2/19.
 */
@Service
public class UsersServiceImpl implements IUsersService {

    @Autowired
    private UsersMapper usersMapper;

    public List<Users> findUser(String username) {
        UsersExample usersExample = new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<Users> user = usersMapper.selectByExample(usersExample);
        return user;
    }
}
