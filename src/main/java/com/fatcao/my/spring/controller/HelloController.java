package com.fatcao.my.spring.controller;

import com.fatcao.my.spring.dto.User;
import com.fatcao.my.spring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: FatCao
 * @Date: 2019-12-21 22:44
 */
@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String sayHi() {
        return "Hello Spring Boot";
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getUser(@PathVariable Integer id) {
        User user = userMapper.selectById(id);
        return user == null ? "没有这个用户" : user.toString();
    }



    // stream 用法
    /*public RoleTypeEnum findUserRoleType(String uid) {
        // 查询用户角色列表
        List<RoleDto> roleDtos = PermissionUtil.findUserRoles(uid);
        if (CollectionUtils.isEmpty(roleDtos)){
            return null;
        }
        return Arrays.stream(RoleTypeEnum.values()).filter(r->
                roleDtos.stream().map(RoleDto::getCode).anyMatch(s->StringUtils.isNotBlank(s) && s.equals(r.getCode()))
        ).findAny().orElse(null);
    }*/
}
