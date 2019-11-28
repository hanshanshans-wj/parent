package com.gulidu.eduservice.service;

import com.gulidu.eduservice.entity.ChapterVo;
import com.gulidu.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getAllChapterVideoCourse(String courseId);

    void addChapter(EduChapter eduChapter);

    void updateChapter(EduChapter chapter);

    void removeChapterById(String chapterId);

    void removeChapterByCouseId(String courseId);

}
