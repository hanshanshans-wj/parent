package com.gulidu.eduservice.controller;


import com.guli.educommom.R;
import com.gulidu.eduservice.entity.EduVideo;
import com.gulidu.eduservice.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
@RestController
@RequestMapping("/eduservice/edu-video")
@CrossOrigin
@Slf4j
public class EduVideoController {
    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation("增加小節")
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        System.out.println(eduVideo);
        eduVideoService.addVideo(eduVideo);
        return R.ok();
    }

    @ApiOperation("查询小節")
    @GetMapping("getVideoById/{videoId}")
    public R getVideoById(@PathVariable String videoId){
        EduVideo eduVideo = eduVideoService.getById(videoId);

        return R.ok().data("eduVideo",eduVideo);
    }
    @ApiOperation("修改小节")
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        eduVideoService.updateVideo(eduVideo);
        return R.ok();
    }
    @ApiOperation("删除小节")
    @DeleteMapping("deleteVideo/{videoId}")
    public R deleteVideo(@PathVariable String videoId){
        eduVideoService.removeVideoById(videoId);
        return R.ok();
    }


}

