package com.gmsj.model.po;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.Data;

/**
 * 
 * @author baojieren
 * @date 2020/4/24 14:41
 */

/**
 * 政策资讯表
 */
@Data
@Table(name = "policy")
public class PolicyPo implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 文章标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章内容
     */
    @Column(name = "content")
    private String content;

    /**
     * 关键字
     */
    @Column(name = "keyword")
    private String keyword;

    /**
     * 主题图片
     */
    @Column(name = "theme_image")
    private String themeImage;

    /**
     * 图片地址,多个用逗号隔开
     */
    @Column(name = "images")
    private String images;

    /**
     * 浏览数
     */
    @Column(name = "browse_sum")
    private Integer browseSum;

    /**
     * 评论数
     */
    @Column(name = "comment_sum")
    private Integer commentSum;

    /**
     * 点赞数
     */
    @Column(name = "praise_sum")
    private Integer praiseSum;

    /**
     * 收藏数
     */
    @Column(name = "collect_sum")
    private Integer collectSum;

    /**
     * 上传者id
     */
    @Column(name = "owner_id")
    private Integer ownerId;

    /**
     * 菜单id
     */
    @Column(name = "policy_menu_id")
    private Integer policyMenuId;

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