package com.baizhi.rlt.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONObject;
import com.baizhi.rlt.entity.User;
import com.baizhi.rlt.service.UserService;
import com.baizhi.rlt.util.HttpUtil;
import io.goeasy.GoEasy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    //根据性别时间查数量
    @RequestMapping("showUserTime")
    public Map showUserTime() {
        Map map = userService.showUserTime();
        return map;
    }

    //根据性比额查地区
    @RequestMapping("showMap")
    public Map showMap() {
        Map map = userService.showMap();
        return map;
    }


    //分页查
    @RequestMapping("selectPageUser")
    public Map selectPageUser(Integer page, Integer rows) {
        Map map = userService.selectPage(page, rows);
        return map;
    }

    //编辑判断是修改/删除/增加
    @RequestMapping("/saveUser")
    public Map saveUser(User user, String oper, String[] id) {
        HashMap hashMap = new HashMap();
        if ("add".equals(oper)) {
            userService.insert(user);
            hashMap.put("userId", user.getId());
        } else if ("edit".equals(oper)) {
            user.setPhoto(null);
            userService.update(user);
            hashMap.put("userId", user.getId());
        } else {
            userService.delete(Arrays.asList(id));
        }
        GoEasy goEasy = new GoEasy("http://rest-hangzhou.goeasy.io",
                "BC-c8cde44c365b4775b882f8e3a24094c3");
        Map map = showUserTime();
        String s = JSONObject.toJSONString(map);
        goEasy.publish("cmfz", s);
        return hashMap;
    }

    //文件上传
    @RequestMapping("/uploadUser")
    public Map uploadUser(MultipartFile photo, String userId, HttpServletRequest request) {

        String http = HttpUtil.getHttp(photo, request, "/upload/userImg/");
        //将文件存放到指定目录
        User user = new User();
        user.setId(userId);
        user.setPhoto(http);
        userService.update(user);

        HashMap hashMap = new HashMap();
        hashMap.put("status", 200);
        return hashMap;
    }

    @RequestMapping("login")
    public Map login(String phone,String password) {
        return userService.login(phone,password);
    }

}
