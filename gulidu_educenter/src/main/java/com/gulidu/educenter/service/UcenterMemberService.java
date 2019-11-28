package com.gulidu.educenter.service;

import com.gulidu.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author testjava
 * @since 2019-11-27
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    Integer countRegisterNum(String day);
}
