package com.gulidu.edusta.staClient;

import com.guli.educommom.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("gulidu-educenter")
@Component
public interface UcenterClient {
    @GetMapping("/educenter/ucenter-member/countRegisterNum/{day}")
    public R countRegisterNum(@PathVariable(value = "day") String day);

}
