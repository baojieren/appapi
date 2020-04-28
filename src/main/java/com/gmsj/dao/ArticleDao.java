package com.gmsj.dao;

import com.gmsj.model.po.ArticlePo;
import tk.mybatis.mapper.common.Mapper;import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/23 11:08
 */
public interface ArticleDao extends Mapper<ArticlePo> {
    /**
     * 根据菜单id查询前10条文章
     */
    List<ArticlePo> selectFirstLimitByMenuId(int menuId);

    /**
     * 根据菜单id分页查询文章列表
     */
    List<ArticlePo> selectPageByMenuId(int menuId);

    /**
     * 统计用户的文章浏览总数
     */
    Integer countBrowseSumByUserId(int userId);

    List<ArticlePo> selectFuzzyArticle(String keyword);

    List<ArticlePo> selectByOwnerId(int userId);

    ArticlePo selectById(int articleId);
}