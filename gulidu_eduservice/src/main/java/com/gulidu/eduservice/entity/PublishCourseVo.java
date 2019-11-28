package com.gulidu.eduservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PublishCourseVo {
    @ApiModelProperty("课程id")
    private String id;
    @ApiModelProperty("课程名称")
    private String title;
    @ApiModelProperty("课程封面")
    private String cover;
    @ApiModelProperty("课时数")
    private Integer lessonNum;
    @ApiModelProperty("一级")
    private String subjectLevelOne;
    @ApiModelProperty("二级")
    private String subjectLevelTwo;
    @ApiModelProperty("讲师名称")
    private String teacherName;
    @ApiModelProperty("价格")
    private String price;


}
