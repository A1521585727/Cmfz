package com.baizhi.rlt;

import com.alibaba.fastjson.JSON;
import com.baizhi.rlt.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(value = SpringRunner.class)
@SpringBootTest(classes = CmfzApplication.class)
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public  void login() {
        Map login = userService.login("12345678910", "12345678910");
        System.out.println(JSON.toJSONString(login));
    }

}
