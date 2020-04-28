package com.gmsj.service;

import com.gmsj.common.dto.BaseOutDTO;

/**
 * 点赞
 *
 * @author baojieren
 * @date 2020/4/22 18:42
 */
public interface CollectService {

    /**
     * 收藏
     */
    BaseOutDTO doCollect(int userId, int articleId, int incr);
}
