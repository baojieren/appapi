package com.gmsj.service;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.model.UserInfo;
import com.gmsj.web.form.SaveCardInfoForm;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author baojieren
 * @date 2020/4/23 10:02
 */
public interface CardFolderService {

    /**
     * 首页个人名片信息,评论收藏数等
     */
    BaseOutDTO getMyCardInfo(UserInfo userInfo);

    /**
     * 查询个人名片详情
     */
    BaseOutDTO getMyProfile(int userId);

    /**
     * 修改名片
     */
    BaseOutDTO updateProfile(SaveCardInfoForm form);

    /**
     * 保存或取消保存对方名片
     */
    BaseOutDTO getOrRemoveCard(int myId, int bizUserId, int isGet);

    /**
     * 点赞或取消点赞对方名片
     */
    BaseOutDTO praiseOrRemoveCard(int myId, int bizUserId, int isPraise);


}
