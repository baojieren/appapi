package com.gmsj.service;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.model.UserInfo;

/**
 * @ProjectName: 贵煤数据
 * @Author: zlh
 * @Date: 2020/4/22 10:11
 * @Description:
 */
public interface UserService {


    /**
     * @Description: 查找用户信息
     * @Param [userName] 用户名
     * @Author: zlh
     * @Date: 2020/4/23 17:57
     * @return
     */
    BaseOutDTO searchUser(UserInfo userInfo, Integer menuId, String keyword);

    BaseOutDTO findUserFast(UserInfo userInfo);

    BaseOutDTO findArticleByUserId(UserInfo userInfo);

    BaseOutDTO findUserCommentByUserId(UserInfo userInfo);

    BaseOutDTO findArticleCollect(UserInfo userInfo);

    BaseOutDTO findUserCardFolder(UserInfo userInfo);
}
