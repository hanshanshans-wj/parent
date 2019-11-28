package com.gulidu.eduvod.Controller;

import com.guli.educommom.R;
import com.gulidu.eduvod.Service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@Api(description = "视频")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;
    @PostMapping("uploadFile")
    @ApiOperation("上传视频")
    public R uploadFile(MultipartFile file){
        String videoId=vodService.uploadFile(file);
        return R.ok().data("videoId",videoId);
    }
    @DeleteMapping("deleteAliVideo/{videoId}")
    @ApiOperation("删除视频")
    public R deleteAliVideo(@PathVariable String videoId){
        vodService.deleteAliVideo(videoId);
        return R.ok();
    }
    @DeleteMapping("deleteMoreVideo")
    @ApiOperation("删除多个视频")
    public R deleteMoreVideo(@RequestParam(value = "videoList") List videoList){
        vodService.deleteMoreVideo(videoList);
        return R.ok();
    }
}
