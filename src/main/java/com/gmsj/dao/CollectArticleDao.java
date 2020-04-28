package com.gmsj.dao;

import com.gmsj.model.po.CollectArticlePo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/22 19:07
 */
public interface CollectArticleDao extends Mapper<CollectArticlePo> {

    /**
     * 查询是否收藏
     */
    CollectArticlePo selectByUserIdAndArticleId(@Param("userId") int userId, @Param("articleId") int articleId);

    List<CollectArticlePo> selectByUserId(int userId);
}