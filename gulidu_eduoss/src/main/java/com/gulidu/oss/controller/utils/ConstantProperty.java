package com.gulidu.oss.controller.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConstantProperty implements InitializingBean{
    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;
    @Value("${aliyun.oss.file.accesskeyid}")
    private String AccessKeyId;
    @Value("${aliyun.oss.file.accesskeysecrett}")
    private  String AccessKeySecret;
    @Value("${aliyun.oss.file.bucketName}")
    private String bucketName;


    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;
    @Override
    public void afterPropertiesSet() throws Exception {
    END_POINT=endPoint;
    ACCESS_KEY_ID=AccessKeyId;
    ACCESS_KEY_SECRET=AccessKeySecret;
    BUCKET_NAME=bucketName;
    }
}
