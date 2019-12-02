package com.gulidu.eduservice.mapper;

import com.gulidu.eduservice.entity.CourseInfoDtoVo;
import com.gulidu.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gulidu.eduservice.entity.PublishCourseVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {


    PublishCourseVo getCourseInfoVo(String courseId);


    CourseInfoDtoVo getCouseFrontById(String courseId);
}
