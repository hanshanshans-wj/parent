package com.gulidu.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gulidu.eduservice.entity.EduSubject;
import com.gulidu.eduservice.entity.SubjectOneVo;
import com.gulidu.eduservice.entity.SubjectTwoVo;
import com.gulidu.eduservice.handle.EduException;
import com.gulidu.eduservice.mapper.EduSubjectMapper;
import com.gulidu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-11-19
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public List<String> importData(MultipartFile file) {
        //获得文件输入流

        try {
            InputStream inputStream = file.getInputStream();
            //创建workbook
            Workbook workbook = WorkbookFactory.create(inputStream);
            //获取sheet
            Sheet sheetAt = workbook.getSheetAt(0);
            //建立集合存储信息是否有空行

            //获取row

            //进行遍历获，第一行数据不需要，获取第二行
            List<String> msg = new ArrayList<>();
            //获取最后一行索引值
            int lastRowNum = sheetAt.getLastRowNum();

            //从第二行获取索引内容，遍历
            for (int i=1;i<=lastRowNum;i++){
                Row row = sheetAt.getRow(i);
                //得到每一行
                Cell cellOne = row.getCell(0);
                if (cellOne==null){
                    String info="第"+(i+1)+"行有空";
                    msg.add(info);
                    continue;

                }
                //获取第一cell
                String stringCellValue1 = cellOne.getStringCellValue();

                //判断第一列是否为空

                //获取第一cell内容
                if (StringUtils.isEmpty(stringCellValue1)){
                    String info="第"+(i+1)+"行有空";
                    msg.add(info);
                    continue;
                }


                //添加一级分类

                //判断有无一级分类
                EduSubject eduSubject1 = this.existOneSubject(stringCellValue1);
//                EduSubject eduSubject1 =null;
                if (eduSubject1==null){
                    eduSubject1 = new EduSubject();
                    eduSubject1.setTitle(stringCellValue1);
                    eduSubject1.setParentId("0");
                    baseMapper.insert(eduSubject1);
                }
                //获取第一级的id，为了第二级分类使用
                String pid = eduSubject1.getId();
                //第二级cell
                Cell cellTwo = row.getCell(1);
                //判断第二列是否为空
                if (cellTwo==null){
                    String info="第"+(i+1)+"行为空";
                    msg.add(info);
                    continue;
                }


                //第二级列的内容
                String stringCellValue2 = cellTwo.getStringCellValue();
                if (StringUtils.isEmpty(stringCellValue2)){
                    String info="第"+(i+1)+"行为空";
                    msg.add(info);
                    continue;
                }

                //调用方法看二级类是否重复
                EduSubject eduSubject2 = this.existTwoSubject(stringCellValue2, pid);

                //判断返回对象是否为空，如果为空，进行添加
                if (eduSubject2==null){
                    eduSubject2=new EduSubject();
                    eduSubject2.setTitle(stringCellValue2);
                    eduSubject2.setParentId(pid);
                    baseMapper.insert(eduSubject2);
                }

            }
            return msg;

        } catch (Exception e) {
            e.printStackTrace();
            throw new EduException(20001,"导入失败");
        }







    }

    @Override
    //返回所有一级和二级分类，按照规定格式进行返回
    public List<SubjectOneVo> getAllSubject() {
        //查询一级分类
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id","0");
        List<EduSubject> eduSubjects1 = baseMapper.selectList(wrapper);



        //查询二级分类
        QueryWrapper<EduSubject> wrapper1 = new QueryWrapper<>();
        wrapper1.ne("parent_id","0");
        List<EduSubject> eduSubjects2 = baseMapper.selectList(wrapper1);

        //进行封装
        //遍历所有一级分类集合
        ArrayList<SubjectOneVo> finalList = new ArrayList<>();
        for (int i = 0; i <eduSubjects1.size() ; i++) {
            EduSubject eduSubjectOne = eduSubjects1.get(i);
            //将eduSubject转成SubjectOneVo
            SubjectOneVo subjectOneVo = new SubjectOneVo();

            BeanUtils.copyProperties(eduSubjectOne,subjectOneVo);
            //将一级分类加入最终封装的集合中
            finalList.add(subjectOneVo);

            //创建集合用于封装二级分类
            ArrayList<SubjectTwoVo> subjectTwoVos = new ArrayList<>();

            //遍历二级集合
            for (int j = 0; j <eduSubjects2.size() ; j++) {
                //判断每一个二级分类parentid和一级id是否一致
                EduSubject eduSubjectTwo = eduSubjects2.get(j);
                if (eduSubjectTwo.getParentId().equals(subjectOneVo.getId())) {
                    SubjectTwoVo subjectTwoVo = new SubjectTwoVo();
                    BeanUtils.copyProperties(eduSubjectTwo,subjectTwoVo);
                    subjectTwoVos.add(subjectTwoVo);
                }
                //将twoSubject转成twovo

                //subjectTwoVo放到集合
            }
            //循环后将整个集合作为children放到一级分类中
            subjectOneVo.setChildren(subjectTwoVos);

        }

        return finalList;


    }

    @Override
    public boolean deleteById(String id) {
        //查询分类是否有子分类
        QueryWrapper<EduSubject> eduSubjectQueryWrapper = new QueryWrapper<>();
        eduSubjectQueryWrapper.eq("parent_id",id);
        Integer count = baseMapper.selectCount(eduSubjectQueryWrapper);
        if (count==0){
        //没有子分类,可以直接删除
            int i = baseMapper.deleteById(id);
            return i>0;
        }else {
            //有子分类不可以删除
           throw new EduException(20001,"有子类无法删除");
        }


    }

    @Override
    public void addSubjectOne(EduSubject eduSubject) {
        //添加一级节点
        EduSubject eduSubject1 = this.existOneSubject(eduSubject.getTitle());
        if (eduSubject1==null){
            eduSubject.setParentId("0");
            int insert = baseMapper.insert(eduSubject);
            if (insert==0){
                throw new EduException(20001,"添加一级节点失败");
            }
        }
    }

    @Override
    public void addSubjectTwo(EduSubject eduSubject) {
        //增加二级节点
        EduSubject eduSubject1 = this.existTwoSubject(eduSubject.getTitle(), eduSubject.getParentId());
        if (eduSubject1==null){

            int insert = baseMapper.insert(eduSubject);
            if (insert==0){
                throw new EduException(20001,"添加二级节点失败");
            }
        }
    }

    //判断一级分类是否重复
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
       return eduSubject;
    }
    //判断二级分类是否有重复
    private EduSubject existTwoSubject(String name,String parentId){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",parentId);
        EduSubject eduSubject = baseMapper.selectOne(wrapper);
        return eduSubject;
    }


}
