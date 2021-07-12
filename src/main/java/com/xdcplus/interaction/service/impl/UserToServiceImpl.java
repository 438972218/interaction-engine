package com.xdcplus.interaction.service.impl;

import com.xdcplus.interaction.common.pojo.entity.UserTo;
import com.xdcplus.interaction.common.pojo.vo.UserToVO;
import com.xdcplus.interaction.mapper.UserToMapper;
import com.xdcplus.interaction.service.UserToService;
import com.xdcplus.mp.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *  用户去向信息服务实现类
 *
 * @author Rong.Jia
 * @date  2021-07-01
 */
@Slf4j
@Service
public class UserToServiceImpl extends BaseServiceImpl<UserTo, UserToVO, UserTo, UserToMapper> implements UserToService {

    @Autowired
    private UserToMapper userToMapper;

    @Override
    public List<UserToVO> findUserTo() {

        return this.objectConversion(this.list());
    }
}
