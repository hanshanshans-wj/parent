package com.gulidu.eduservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseInfoDtoVo {

    private String id;
    @ApiModelProperty("课程标题")
    private String title;
    @ApiModelProperty(value = "课程销售价格，设置为0则可免费观看")
    private String price;
    @ApiModelProperty("课程总课时")
    private String lessonNum;
    @ApiModelProperty("课程封面")
    private String cover;
    @ApiModelProperty("销售数量")
    private String buyCount;
    @ApiModelProperty("浏览数量")
    private String viewCount;
    @ApiModelProperty(value = "课程简介")
    private String description;
    @ApiModelProperty(value = "讲师ID")
    private String teacherId;
    @ApiModelProperty(value = "讲师姓名")
    private String teacherName;
    @ApiModelProperty(value = "讲师简介")
    private String intro;
    @ApiModelProperty(value = "讲师头像")
    private String avatar;
    @ApiModelProperty(value = "课程类别ID")
    private String subjectLevelOneId;
    @ApiModelProperty(value = "课程类别名称")
    private String subjectLevelOne;
    @ApiModelProperty(value = "课程类别ID")
    private String subjectLevelTwoId;
    @ApiModelProperty(value = "课程类别名称")
    private String subjectLevelTwo;


}
