package com.gulidu.eduservice.service;

import com.gulidu.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
public interface EduVideoService extends IService<EduVideo> {

    void addVideo(EduVideo eduVideo);

    void updateVideo(EduVideo eduVideo);

    void removeByCourseId(String courseId);

    void removeVideoById(String vid);
}
