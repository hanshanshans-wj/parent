package com.gulidu.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.educommom.R;
import com.gulidu.eduservice.entity.EduTeacher;
import com.gulidu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/eduservice/frontteacher")
@Api(description = "教师分页所有列表")
public class EduTeacherAllController {
    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("getFrontTeacher/{page}/{limit}")
    @ApiOperation("分页查询所有属性教师")
    public R getFrontTeacher(@PathVariable Long page,
                             @PathVariable Long limit){
        Page<EduTeacher> eduTeacherPage = new Page<>(page,limit);
        Map<String,Object> map=eduTeacherService.getPageFrontListPage(eduTeacherPage);

        return R.ok().data(map);
    }
    @ApiOperation("根据讲师id查询讲师及课程信息")
    @GetMapping("getTeacherInfo/{tid}")
    public R getTeacherInfo(@PathVariable String tid){
        Map<String,Object> map=eduTeacherService.getTeacherInfoById(tid);
        return R.ok().data(map);
    }
}
