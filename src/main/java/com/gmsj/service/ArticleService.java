package com.gmsj.service;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.model.UserInfo;
import com.gmsj.web.form.SaveArticleForm;
import com.gmsj.web.form.GetArticlePageForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文章相关业务层
 *
 * @author baojieren
 * @date 2020/4/22 10:34
 */
public interface ArticleService {

    /**
     * 查询需求广场所有菜单
     */
    BaseOutDTO findLv1DemandMenu();

    /**
     * 根据一级目录id查询二级目录列表并默认查询第一个二级菜单前10条记录
     */
    BaseOutDTO findLv2Menu(int lv1Id);

    /**
     * 发布按钮获取二级菜单列表
     */
    BaseOutDTO findLv2MenuAll(int lv1Id);

    /**
     * 发布文章
     */
    BaseOutDTO createArticle(SaveArticleForm form);

    /**
     * 根据菜单id查询文章列表
     */
    BaseOutPageDTO findArticleListByMenuId(GetArticlePageForm form);

    /**
     * 获取文章详情
     */
    BaseOutDTO findArticleInfo(int userId, int articleId);

    /**
     * 搜索
     */
    BaseOutPageDTO searchArticle(String keyword);

    /**
     * 文章详情
     */
    BaseOutDTO selectArticleById(int articleId);
}
