package com.gmsj.dao;

import com.gmsj.model.po.CommentPo;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/22 16:28
 */
public interface CommentDao extends Mapper<CommentPo> {
    /**
     * 查询评论文章列表
     */
    List<CommentPo> selectCommentList(int articleId);

    /**
     * 查询评论的回复列表
     */
    List<CommentPo> selectReplyList(@Param("id") int id);

    /**
     * 查看某人的总评论数
     */
    Integer countByUserId(int userId);

    /**
     * 查询用户评论
     */
    List<CommentPo> selectByUserId(int userId);
}