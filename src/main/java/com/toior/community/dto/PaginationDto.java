package com.toior.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDto {

    private List<QuestionDto> questionDtos;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer page;//current pageNum
    private List<Integer> pages = new ArrayList<>();//所有的页码数
    private Integer totalPage;




    public void setPagination(Integer totalPage, Integer page) {


        this.totalPage = totalPage;
        this.page = page;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) {
                pages.add(0, page - i);//add to the forehead
            }
            if (page + i <= totalPage) {
                pages.add(page + i);
            }

        }

        //
//prePage
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }
//nextPage
        if (page.equals(totalPage)) {
            showNext = false;
        } else {
            showNext = true;
        }
//firstPage
        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        //lastPage
        if (pages.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }


    }





}
