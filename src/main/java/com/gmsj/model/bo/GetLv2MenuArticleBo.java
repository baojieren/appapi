package com.gmsj.model.bo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章表
 *
 * @author baojieren
 * @date 2020/4/22 10:10
 */
@Data
public class GetLv2MenuArticleBo implements Serializable {
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容(超过300个字使用...)
     */
    private String content;

    /**
     * 主题图片
     */
    private String themeImage;

    /**
     * 图片地址
     */
    private List<String> images;

    /**
     * 评论数
     */
    private Integer commentSum;

    /**
     * 点赞数
     */
    private Integer praiseSum;

    /**
     * 收藏数
     */
    private Integer collectSum;

    /**
     * 上传者id
     */
    private Integer ownerId;

    private String createTime;
}