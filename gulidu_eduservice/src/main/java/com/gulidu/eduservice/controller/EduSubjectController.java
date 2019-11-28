package com.gulidu.eduservice.controller;


import com.guli.educommom.R;
import com.gulidu.eduservice.entity.EduSubject;
import com.gulidu.eduservice.entity.SubjectOneVo;
import com.gulidu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/eduservice/edu-subject")
@Api("课程分类")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @PostMapping("addSubjectOne")
    @ApiOperation("增加一级节点")
    public R addSubjectOne(@RequestBody EduSubject eduSubject){
        eduSubjectService.addSubjectOne(eduSubject);
        return R.ok();
    }
    @PostMapping("addSubjectTwo")
    @ApiOperation("增加二级节点")
    public R addSubjectTwo(@RequestBody EduSubject eduSubject){
        eduSubjectService.addSubjectTwo(eduSubject);
        return R.ok();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除课程节点")
    public R deleteSubjectById(@PathVariable String id){
        boolean i=eduSubjectService.deleteById(id);
        if (i){
            return R.ok();
        }else {
            return R.error();
        }

    }

    /**
     * 获取分类
     * @return
     */
    @GetMapping("getdata")
    @ApiOperation(value = "获取一二级分类方法")
    public R getAllSubject(){
        List<SubjectOneVo> allSubject = eduSubjectService.getAllSubject();
        return R.ok().data("childrenList",allSubject);
    }
    /**
     * 将excel中数据导入数据库
     * @param file
     * @return
     */
    @PostMapping("importdata")
    public R eduSubjectImportData(MultipartFile file){
        //上传过来excel为空
        List<String> msg=eduSubjectService.importData(file);
        //判断返回的信息是否为空
        if (msg.size()==0){
            return R.ok();
        }else {
            return R.error().message("部分数据导入失败").data("msg",msg);
        }

    }
}

