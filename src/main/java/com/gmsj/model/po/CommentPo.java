package com.gmsj.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 
 * @author baojieren
 * @date 2020/4/22 16:28
 */

/**
 * 评论表
 */
@Data
@Table(name = "`comment`")
public class CommentPo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 文章id
     */
    @Column(name = "article_id")
    private Integer articleId;

    /**
     * 评论人id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名称
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 用户头像图片地址
     */
    @Column(name = "user_logo")
    private String userLogo;

    /**
     * 1:评论文章 2:回复评论
     */
    @Column(name = "`type`")
    private Integer type;

    /**
     * 评论id, type为回复评论时不能为空
     */
    @Column(name = "comment_id")
    private Integer commentId;

    /**
     * 评论内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 如果是回复的话,记录评论文章的评论id
     */
    @Column(name = "first_comment_id")
    private Integer firstCommentId;

    /**
     * 0:无效 1:有效
     */
    @Column(name = "`valid`")
    private Integer valid;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Column(name = "update_time")
    private LocalDateTime updateTime;

    private static final long serialVersionUID = 1L;
}