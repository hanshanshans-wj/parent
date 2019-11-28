package com.gulidu.eduservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseFormVo {
    @ApiModelProperty("课程名称")
    private String title;
    @ApiModelProperty("课程状态")
    private String status;
}
