package com.gmsj.feign.impl;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.feign.AuthClient;
import org.springframework.stereotype.Component;

/**
 * @author baojieren
 * @date 2020/4/22 15:03
 */
@Component
public class AuthClientImpl implements AuthClient {

    @Override
    public BaseOutDTO findUserInfoByUserId(Integer userId) {
        return new BaseOutDTO().fail("feign调用失败");
    }
}
