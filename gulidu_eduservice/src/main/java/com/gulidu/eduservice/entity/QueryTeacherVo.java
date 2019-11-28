package com.gulidu.eduservice.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Api(description = "查询信息类")
public class QueryTeacherVo {
    @ApiModelProperty(value = "教师名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "头衔 1高级讲师 2首席讲师")
    private Integer level;
    @ApiModelProperty(value = "开始时间",example = "2018-01-01 10:10:10")
    private String begin;
    @ApiModelProperty(value = "结束时间",example = "2018-03-01 10:10:10")
    private String end;
}
