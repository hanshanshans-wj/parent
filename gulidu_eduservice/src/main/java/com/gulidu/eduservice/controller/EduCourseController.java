package com.gulidu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.educommom.R;
import com.gulidu.eduservice.entity.CourseFormVo;
import com.gulidu.eduservice.entity.CourseInfoVo;
import com.gulidu.eduservice.entity.EduCourse;
import com.gulidu.eduservice.entity.PublishCourseVo;
import com.gulidu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/eduservice/edu-course")
@CrossOrigin
@Api(description = "课程管理")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiOperation(value = "增加课程")
    @PostMapping("saveCourseInfo")
    public R saveCourseInfo(@RequestBody CourseInfoVo courseInfoVo){
        String courseId = eduCourseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId",courseId);
    }
    @GetMapping("findCourseInfo/{courseId}")
    @ApiOperation("根据id查找课程")
    public R findCourseInfo(@PathVariable String courseId){
        CourseInfoVo courseInfoVo=eduCourseService.getCourseBaseInfo(courseId);

        return R.ok().data("courseInfoVo",courseInfoVo);
    }
    @ApiOperation("根据课程id查找课程确认信息")
    @GetMapping("getCourseInfoVo/{courseId}")
    public R getCourseInfoVo(@PathVariable String courseId){
        PublishCourseVo publishCourseVo=eduCourseService.getCourseInfoVo(courseId);
        return R.ok().data("publishCourseVo",publishCourseVo);
    }
    @ApiOperation("修改课程信息")
    @PostMapping("updateCourseInfo")
    public  R updateCourseInfo(@RequestBody CourseInfoVo courseInfoVo){

        eduCourseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }
    //课程发布
    @ApiOperation("课程发布")
    @PostMapping("publishCourse/{courseId}")
    public R publishCourse(@PathVariable String courseId){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(courseId);
        eduCourse.setStatus("Normal");
        eduCourseService.updateById(eduCourse);
        return R.ok();
    }
    @ApiOperation("获取带分页状态的课程列表")
    @PostMapping("getCoursePageList/{page}/{limit}")
    public R getCoursePageList(@PathVariable Long page, @PathVariable Long limit, @RequestBody(required = false) CourseFormVo courseFormVo){
    //创建page对象
        Page<EduCourse> coursePage = new Page<>(page,limit);
        eduCourseService.getCoursePageList(coursePage,courseFormVo);
        long total = coursePage.getTotal();//总记录数
        List<EduCourse> records = coursePage.getRecords();//每条记录
        return R.ok().data("total",total).data("records",records);

    }
    @ApiOperation("删除课程接口")
    @DeleteMapping("deleteCourse/{courseId}")
    public R deleteCourse(@PathVariable String courseId){
        eduCourseService.deleteCourse(courseId);
        return R.ok();
    }

}

