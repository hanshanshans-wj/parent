package com.gulidu.oss.controller.controller;

import com.guli.educommom.R;
import com.gulidu.oss.controller.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api("文件上传")
@CrossOrigin
@RequestMapping("/eduoss/oss")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 文件上传方法
     * @param file
     * @return
     */
    @ApiOperation(value = "上传文件")
    @PostMapping("uploadOss")
    public R uploadFile(@RequestParam MultipartFile file){
        String s = fileService.FileUpload(file);
        return R.ok().data("url",s);
    }
}
