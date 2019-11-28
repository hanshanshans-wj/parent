package com.guli.educommom;

public interface ResultCode {
    int OK=20000;//正确
    int ERROR=20001;//错误
    int LOGIN_ERROR=20002;//用户名密码错误
    int ACCESS_ERROR=20003;//权限不足
    int REMOTE_ERROR=20004;//远程调用失败
    int REPEAT_ERROR=20005;//重复操作
    int SQL_ERROR=20006;//sql错误
}
