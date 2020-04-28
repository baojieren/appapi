package com.gmsj.model.bo;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/22 17:25
 */
@Data
public class CommentListBo implements Serializable {

    public Integer id;

    /**
     * 文章id
     */
    public Integer articleId;

    /**
     * 评论人id
     */
    public Integer commentUserId;

    /**
     * 用户名称
     */
    public String commentUserName;

    /**
     * 用户头像图片地址
     */
    public String commentUserLogo;

    /**
     * 评论内容
     */
    public String content;

    /**
     * 本条评论下的评论
     */
    List<Reply> replyList;

    @Data
    public static class Reply {
        /**
         * 回复人id
         */
        public Integer replyUserId;

        /**
         * 用户名称
         */
        public String replyUserName;

        /**
         * 用户头像图片地址
         */
        public String replyUserLogo;

        /**
         * 评论内容
         */
        public String replyContent;

        /**
         * 被回复人id
         */
        public Integer commentUserId;

        /**
         * 被回复人id
         */
        public String commentUserName;

        /**
         * 被回复人写的评论
         */
        public String commentContent;

    }
}
