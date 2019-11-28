package com.gulidu.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guli.educommom.R;
import com.gulidu.eduservice.entity.EduTeacher;
import com.gulidu.eduservice.entity.QueryTeacherVo;
import com.gulidu.eduservice.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.misc.Version;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-11-13
 */
@Slf4j
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;
    @PostMapping("login")
    public R Login(){
        //{"code":20000,"data":{"token":"admin"}}
        return R.ok().data("token","admin");
    }
    @GetMapping("info")
    public R info(){
        //{"code":20000,"data":{"roles":["admin"],"name":"admin","avatar":
        // "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif"}}
        return R.ok().data("roles","[admin]").data("name","admin").data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");

    }
    @GetMapping//列出所有讲师
    @ApiOperation(value = "列出所有讲师列表")
    public R ListTeacher(){
        List<EduTeacher> list = eduTeacherService.list(null);
        list.forEach(System.out::println);

        return R.ok().data("items",list);
    }
    @DeleteMapping("{id}")//删除某位讲师
    @ApiOperation(value = "删除某位讲师")
    public R deleteTeacherId(@ApiParam(name = "id",value = "讲师id")@PathVariable String id){
        boolean b = eduTeacherService.removeById(id);
        log.debug(b+"");
        return R.ok();

    }
    @ApiOperation(value = "分页查询")
    @GetMapping("getPageTeacher/{page}/{limit}")
    public R pageList(@ApiParam(value = "当前页")@PathVariable Long page,@ApiParam(value = "每页几条记录") @PathVariable Long limit){
        //建立page对象，分页信息
        Page<EduTeacher> page1 = new Page<>(page,limit);
        //封装信息进行分页
        eduTeacherService.page(page1,null);
        //根据page1获取各种信息
        List<EduTeacher> records = page1.getRecords();//获取详情数
        long total = page1.getTotal();//获取总记录数
        return R.ok().data("rows",records).data("total",total);

    }
    //多条件组合查询分页
    @ApiOperation(value = "多条件分页查询")
    @PostMapping("getMultiPageTeacher/{page}/{limit}")
    public R pageMultiList(@ApiParam(value = "当前页")@PathVariable Long page, @ApiParam(value = "每页几条记录") @PathVariable Long limit,
                           @RequestBody(required = false) QueryTeacherVo queryTeacherVo){
        Page<EduTeacher> Teacher = new Page<>(page,limit);
        //建造一个方法用于多条件查询
        eduTeacherService.getPageListTeacher(Teacher,queryTeacherVo);
        List<EduTeacher> records = Teacher.getRecords();//详情
        long total = Teacher.getTotal();
        return R.ok().data("total",total).data("rows",records);


    }
    //讲师添加功能
    @ApiOperation(value = "讲师添加功能")
    @PostMapping("addTeacher")
    public R addTeacher(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return R.ok();
        }else {
            return  R.error();
        }

    }
    //通过id查询讲师
    @ApiOperation(value = "通过id查询")
    @GetMapping("/getTeacherId/{id}")
    public R selectByIdTeacher(@PathVariable String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        if (!StringUtils.isEmpty(eduTeacher)){
            return R.ok().data("eduTeacher",eduTeacher);
        }else {
            return R.error();
        }
    }
    //进行修改
    @ApiOperation(value = "进行修改")
    @PostMapping("updateByIdTeacher")
    public R updateByIdTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        if (b){
            return R.ok();
        }else {
            return R.error();
        }
    }

}

