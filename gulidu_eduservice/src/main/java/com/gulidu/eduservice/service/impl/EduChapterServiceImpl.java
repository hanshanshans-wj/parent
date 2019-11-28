package com.gulidu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gulidu.eduservice.entity.ChapterVo;
import com.gulidu.eduservice.entity.EduChapter;
import com.gulidu.eduservice.entity.EduVideo;
import com.gulidu.eduservice.entity.VideoVo;
import com.gulidu.eduservice.handle.EduException;
import com.gulidu.eduservice.mapper.EduChapterMapper;
import com.gulidu.eduservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gulidu.eduservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;
    @Override
    public List<ChapterVo> getAllChapterVideoCourse(String courseId) {
        //查询章节集合
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();

        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

        //查询小节集合
        QueryWrapper<EduVideo> wrapper1 = new QueryWrapper<>();
        wrapper1.eq("course_id",courseId);
        List<EduVideo> listVideo = eduVideoService.list(wrapper1);

        //构建最终集合封装chaptervo
        ArrayList<ChapterVo> finalList = new ArrayList<>();


        //遍历章节集合
        for (int i = 0; i <eduChapters.size() ; i++) {
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);
            ArrayList<VideoVo> eduVideos = new ArrayList<>();
            //遍历小节集合
            for (int j = 0; j <listVideo.size(); j++) {
                EduVideo eduVideo = listVideo.get(j);
                //创建属于某个章节的小节集合

                if (chapterVo.getId().equals(eduVideo.getChapterId())){
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                   eduVideos.add(videoVo);
                }
                chapterVo.setChildren(eduVideos);
            }


        }
        return finalList;
    }

    @Override
    public void addChapter(EduChapter eduChapter) {
        //调用判断是否重复
        EduChapter eduChapter1 = this.ExistChapter(eduChapter.getTitle(), eduChapter.getCourseId());

        if (eduChapter1==null){

            int insert = baseMapper.insert(eduChapter);
            if (insert==0){
                throw new EduException(20001,"增加章节失败");
            }
        }else {
            throw new EduException(20001,"章节重复无法添加");
        }
    }

    @Override
    public void updateChapter(EduChapter chapter) {
        EduChapter eduChapter = this.ExistChapter(chapter.getTitle(), chapter.getCourseId());
        if (eduChapter==null){
            int i = baseMapper.updateById(chapter);
            if (i==0){
                throw new EduException(20001,"修改章节失败");
            }
        }else {
            throw new EduException(20001,"章节重复无法修改");
        }
    }

    @Override
    public void removeChapterById(String chapterId) {
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("chapter_id",chapterId);
        int count = eduVideoService.count(eduVideoQueryWrapper);
        if (count==0){
            int i = baseMapper.deleteById(chapterId);
            if (i==0){
                throw new EduException(20001,"删除章节失败");
            }
        }else{
            throw new EduException(20001,"有子章节删除章节失败");
        }
    }

    @Override
    public void removeChapterByCouseId(String courseId) {
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",courseId);
        baseMapper.delete(eduChapterQueryWrapper);
    }

    //判断章节是否重复
    private EduChapter ExistChapter(String chapterName,String courseId){
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("title",chapterName);
        eduChapterQueryWrapper.eq("course_id",courseId);
        EduChapter eduChapter = baseMapper.selectOne(eduChapterQueryWrapper);
        return eduChapter;
    }

}
