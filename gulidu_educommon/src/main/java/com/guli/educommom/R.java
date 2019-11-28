package com.guli.educommom;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;
@Data
@Api(description = "描述返回信息类")
public class R {
    @ApiModelProperty(value = "返回是否成功")
    private boolean success;
    @ApiModelProperty(value = "返回代码")
    private Integer code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回map集合")
    private Map<String,Object> data=new HashMap<>();

    private R(){}//构造私有化
    public static R ok(){
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.OK);
        r.setMessage("成功");
        return r;
    }
    public static R error(){
        R r = new R();
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        r.setSuccess(false);
        return r;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;

    }
    public R message(String message){
        this.setMessage(message);
        return  this;
    }
    public R data(String key,Object value){
    this.data.put(key, value);
    return this;
    }
    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }
}
