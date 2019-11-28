package com.gulidu.edusta.controller;


import com.guli.educommom.R;
import com.gulidu.edusta.service.StatisticsDailyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2019-11-27
 */
@RestController
@RequestMapping("/edusta/statistics-daily")
@CrossOrigin
public class StatisticsDailyController {
    @Autowired
    private StatisticsDailyService statisticsDailyService;
    @ApiOperation("增加统计数据记录")
    @PostMapping("createStatisticsByDay/{day}")
    public R createStatisticsByDay(@PathVariable(value = "day") String day){
        statisticsDailyService.createStatisticsByDay(day);
        return R.ok();
    }

    //返回图标显示的数据
    @ApiOperation("返回图表显示数据")
    @GetMapping("getShowData/{type}/{begin}/{end}")
    public R getShowData(@PathVariable String type,
                         @PathVariable String begin,
                         @PathVariable String end){
        //返回什么类型？
        // 第一部分json数组，日期：['Mon', 'Tue']
        //第二部分json数组，数据：[820, 932]
        Map<String,Object> map=statisticsDailyService.getShowData(type,begin,end);
        return R.ok().data(map);
    }
}

