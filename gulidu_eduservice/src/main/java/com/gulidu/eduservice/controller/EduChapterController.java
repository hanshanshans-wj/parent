package com.gulidu.eduservice.controller;


import com.guli.educommom.R;
import com.gulidu.eduservice.entity.ChapterVo;
import com.gulidu.eduservice.entity.EduChapter;
import com.gulidu.eduservice.service.EduChapterService;
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
@RequestMapping("/eduservice/edu-chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService eduChapterService;


    @GetMapping("getAllChapterVideoCourse/{courseId}")
    public R getAllChapterVideoCourse(@PathVariable String courseId){
        List<ChapterVo> chapterVoList=eduChapterService.getAllChapterVideoCourse(courseId);
        return R.ok().data("chapterVoList",chapterVoList);
    }
    @ApiOperation("增加章节")
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter){

        eduChapterService.addChapter(eduChapter);
        return R.ok();
    }
    @ApiOperation("根据id查询章节")
    @GetMapping("getChapterById/{chapterId}")
    public R getChapterById(@PathVariable String chapterId){
        EduChapter eduChapter = eduChapterService.getById(chapterId);
        return R.ok().data("eduChapter",eduChapter);
    }
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter chapter){
        eduChapterService.updateChapter(chapter);
        return R.ok();
    }
    @DeleteMapping("deleteChapter/{chapterId}")
    public R deleteChapter(@PathVariable String chapterId){

        eduChapterService.removeChapterById(chapterId);
        return R.ok();
    }
}

