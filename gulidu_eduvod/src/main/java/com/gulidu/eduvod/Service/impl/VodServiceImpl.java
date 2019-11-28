package com.gulidu.eduvod.Service.impl;

import com.aliyun.vod.upload.UploadVideo;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;

import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.gulidu.eduvod.Service.VodService;
import com.gulidu.eduvod.utils.AliyunVodUtils;
import com.gulidu.eduvod.utils.ConstantVodUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadFile(MultipartFile file) {
        //第一个参数 oss的id
        //第二个参数 oss的秘钥
        //第三个参数 视频显示名称，建议上传文件名称不带后缀名值
        //第四个参数 文件原始名称
        //第五个参数 文件输入流
        //123.11.1.mp4
        try {
            String filename = file.getOriginalFilename();
            //获取视频显示名称
            String title = filename.substring(0, filename.lastIndexOf("."));
            //文件输入流
            InputStream inputStream = file.getInputStream();
            UploadStreamRequest uploadStreamRequest = new UploadStreamRequest(ConstantVodUtil.ACCESS_ID, ConstantVodUtil.ACCESS_KEY_SECRET, title, filename, inputStream);
            uploadStreamRequest.setCoverURL("https://gaoji.oss-cn-beijing.aliyuncs.com/2019/11/22fb323ac1f71e438f8e5b89bae6d30515product-4.jpg");
            UploadVideo uploadVideo = new UploadVideoImpl();
            UploadStreamResponse uploadStreamResponse = uploadVideo.uploadStream(uploadStreamRequest);
            String videoId=null;
            if (uploadStreamResponse.isSuccess()){
                videoId = uploadStreamResponse.getVideoId();
            }else {
                videoId = uploadStreamResponse.getVideoId();
            }
            return videoId;

        }catch (Exception e){
            e.printStackTrace();
            return null;

        }

    }

    @Override
    public void deleteAliVideo(String videoId) {
        //创建初始化对象
        try {
            DefaultAcsClient client = AliyunVodUtils.initVodClient(ConstantVodUtil.ACCESS_ID, ConstantVodUtil.ACCESS_KEY_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(videoId);
            client.getAcsResponse(request);

        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
//删除多个视频
    @Override
    public void deleteMoreVideo(List videoList) {
        //初始化对象
        try {
            DefaultAcsClient client = AliyunVodUtils.initVodClient(ConstantVodUtil.ACCESS_ID, ConstantVodUtil.ACCESS_KEY_SECRET);
            //创建删除的request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request设置视频id   1,2,3
            //videoIdList集合转换 id1,id2,id3
            String videolists=StringUtils.join(videoList.toArray(),",");
            System.out.println("join\t"+ videolists);
            request.setVideoIds(videolists);
            client.getAcsResponse(request);


        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
