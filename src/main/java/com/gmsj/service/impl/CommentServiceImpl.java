package com.gmsj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gmsj.base.CustomError;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.exception.BaseRuntimeException;
import com.gmsj.common.model.UserInfo;
import com.gmsj.dao.ArticleDao;
import com.gmsj.dao.CommentDao;
import com.gmsj.model.bo.CommentListBo;
import com.gmsj.model.po.ArticlePo;
import com.gmsj.model.po.CommentPo;
import com.gmsj.service.CommentService;
import com.gmsj.web.form.CreateCommentForm;
import com.gmsj.web.form.GetCommentListForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author baojieren
 * @date 2020/4/22 14:27
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
    @Resource
    CommentDao commentDao;
    @Resource
    ArticleDao articleDao;

    @Override
    @Transactional
    public BaseOutDTO saveComment(CreateCommentForm form, UserInfo userInfo) {
        CommentPo commentPo = new CommentPo();
        BeanUtils.copyProperties(form, commentPo);
        commentPo.setUserId(userInfo.getId());
        commentPo.setUserName(userInfo.getUserName());
        commentPo.setUserLogo(userInfo.getLogo());

        // 评论评论或回复评论
        if (form.getType() == 2) {
            CommentPo byPrimaryKey = commentDao.selectByPrimaryKey(form.getCommentId());
            if (byPrimaryKey.getType() == 1) {
                commentPo.setFirstCommentId(byPrimaryKey.getId());
            } else {
                commentPo.setFirstCommentId(byPrimaryKey.getFirstCommentId());
            }
        }
        commentDao.insertSelective(commentPo);

        // 更新文章评论数
        ArticlePo articlePo = articleDao.selectByPrimaryKey(form.getArticleId());
        if (ObjectUtils.isEmpty(articlePo)) {
            throw new BaseRuntimeException(new CustomError("文章不存在"));
        }
        articlePo.setCommentSum(articlePo.getCommentSum() + 1);
        articleDao.updateByPrimaryKeySelective(articlePo);
        return new BaseOutDTO();
    }

    @Override
    public BaseOutPageDTO findCommentPage(GetCommentListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<CommentPo> commentPoList = commentDao.selectCommentList(form.getArticleId());
        PageInfo pageInfo = new PageInfo<>(commentPoList);

        List<CommentListBo> boList = new ArrayList<>();
        CommentListBo bo;
        for (CommentPo commentPo : commentPoList) {
            bo = new CommentListBo();
            boList.add(bo);
            // 一级评论内容
            bo.setId(commentPo.getId());
            bo.setArticleId(commentPo.getArticleId());
            bo.setCommentUserId(commentPo.getUserId());
            bo.setCommentUserName(commentPo.getUserName());
            bo.setCommentUserLogo(commentPo.getUserLogo());
            bo.setContent(commentPo.getContent());

            // 一级评论下的评论(回复)
            List<CommentPo> replyList = commentDao.selectReplyList(commentPo.getId());
            // 回复分组
            Map<Integer, List<CommentPo>> groupByCommentId = replyList.stream().collect(Collectors.groupingBy(CommentPo::getCommentId));

            List<CommentListBo.Reply> boReplyList = new ArrayList<>();
            bo.setReplyList(boReplyList);
            CommentListBo.Reply boReply;
            for (Map.Entry<Integer, List<CommentPo>> entry : groupByCommentId.entrySet()) {
                // 拼装评论下的评论
                CommentPo passiveCommentPo = commentDao.selectByPrimaryKey(entry.getKey());
                for (CommentPo reply : entry.getValue()) {
                    boReply = new CommentListBo.Reply();
                    boReplyList.add(boReply);
                    boReply.setCommentUserId(passiveCommentPo.getUserId());
                    boReply.setCommentUserName(passiveCommentPo.getUserName());
                    boReply.setCommentContent(passiveCommentPo.getContent());
                    boReply.setReplyUserId(reply.getUserId());
                    boReply.setReplyUserName(reply.getUserName());
                    boReply.setReplyUserLogo(reply.getUserLogo());
                    boReply.setReplyContent(reply.getContent());
                }
            }
        }

        return new BaseOutPageDTO().setData((int) pageInfo.getTotal(), boList);
    }
}
