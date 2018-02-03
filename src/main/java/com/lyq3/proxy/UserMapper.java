package com.lyq3.proxy;

/**
 * 模仿Mybatis的Mapper接口
 */
public interface UserMapper {
    User findUserById(Integer id);
}
