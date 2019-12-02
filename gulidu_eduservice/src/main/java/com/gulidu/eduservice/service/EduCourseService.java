package com.gulidu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gulidu.eduservice.entity.*;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCourseInfo(CourseInfoVo courseInfoVo);

    CourseInfoVo getCourseBaseInfo(String courseId);

    void updateCourseInfo(CourseInfoVo courseInfoVo);

    PublishCourseVo getCourseInfoVo(String courseId);

    void getCoursePageList(Page<EduCourse> coursePage, CourseFormVo courseFormVo);

    void deleteCourse(String courseId);

    Map<String,Object> getCoursepageInfoList(Page<EduCourse> coursePage);

    CourseInfoDtoVo getCouseFrontById(String courseId);
}
