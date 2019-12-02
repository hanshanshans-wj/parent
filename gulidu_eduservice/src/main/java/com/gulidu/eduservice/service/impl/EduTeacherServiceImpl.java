package com.gulidu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.educommom.ResultCode;
import com.gulidu.eduservice.entity.EduCourse;
import com.gulidu.eduservice.entity.EduTeacher;
import com.gulidu.eduservice.entity.QueryTeacherVo;
import com.gulidu.eduservice.handle.EduException;
import com.gulidu.eduservice.mapper.EduTeacherMapper;
import com.gulidu.eduservice.service.EduCourseService;
import com.gulidu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private EduCourseService eduCourseService;
    @Override
    public void  getPageListTeacher(Page<EduTeacher> teacher, QueryTeacherVo queryTeacherVo) {
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

    @Override
    public Map<String, Object> getPageFrontListPage(Page<EduTeacher> eduTeacherPage) {
        baseMapper.selectPage(eduTeacherPage,null);
        //新建返回集合
        Map<String,Object> map=new HashMap<>();
        long total = eduTeacherPage.getTotal();//获取总记录数
        List<EduTeacher> records = eduTeacherPage.getRecords();//获取集合
        long current = eduTeacherPage.getCurrent();//获取当前页
        long size = eduTeacherPage.getSize();//获取每页记录数
        long pages = eduTeacherPage.getPages();//总页数
        boolean hasPrevious = eduTeacherPage.hasPrevious();//是否有前一页
        boolean hasNext = eduTeacherPage.hasNext();//是否有下一页

        //把分页数据放到map集合里
        map.put("total",total);
        map.put("records",records);
        map.put("current",current);
        map.put("size",size);
        map.put("pages",pages);
        map.put("hasPrevious",hasPrevious);
        map.put("hasNext",hasNext);


        return map;
    }

    @Override
    public Map<String, Object> getTeacherInfoById(String tid) {
        //查询讲师信息
        EduTeacher eduTeacher = baseMapper.selectById(tid);
        //查询该讲师的课程信息
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        eduCourseQueryWrapper.eq("teacher_id",tid);
        List<EduCourse> list = eduCourseService.list(eduCourseQueryWrapper);
        Map<String,Object> map=new HashMap<>();
        map.put("list",list);
        map.put("eduTeacher",eduTeacher);

        return map;
    }
}
