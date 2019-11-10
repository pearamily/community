package com.toior.community.controller;

import com.toior.community.dto.QuestionDto;
import com.toior.community.mapper.QuestionMapper;
import com.toior.community.model.Question;
import com.toior.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description TODO
 * @Author liuligong
 * @Date 2019-11-04 07:45
 * @Version 1.0
 **/

@Controller
public class QuestionController {


    @Autowired
    QuestionService questionService;

    @GetMapping("question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model) {


        QuestionDto questionDto = questionService.getById(id);
        model.addAttribute("question", questionDto);
        return "question";
    }
}
