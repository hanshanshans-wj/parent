package com.gulidu.eduvod.Service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    String uploadFile(MultipartFile file);

    void deleteAliVideo(String videoId);

    void deleteMoreVideo(List videoList);

}
