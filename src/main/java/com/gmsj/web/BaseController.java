package com.gmsj.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gmsj.base.YmlConfig;
import com.gmsj.common.constant.HttpHeaderConstant;
import com.gmsj.common.model.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * @author baojieren
 * @date 2020/4/17 17:59
 */
@Controller
public abstract class BaseController {
    @Resource
    YmlConfig ymlConfig;
    @Resource
    ObjectMapper objectMapper;

    /**
     * 获取用户基本信息
     */
    UserInfo getUserInfo(HttpServletRequest request) {
        if (!"dev".equals(ymlConfig.getDebug())) {
            String userInfoHeader = request.getHeader(HttpHeaderConstant.HEADER_USER_INFO);
            if (StringUtils.isEmpty(userInfoHeader)) {
                return null;
            }
            try {
                return objectMapper.readValue(URLDecoder.decode(userInfoHeader, "UTF-8"), UserInfo.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } else {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(1314);
            userInfo.setUserName("baojieren");
            userInfo.setPhone("18585131312");
            userInfo.setLogo("https://img.gmsj.com/logo");
            userInfo.setOpenId("openidopenidopenidopenid");
            userInfo.setCompanyId(1);
            userInfo.setJob("挖煤工");
            userInfo.setAddr("阿富汗");
            userInfo.setEmail("2013142083@qq.com");
            return userInfo;
        }
    }
}
