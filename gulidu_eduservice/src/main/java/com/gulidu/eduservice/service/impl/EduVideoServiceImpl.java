package com.gulidu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gulidu.eduservice.client.VodClient;
import com.gulidu.eduservice.entity.EduVideo;
import com.gulidu.eduservice.handle.EduException;
import com.gulidu.eduservice.mapper.EduVideoMapper;
import com.gulidu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-11-20
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
    private VodClient vodClient;
    @Override
    public void addVideo(EduVideo eduVideo) {
        EduVideo eduVideo1 = this.existEduVideo(eduVideo.getTitle(),eduVideo.getCourseId(),eduVideo.getChapterId(),eduVideo.getVideoSourceId());
        if (eduVideo1==null){
            int insert = baseMapper.insert(eduVideo);
            if(insert==0){
                throw new EduException(20001,"添加小节失败");
            }
        }

    }

    @Override
    public void updateVideo(EduVideo eduVideo) {
        EduVideo eduVideo1 = this.existEduVideo(eduVideo.getTitle(),eduVideo.getCourseId(),eduVideo.getChapterId(),eduVideo.getVideoSourceId());
        if (eduVideo1==null){
            baseMapper.updateById(eduVideo);
        }else {
            throw new EduException(20001,"重复修改失败");
        }
    }

    @Override
    public void removeByCourseId(String courseId) {


        //获取课程里所有的视频id,把其封装成一个集合
        ArrayList<String> IdsLists = new ArrayList<>();

        //根据课程id获取所有的小节，每个小节中有视频id
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = baseMapper.selectList(wrapper);
        for (int i = 0; i <eduVideos.size(); i++) {
            //得到每个小节
            EduVideo eduVideo = eduVideos.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if (!StringUtils.isEmpty(videoSourceId)){
                IdsLists.add(videoSourceId);
            }


        }
        //删除课程里所有视频
        if (IdsLists.size()!=0){
            vodClient.deleteMoreVideo(IdsLists);
        }



        //删除课程里所有小节

        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        baseMapper.delete(eduVideoQueryWrapper);
    }
//删除小节时同时删除视频
    @Override
    public void removeVideoById(String vid) {
        //通过小节id获取视频id
        EduVideo eduVideo = baseMapper.selectById(vid);
        //获取视频id
        String videoSourceId = eduVideo.getVideoSourceId();
        if (!StringUtils.isEmpty(videoSourceId)){
            vodClient.deleteAliVideo(videoSourceId);
        }
        //删除小节
        baseMapper.deleteById(vid);
    }

    //判断是否重复方法
    public EduVideo existEduVideo(String videoName,String courseId,String chapterId,String videoSourceId){
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        eduVideoQueryWrapper.eq("chapter_id",chapterId);
        eduVideoQueryWrapper.eq("title",videoName);
        eduVideoQueryWrapper.eq("video_source_id",videoSourceId);
        EduVideo eduVideo1 = baseMapper.selectOne(eduVideoQueryWrapper);
        return eduVideo1;

    }
}
