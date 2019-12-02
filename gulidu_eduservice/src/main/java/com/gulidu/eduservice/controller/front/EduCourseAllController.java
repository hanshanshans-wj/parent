package com.gulidu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.educommom.R;
import com.gulidu.eduservice.entity.ChapterVo;
import com.gulidu.eduservice.entity.CourseInfoDtoVo;
import com.gulidu.eduservice.entity.EduChapter;
import com.gulidu.eduservice.entity.EduCourse;
import com.gulidu.eduservice.service.EduChapterService;
import com.gulidu.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/eduservice/edufrontcourse")
@CrossOrigin
public class EduCourseAllController {
    @Autowired
    private EduCourseService eduCourseService;
    @Autowired
    private EduChapterService eduChapterService;
    @ApiOperation("查询课程分页详细信息")
    @GetMapping("getCourseInfoAll/{page}/{limit}")
    public R getCourseInfoAll(@PathVariable Long page,@PathVariable Long limit){
        Page<EduCourse> coursePage = new Page<>(page, limit);
        Map<String,Object> map=eduCourseService.getCoursepageInfoList(coursePage);
        return R.ok().data(map);
    }
    @ApiOperation("根据课程id详细信息")
    @GetMapping("getCouseFrontById/{courseId}")
    public R getCouseFrontById(@PathVariable String courseId){
        //根据课程id查询信息
       CourseInfoDtoVo courseInfoDtoVo =eduCourseService.getCouseFrontById(courseId);
       //根据课程id查询课程大纲，小节及视频返回集合
        List<ChapterVo> allChapterVideoCourse = eduChapterService.getAllChapterVideoCourse(courseId);
        return R.ok().data("allChapterVideoCourse",allChapterVideoCourse).data("courseInfoDtoVo",courseInfoDtoVo);
    }
}
