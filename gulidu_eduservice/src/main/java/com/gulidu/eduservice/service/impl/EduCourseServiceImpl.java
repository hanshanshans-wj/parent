package com.gulidu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gulidu.eduservice.entity.*;
import com.gulidu.eduservice.handle.EduException;
import com.gulidu.eduservice.mapper.EduCourseMapper;
import com.gulidu.eduservice.service.EduChapterService;
import com.gulidu.eduservice.service.EduCourseDescriptionService;
import com.gulidu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gulidu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;
    @Autowired
    private EduVideoService eduVideoService;
    @Autowired
    private EduChapterService eduChapterService;
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //课程基本信息添加课程表

        //courseInfoVo变成EduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);

        int insert = baseMapper.insert(eduCourse);
        //判断课程是否添加成功
        if (insert==0){
            throw new EduException(20001,"添加课程信息失败");
        }
        //获取添加之后课程id
        String courseId = eduCourse.getId();
        //课程描述信息添加描述表
        //课程和描述是一对一关系，设置课程id到描述对象里面
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setId(courseId);
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescriptionService.save(eduCourseDescription);
        return courseId;
    }

    @Override
    public CourseInfoVo getCourseBaseInfo(String courseId) {
        EduCourse eduCourse = baseMapper.selectById(courseId);
        CourseInfoVo courseInfoVo = new CourseInfoVo();
        BeanUtils.copyProperties(eduCourse,courseInfoVo);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getById(courseId);
        String description = eduCourseDescription.getDescription();
        courseInfoVo.setDescription(description);
        return courseInfoVo;
    }

    @Override
    public void updateCourseInfo(CourseInfoVo courseInfoVo) {
        //课程
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo,eduCourse);
        int i = baseMapper.updateById(eduCourse);
        if (i==0){
            throw new EduException(20001,"修改失败");
        }
        EduCourseDescription eduCourseDescription = new EduCourseDescription();
        eduCourseDescription.setDescription(courseInfoVo.getDescription());
        eduCourseDescription.setId(courseInfoVo.getSubjectId());
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    @Override
    public PublishCourseVo getCourseInfoVo(String courseId) {
        PublishCourseVo publishCourseVo=baseMapper.getCourseInfoVo(courseId);
        return publishCourseVo;
    }

    @Override
    public void getCoursePageList(Page<EduCourse> coursePage, CourseFormVo courseFormVo) {
        //获取两个属性
        String status = courseFormVo.getStatus();
        String title = courseFormVo.getTitle();
        //判断条件是否为空
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(status)){
            eduCourseQueryWrapper.eq("status",status);
        }
        if (!StringUtils.isEmpty(title)){
            eduCourseQueryWrapper.like("title",title);
        }
        //调用方法进行查询
        baseMapper.selectPage(coursePage,eduCourseQueryWrapper);
    }

    @Override
    public void deleteCourse(String courseId) {
        //删除课程小节
        eduVideoService.removeByCourseId(courseId);
        //删除章节
        eduChapterService.removeChapterByCouseId(courseId);
        //删除课程描述
        eduCourseDescriptionService.removeById(courseId);
        //删除课程本身
        int i = baseMapper.deleteById(courseId);
        if (i==0){
            throw new EduException(20001,"删除小节失败");
        }
    }
}
