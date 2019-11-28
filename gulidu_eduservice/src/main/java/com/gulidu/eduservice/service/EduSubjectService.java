package com.gulidu.eduservice.service;

import com.gulidu.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gulidu.eduservice.entity.SubjectOneVo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-11-19
 */
public interface EduSubjectService extends IService<EduSubject> {

    List<String> importData(MultipartFile file);

    List<SubjectOneVo> getAllSubject();

    boolean deleteById(String id);

    void addSubjectOne(EduSubject eduSubject);

    void addSubjectTwo(EduSubject eduSubject);
}
