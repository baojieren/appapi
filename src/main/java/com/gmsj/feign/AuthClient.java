package com.gmsj.feign;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.feign.impl.AuthClientImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author baojieren
 * @date 2020/4/22 14:57
 */
@FeignClient(value = "auth", fallback = AuthClientImpl.class)
public interface AuthClient {

    @RequestMapping(path = "/auth/userInfoByUserId", method = RequestMethod.GET)
    BaseOutDTO findUserInfoByUserId(@Param("userId") Integer userId);
}
