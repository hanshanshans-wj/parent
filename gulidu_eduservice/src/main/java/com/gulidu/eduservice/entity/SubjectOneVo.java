package com.gulidu.eduservice.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
/**
 * 第一个科目vo，封装id和title
 */
public class SubjectOneVo {

    private String id;
    private String title;
    private List<SubjectTwoVo> children=new ArrayList<>();
}
