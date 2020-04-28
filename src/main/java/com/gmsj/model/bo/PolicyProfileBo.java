package com.gmsj.model.bo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/24 14:52
 */
@Data
public class PolicyProfileBo implements Serializable {

    public Integer id;

    /**
     * 文章标题
     */
    public String title;

    /**
     * 文章内容
     */
    public String content;

    /**
     * 主题图片
     */
    public String themeImage;

    /**
     * 图片地址,多个用逗号隔开
     */
    public List<String> images;

    /**
     * 浏览数
     */
    public Integer browseSum;

    /**
     * 评论数
     */
    public Integer commentSum;

    /**
     * 点赞数
     */
    public Integer praiseSum;

    /**
     * 分类名称
     */
    public String menuName;

    public String createTime;
}
