package com.toior.community.controller;

import com.toior.community.mapper.UserMapper;
import com.toior.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(/*@RequestParam(name = "name") String name, Model model*/HttpServletRequest request) {


        Cookie[] cookies = request.getCookies();
        if (cookies.length <= 1) {
            return "index";
        }

        for (Cookie cookie :
                cookies) {
            if ("token".equals(cookie.getName())) {
                String token = cookie.getValue();
                if ("".equals(token) || token == null) {
                    break;
                }
                User user = userMapper.findByToken(token);

                if (user != null) {
                    request.getSession().setAttribute("user", user);
                }
                break;
            }
        }


        return "index";
    }
}
