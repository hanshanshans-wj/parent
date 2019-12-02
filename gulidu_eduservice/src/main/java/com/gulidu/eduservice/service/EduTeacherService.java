package com.gulidu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gulidu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gulidu.eduservice.entity.QueryTeacherVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-11-13
 */

public interface EduTeacherService extends IService<EduTeacher> {

    void getPageListTeacher(Page<EduTeacher> teacher, QueryTeacherVo queryTeacherVo);

    Map<String,Object> getPageFrontListPage(Page<EduTeacher> eduTeacherPage);

    Map<String,Object> getTeacherInfoById(String tid);
}
