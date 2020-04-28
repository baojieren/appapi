package com.gmsj.service.impl;

import com.gmsj.base.CustomError;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.exception.BaseRuntimeException;
import com.gmsj.dao.ArticleDao;
import com.gmsj.dao.CollectArticleDao;
import com.gmsj.model.po.ArticlePo;
import com.gmsj.model.po.CollectArticlePo;
import com.gmsj.service.CollectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

/**
 * @author baojieren
 * @date 2020/4/22 18:42
 */
@Slf4j
@Service
public class CollectServiceImpl implements CollectService {

    @Resource
    CollectArticleDao collectArticleDao;
    @Resource
    ArticleDao articleDao;

    @Override
    @Transactional
    public BaseOutDTO doCollect(int userId, int articleId, int incr) {
        BaseOutDTO outDTO = new BaseOutDTO();

        // 判断是否已经收藏
        CollectArticlePo collectArticlePo = collectArticleDao.selectByUserIdAndArticleId(userId, articleId);

        boolean flag = false;

        // 添加收藏
        if (incr == 1) {
            if (ObjectUtils.isEmpty(collectArticlePo)) {
                collectArticlePo = new CollectArticlePo();
                collectArticlePo.setArticleId(articleId);
                collectArticlePo.setUserId(userId);
                collectArticleDao.insertSelective(collectArticlePo);
                flag = true;
            }
        }

        // 取消收藏
        if (incr == -1) {
            if (!ObjectUtils.isEmpty(collectArticlePo)) {
                collectArticleDao.deleteByPrimaryKey(collectArticlePo.getId());
                flag = true;
            }
        }

        // 更新文章点赞数
        if (flag) {
            ArticlePo byPrimaryKey = articleDao.selectByPrimaryKey(articleId);
            if (ObjectUtils.isEmpty(byPrimaryKey)) {
                throw new BaseRuntimeException(new CustomError("文章不存在"));
            }

            byPrimaryKey.setCollectSum(byPrimaryKey.getCollectSum() + incr);
            articleDao.updateByPrimaryKeySelective(byPrimaryKey);
        }
        return new BaseOutDTO();
    }
}
