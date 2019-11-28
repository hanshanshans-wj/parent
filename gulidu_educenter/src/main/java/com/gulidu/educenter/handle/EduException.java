package com.gulidu.educenter.handle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "自定义异常类")
public class EduException extends RuntimeException{
    @ApiModelProperty("异常代码")
    private Integer code;
    @ApiModelProperty("异常信息")
    private String message;
}
