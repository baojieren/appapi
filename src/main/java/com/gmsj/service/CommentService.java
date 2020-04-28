package com.gmsj.service;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.model.UserInfo;
import com.gmsj.web.form.CreateCommentForm;
import com.gmsj.web.form.GetCommentListForm;

/**
 * @author baojieren
 * @date 2020/4/22 14:26
 */
public interface CommentService {

    /**
     * 保存评论
     */
    BaseOutDTO saveComment(CreateCommentForm form, UserInfo userInfo);

    /**
     * 查询文章评论
     */
    BaseOutPageDTO findCommentPage(GetCommentListForm form);
}
