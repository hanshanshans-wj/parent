package com.gulidu.educenter.controller;


import com.guli.educommom.R;
import com.gulidu.educenter.service.UcenterMemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/educenter/ucenter-member")
@Api(description = "统计每日注册数")
@CrossOrigin
public class UcenterMemberController {
    @Autowired
    private UcenterMemberService ucenterMemberService;
    @GetMapping("countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable(value = "day") String day){
        Integer count=ucenterMemberService.countRegisterNum(day);
        return R.ok().data("count",count);
    }
}

