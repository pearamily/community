package com.toior.community.controller;

import com.toior.community.dto.PaginationDto;
import com.toior.community.mapper.UserMapper;
import com.toior.community.model.User;
import com.toior.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {


    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(/*@RequestParam(name = "name") String name, Model model*/
            HttpServletRequest request,
            Model model
            , @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {


        PaginationDto pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        return "index";
    }
}
