package com.gulidu.oss.controller.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public String FileUpload(MultipartFile file);
}
