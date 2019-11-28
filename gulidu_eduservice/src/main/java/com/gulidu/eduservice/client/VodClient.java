package com.gulidu.eduservice.client;

import com.guli.educommom.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("GULIDU-EDUVOD")
@Component
public interface VodClient {

    //定义删除小节删除视频的方法
    //删除阿里云视频
    @DeleteMapping("/eduvod/video/deleteAliVideo/{videoId}")
    public R deleteAliVideo(@PathVariable("videoId") String videoId);

  /*  @DeleteMapping("/eduvod/video/deleteMoreVideo")
    public R deleteMoreVideo(@RequestParam List videoList);*/
  @DeleteMapping("/eduvod/video/deleteMoreVideo")
  public R deleteMoreVideo(@RequestParam(value = "videoList") List videoList);
}
