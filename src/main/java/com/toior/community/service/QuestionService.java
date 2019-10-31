package com.toior.community.service;

import com.toior.community.dto.PaginationDto;
import com.toior.community.dto.QuestionDto;
import com.toior.community.mapper.QuestionMapper;
import com.toior.community.mapper.UserMapper;
import com.toior.community.model.Question;
import com.toior.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;


    public PaginationDto list(Integer page, Integer size) {


        Integer offset = size * (page - 1);
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage = 1;
        Integer totalCount = questionMapper.count();

        if (totalCount % size == 0) {
            totalPage = totalCount / size;


        }else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDto.setPagination(totalPage, page);




        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question: questions
             ) {
           User user =  userMapper.findById(question.getCreator());
//           User user =  userMapper.findByAccountId(String.valueOf(question.getCreator()));
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestionDtos(questionDtoList);




        return paginationDto;
    }


    public PaginationDto listByUserId(Integer userId, Integer page, Integer size) {
        Integer offset = size * (page - 1);
        PaginationDto paginationDto = new PaginationDto();
        Integer totalPage = 1;
        Integer totalCount = questionMapper.countByUserId(userId);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;


        }else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDto.setPagination(totalPage, page);

        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        List<QuestionDto> questionDtoList = new ArrayList<>();

        for (Question question: questions
        ) {
           User user =  userMapper.findById(question.getCreator());
//            User user =  userMapper.findByAccountId(String.valueOf(question.getCreator()));
            QuestionDto questionDto = new QuestionDto();
            BeanUtils.copyProperties(question, questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestionDtos(questionDtoList);




        return paginationDto;
    }
}
