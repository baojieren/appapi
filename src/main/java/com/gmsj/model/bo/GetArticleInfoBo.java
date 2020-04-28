package com.gmsj.model.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/23 16:16
 */
@Data
public class GetArticleInfoBo implements Serializable {

    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    /**
     * 关键字
     */
    private String keyword;

    /**
     * 图片地址,多个用逗号隔开
     */
    private List<String> images;

    /**
     * 浏览数
     */
    private Integer browseSum;

    /**
     * 评论数
     */
    private Integer commentSum;

    /**
     * 收藏数
     */
    private Integer collectSum;

    /**
     * 上传者id
     */
    private Integer ownerId;

    /**
     * 0:未收藏 1:已收藏
     */
    private Integer collected;

    private String createTime;
}
