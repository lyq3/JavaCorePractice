package com.lyq3.proxy;

/**
 * 实现类
 * @author 卡卢比
 */
public class UserMapperImpl implements UserMapper{
    @Override
    public User findUserById(Integer id) {
        User user = new User();
        user.setId(1);
        user.setName("卡卢比");
        System.out.println(user.getName());
        return user;
    }
}
