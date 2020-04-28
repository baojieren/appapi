package com.gmsj.web.form;

import lombok.Data;

import java.io.Serializable;

/**
 * @author baojieren
 * @date 2020/4/22 14:23
 */
@Data
public class CreateCommentForm implements Serializable {

    /**
     * 文章id
     */
    public Integer articleId;

    /**
     * 评论内容
     */
    public String content;

    /**
     * 1:评论文章 2:回复评论
     */
    private Integer type;

    /**
     * 评论id, type为2时不能为空
     */
    private Integer commentId;

    /**
     * 如果是回复的话,记录评论文章的评论id
     */
    private Integer firstCommentId;
}
