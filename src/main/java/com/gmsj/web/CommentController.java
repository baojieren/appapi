package com.gmsj.web;

import com.gmsj.base.YmlConfig;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.util.CheckUtil;
import com.gmsj.service.CommentService;
import com.gmsj.web.form.CreateCommentForm;
import com.gmsj.web.form.GetCommentListForm;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author baojieren
 * @date 2020/4/22 14:18
 */
@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {
    @Resource
    YmlConfig ymlConfig;

    @Resource
    CommentService commentService;

    /**
     * 保存评论
     */
    @PostMapping("save")
    public BaseOutDTO saveComment(@RequestBody CreateCommentForm form, HttpServletRequest request) {
        CheckUtil.isEmpty("文章id", form.getArticleId());
        CheckUtil.isEmpty("评论内容", form.getContent());
        CheckUtil.isEmpty("type", form.getType());
        if (form.getType() == 2) {
            CheckUtil.isEmpty("回复评论必须传被评论id", form.getCommentId());
        }
        return commentService.saveComment(form, getUserInfo(request));
    }

    /**
     * 分页查评论列表
     */
    @PostMapping("list")
    public BaseOutPageDTO findCommentPage(@RequestBody GetCommentListForm form) {
        CheckUtil.isEmpty("文章id", form.getArticleId());
        return commentService.findCommentPage(form);
    }
}
