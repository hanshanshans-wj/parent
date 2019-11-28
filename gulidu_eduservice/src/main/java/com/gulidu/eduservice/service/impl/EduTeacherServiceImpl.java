package com.gulidu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.educommom.ResultCode;
import com.gulidu.eduservice.entity.EduTeacher;
import com.gulidu.eduservice.entity.QueryTeacherVo;
import com.gulidu.eduservice.handle.EduException;
import com.gulidu.eduservice.mapper.EduTeacherMapper;
import com.gulidu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-11-13
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    public void getPageListTeacher(Page<EduTeacher> teacher, QueryTeacherVo queryTeacherVo) {
        //从封装的queryTeacherVo中取出各种属性值进行条件查询，判断条件是否为空
        /*try {
            int x=8/0;
        } catch (Exception e) {
           throw new EduException(ResultCode.ERROR,"缓存查询失败");
        }*/
        String name = queryTeacherVo.getName();
        Integer level = queryTeacherVo.getLevel();
        String begin = queryTeacherVo.getBegin();
        String end = queryTeacherVo.getEnd();
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //判断是否为空
        if (!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }
        if (!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }
        if (!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }
        if (!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }
        //使用mapper方法来查询
        baseMapper.selectPage(teacher,wrapper);
    }
}
