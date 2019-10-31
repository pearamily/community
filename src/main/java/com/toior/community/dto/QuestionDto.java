package com.toior.community.dto;

import com.toior.community.model.User;
import lombok.Data;

@Data
public class QuestionDto {

    private  Integer id;
    private  String title;
    private  String description;
    private  String tag;
    private  Long gmtCreate;
    private  Long gmtModified;
    private String creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCount;
    private User user;

}
