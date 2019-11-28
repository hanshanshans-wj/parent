package com.gulidu.oss.controller.service.impl;

import com.aliyun.oss.OSSClient;
import com.gulidu.oss.controller.service.FileService;
import com.gulidu.oss.controller.utils.ConstantProperty;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String FileUpload(MultipartFile file) {
        //地域节点
        String endPoint= ConstantProperty.END_POINT;
        //oss id
        String AccessKeyId=ConstantProperty.ACCESS_KEY_ID;
        //阿里云oss 的秘钥
        String AccessKeySecret=ConstantProperty.ACCESS_KEY_SECRET;
        //桶的名字
        String bucketName=ConstantProperty.BUCKET_NAME;

        //创建阿里云oss对象，因为使用 2.8.3  对象名称OSSClient
        OSSClient ossClient=new OSSClient(endPoint,AccessKeyId,AccessKeySecret);
        //调用oss对象里面的方法实现上传操作
        //有三个参数
        //第一个参数bucket名称
        //第二个参数 文件路径和名称
        //第三个参数 文件输入流





        try {
            //上传文件名称
            String fileName=file.getOriginalFilename();
            String uuid= UUID.randomUUID().toString().replaceAll("-","");
            fileName=uuid+fileName;
            String dataUrl=new DateTime().toString("yyyy/MM/dd");
            fileName=dataUrl+fileName;
            //文件输入流
            InputStream in=file.getInputStream();
            ossClient.putObject(bucketName,fileName,in);
            //OSSClient关闭
            ossClient.shutdown();

            //返回上传到阿里云oss地址
            //https://guli-edudemo0624.oss-cn-beijing.aliyuncs.com/01.jpg
            String url="https://"+bucketName+"."+endPoint+"/"+fileName;
            return url;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }




    }
}
