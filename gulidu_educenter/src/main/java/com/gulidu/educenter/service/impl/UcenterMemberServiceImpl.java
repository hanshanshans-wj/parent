package com.gulidu.educenter.service.impl;

import com.gulidu.educenter.entity.UcenterMember;
import com.gulidu.educenter.mapper.UcenterMemberMapper;
import com.gulidu.educenter.service.UcenterMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author testjava
 * @since 2019-11-27
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Override
    public Integer countRegisterNum(String day) {
        Integer count = baseMapper.countRegisterNum(day);
        return count;
    }
}
