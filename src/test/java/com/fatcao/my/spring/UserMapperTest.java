package com.fatcao.my.spring;

import com.fatcao.my.spring.dto.User;
import com.fatcao.my.spring.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: FatCao
 * @Date: 2019-12-22 21:08
 */
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void saveTest(){
        User user = new User();
        user.setId(1);
        user.setName("肥曹");
        user.setPassword("123456");
        userMapper.insert(user);
    }
}
