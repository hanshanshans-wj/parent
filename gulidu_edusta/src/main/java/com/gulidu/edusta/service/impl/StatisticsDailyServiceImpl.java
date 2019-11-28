package com.gulidu.edusta.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guli.educommom.R;
import com.gulidu.edusta.entity.StatisticsDaily;
import com.gulidu.edusta.mapper.StatisticsDailyMapper;
import com.gulidu.edusta.service.StatisticsDailyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gulidu.edusta.staClient.UcenterClient;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-11-27
 */
@Service
public class StatisticsDailyServiceImpl extends ServiceImpl<StatisticsDailyMapper, StatisticsDaily> implements StatisticsDailyService {
    @Autowired
    private UcenterClient ucenterClient;
    //建立统计分析数据，通过远程调用ucenter
    @Override
    public void createStatisticsByDay(String day) {
              //得到当天统计的访问次数
        R r = ucenterClient.countRegisterNum(day);
        //得到访问次数
        Integer registCount = (Integer) r.getData().get("count");

        // 判断是否有重复
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.eq("register_num",registCount);
        baseMapper.delete(wrapper);


        //建立统计对象
        StatisticsDaily statisticsDaily = new StatisticsDaily();
        statisticsDaily.setRegisterNum(registCount);
        statisticsDaily.setVideoViewNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setLoginNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setCourseNum(RandomUtils.nextInt(100,200));
        statisticsDaily.setDateCalculated(day);
        baseMapper.insert(statisticsDaily);


    }

    @Override
    public Map<String, Object> getShowData(String type, String begin, String end) {
        //根据条件查询需要的数据
        QueryWrapper<StatisticsDaily> wrapper = new QueryWrapper<>();
        wrapper.between("date_calculated",begin,end);

        //根据type就是字段名称查询
        wrapper.select("date_calculated",type);
        wrapper.orderByDesc("date_calculated");
        List<StatisticsDaily> dailyList = baseMapper.selectList(wrapper);
        List<String> dateList=new ArrayList<>();
        List<Integer> dataList=new ArrayList<>();
        for (int i = 0; i <dailyList.size() ; i++) {
            StatisticsDaily statisticsDaily = dailyList.get(i);
            String dateCalculated = statisticsDaily.getDateCalculated();
            dateList.add(dateCalculated);
            switch (type){
                case "register_num":
                    Integer registerNum = statisticsDaily.getRegisterNum();
                    dataList.add(registerNum);
                    break;
                case "login_num":
                    Integer loginNum = statisticsDaily.getLoginNum();
                    dataList.add(loginNum);
                    break;
                case "video_view_num":
                    Integer videoViewNum = statisticsDaily.getVideoViewNum();
                    dataList.add(videoViewNum);
                    break;
                case "course_num":
                    Integer courseNum = statisticsDaily.getCourseNum();
                    dataList.add(courseNum);
                    break;

            }

        }


        //创建集合
            Map<String,Object> map=new HashMap<>();
        map.put("dataList",dataList);
        map.put("dateList",dateList);



        return map;
    }
}
