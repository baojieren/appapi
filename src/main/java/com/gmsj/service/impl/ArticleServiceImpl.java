package com.gmsj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.model.UserInfo;
import com.gmsj.common.util.TimestampUtil;
import com.gmsj.dao.ArticleDao;
import com.gmsj.dao.CollectArticleDao;
import com.gmsj.dao.DemandMenuDao;
import com.gmsj.model.bo.*;
import com.gmsj.model.po.ArticlePo;
import com.gmsj.model.po.CollectArticlePo;
import com.gmsj.model.po.DemandMenuPo;
import com.gmsj.service.ArticleService;
import com.gmsj.web.form.GetArticlePageForm;
import com.gmsj.web.form.SaveArticleForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author baojieren
 * @date 2020/4/22 10:34
 */
@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {

    @Resource
    DemandMenuDao demandMenuDao;
    @Resource
    ArticleDao articleDao;
    @Resource
    CollectArticleDao collectArticleDao;

    @Override
    public BaseOutDTO findLv1DemandMenu() {
        List<DemandMenuPo> lv1DemandMenu = demandMenuDao.selectLv1DemandMenu();
        List<GetLv1MenuListBo> boList = new ArrayList<>();
        GetLv1MenuListBo bo;
        for (DemandMenuPo menu : lv1DemandMenu) {
            bo = new GetLv1MenuListBo();
            boList.add(bo);
            BeanUtils.copyProperties(menu, bo);
        }
        return new BaseOutDTO().setData(boList);
    }

    @Override
    public BaseOutDTO findLv2Menu(int lv1Id) {
        BaseOutDTO outDTO = new BaseOutDTO();
        GetLv2MenuBo bo = new GetLv2MenuBo();

        // 二级菜单列表
        List<DemandMenuPo> lv2MenuByLv1 = demandMenuDao.findLv2MenuByLv1(lv1Id);
        if (CollectionUtils.isEmpty(lv2MenuByLv1)) {
            return outDTO;
        }

        List<GetDemandMenuBo> getDemandMenuBoList = lv2MenuByLv1.stream().map(r -> {
            GetDemandMenuBo demandMenuBo = new GetDemandMenuBo();
            BeanUtils.copyProperties(r, demandMenuBo);
            return demandMenuBo;
        }).collect(Collectors.toList());

        // 查询第一个二级菜单项的前10条文章列表
        List<ArticlePo> articlePos = articleDao.selectFirstLimitByMenuId(lv2MenuByLv1.get(0).getId());

        List<GetLv2MenuArticleBo> articleBoList = new ArrayList<>();
        GetLv2MenuArticleBo articleBo;
        for (ArticlePo articlePo : articlePos) {
            articleBo = new GetLv2MenuArticleBo();
            articleBoList.add(articleBo);
            BeanUtils.copyProperties(articlePo, articleBo);
            if (articlePo.getContent().length() > 300) {
                articleBo.setContent(articlePo.getContent().substring(0, 300) + "...");
            }
            if (!StringUtils.isEmpty(articlePo.getImages())) {
                String[] imgArr = articlePo.getImages().split(",");
                articleBo.setImages(Arrays.asList(imgArr));
            }
            articleBo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(articlePo.getCreateTime()));
        }

        bo.setLv2MenuList(getDemandMenuBoList);
        bo.setArticleBoList(articleBoList);
        return outDTO.setData(bo);
    }

    @Override
    public BaseOutDTO findLv2MenuAll(int lv1Id) {
        List<DemandMenuPo> lv2MenuByLv1 = demandMenuDao.findLv2MenuByLv1(lv1Id);

        List<GetLv1MenuListBo> boMenuList = lv2MenuByLv1.stream().map(m -> {
            GetLv1MenuListBo bo = new GetLv1MenuListBo();
            BeanUtils.copyProperties(m, bo);
            return bo;
        }).collect(Collectors.toList());

        return new BaseOutDTO().setData(boMenuList);
    }

    @Override
    public BaseOutDTO createArticle(SaveArticleForm form) {
        ArticlePo articlePo = new ArticlePo();
        BeanUtils.copyProperties(form, articlePo);
        if (!ObjectUtils.isEmpty(form.getImages())) {
            articlePo.setImages(form.getImages().toString().replace(" ", "").replace("[", "").replace("]", ""));
        }
        articleDao.insertSelective(articlePo);
        return new BaseOutDTO();
    }

    @Override
    public BaseOutPageDTO findArticleListByMenuId(GetArticlePageForm form) {
        BaseOutPageDTO outPageDTO = new BaseOutPageDTO();

        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<ArticlePo> articlePos = articleDao.selectPageByMenuId(form.getDemandMenuId());
        PageInfo pageInfo = new PageInfo<>(articlePos);

        List<GetLv2MenuArticleBo> menuArticleBos = articlePos.stream().map(articlePo -> {
            GetLv2MenuArticleBo articleBo = new GetLv2MenuArticleBo();
            BeanUtils.copyProperties(articlePo, articleBo);
            if (articlePo.getContent().length() > 300) {
                articleBo.setContent(articlePo.getContent().substring(0, 300) + "...");
            }

            // 图片地址转数组
            if (!StringUtils.isEmpty(articlePo.getImages())) {
                articleBo.setImages(Arrays.asList(articlePo.getImages().split(",")));
            }
            articleBo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(articlePo.getCreateTime()));
            return articleBo;
        }).collect(Collectors.toList());

        return outPageDTO.setData((int) pageInfo.getTotal(), menuArticleBos);
    }

    @Override
    public BaseOutDTO findArticleInfo(int userId, int articleId) {
        BaseOutDTO outDTO = new BaseOutDTO();
        ArticlePo articlePo = articleDao.selectByPrimaryKey(articleId);
        if (ObjectUtils.isEmpty(articlePo)) {
            return outDTO.fail("文章不存在");
        }

        GetArticleInfoBo bo = new GetArticleInfoBo();
        BeanUtils.copyProperties(articlePo, bo);
        if (!StringUtils.isEmpty(articlePo.getImages())) {
            String[] urlArr = articlePo.getImages().split(",");
            bo.setImages(Arrays.asList(urlArr));
        }
        bo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(articlePo.getCreateTime()));

        // 检查是否已经收藏
        CollectArticlePo collectArticlePo = collectArticleDao.selectByUserIdAndArticleId(userId, articleId);
        bo.setCollected(ObjectUtils.isEmpty(collectArticlePo) ? 0 : 1);
        return outDTO.setData(bo);
    }

    @Override
    public BaseOutPageDTO searchArticle(String keyword) {
        BaseOutPageDTO outPageDTO = new BaseOutPageDTO();
        List<ArticlePo> articlePoList = articleDao.selectFuzzyArticle(keyword);
        List<GetLv2MenuArticleBo> articleBoList = new ArrayList<>();
        GetLv2MenuArticleBo articleBo;
        for (ArticlePo articlePo : articlePoList) {
            articleBo = new GetLv2MenuArticleBo();
            articleBoList.add(articleBo);
            BeanUtils.copyProperties(articlePo, articleBo);
            if (articlePo.getContent().length() > 300) {
                articleBo.setContent(articlePo.getContent().substring(0, 300) + "...");
            }

            if (!StringUtils.isEmpty(articlePo.getImages())) {
                String[] imgArr = articlePo.getImages().split(",");
                articleBo.setImages(Arrays.asList(imgArr));
            }
            articleBo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(articlePo.getCreateTime()));
        }

        return outPageDTO.setData(0, articleBoList);
    }

    @Override
    public BaseOutDTO selectArticleById(int articleId) {
        return new BaseOutDTO().setData(articleDao.selectById(articleId));
    }
}
