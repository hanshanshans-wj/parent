package com.gulidu.educenter.mapper;

import com.gulidu.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author testjava
 * @since 2019-11-27
 */
public interface UcenterMemberMapper extends BaseMapper<UcenterMember> {
//查询某一天注册人数
    public Integer countRegisterNum(String day);

}
