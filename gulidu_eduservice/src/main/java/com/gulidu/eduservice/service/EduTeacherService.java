package com.gulidu.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gulidu.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gulidu.eduservice.entity.QueryTeacherVo;
import org.springframework.stereotype.Service;

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
}
