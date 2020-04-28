package com.gmsj.web.form;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/22 12:20
 */
@Data
public class SaveArticleForm implements Serializable {
    /**
     * 上传者id
     */
    private Integer ownerId;

    /**
     * 菜单id
     */
    private Integer demandMenuId;

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
     * 主题图片
     */
    private String themeImage;

    /**
     * 图片地址
     */
    private List<String> images;
}
