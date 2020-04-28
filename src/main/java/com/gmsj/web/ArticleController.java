package com.gmsj.web;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.exception.BaseError;
import com.gmsj.common.exception.BaseRuntimeException;
import com.gmsj.common.model.UserInfo;
import com.gmsj.common.util.CheckUtil;
import com.gmsj.web.form.SaveArticleForm;
import com.gmsj.web.form.GetArticlePageForm;
import com.gmsj.service.ArticleService;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文章相关接口
 *
 * @author baojieren
 * @date 2020/4/22 10:33
 */
@RestController
@RequestMapping("/article")
public class ArticleController extends BaseController {

    @Resource
    ArticleService articleService;

    /**
     * 查询需求广场一级菜单
     */
    @GetMapping("lv1")
    public BaseOutDTO findLv1DemandMenu() {
        return articleService.findLv1DemandMenu();
    }

    /**
     * 需求广场查询二级菜单及默认10条文章
     */
    @GetMapping("lv2/init")
    public BaseOutDTO findLv2MenuAndFirst10Article(Integer lv1Id) {
        CheckUtil.isEmpty("一级菜单id", lv1Id);
        return articleService.findLv2Menu(lv1Id);
    }

    /**
     * 发布按钮返回二级菜单列表
     */
    @GetMapping("lv2/publish")
    public BaseOutDTO findAllLv2(Integer lv1Id) {
        CheckUtil.isEmpty("一级菜单id", lv1Id);
        return articleService.findLv2MenuAll(lv1Id);
    }

    /**
     * 搜索
     */
    @GetMapping("search")
    public BaseOutPageDTO searchArticle(String keyword) {
        CheckUtil.isEmpty("关键字", keyword);
        return articleService.searchArticle(keyword);
    }

    /**
     * 发布文章
     */
    @PostMapping("save")
    public BaseOutDTO saveArticle(@RequestBody SaveArticleForm form, HttpServletRequest request) {
        CheckUtil.isEmpty("分类id", form.getDemandMenuId());
        CheckUtil.isEmpty("标题", form.getTitle());
        CheckUtil.isEmpty("内容", form.getContent());
        UserInfo userInfo = getUserInfo(request);
        if (ObjectUtils.isEmpty(userInfo)) {
            // 没有用户信息,表明用户没登录
            throw new BaseRuntimeException(BaseError.AUTH_FAILED);
        }
        form.setOwnerId(userInfo.getId());
        return articleService.createArticle(form);
    }

    /**
     * 根据二级分类id分页查询文章列表
     */
    @PostMapping("page/list")
    public BaseOutPageDTO findArticlePageByPage(@RequestBody GetArticlePageForm form) {
        CheckUtil.isEmpty("页码", form.getPageNum());
        CheckUtil.isEmpty("每页条数", form.getPageSize());
        CheckUtil.isEmpty("分类id", form.getDemandMenuId());
        return articleService.findArticleListByMenuId(form);
    }

    /**
     * 查询文章详细
     */
    @GetMapping("one/info")
    public BaseOutDTO getOneArticleInfo(Integer articleId, HttpServletRequest request) {
        CheckUtil.isEmpty("文章id", articleId);
        UserInfo userInfo = getUserInfo(request);
        return articleService.findArticleInfo(userInfo.getId(), articleId);
    }

    /**
     * @Description: 根据文章id获取文章详情
     * @Param [request]
     * @Author: zlh
     * @Date: 2020/4/24 16:17
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @GetMapping("info")
    public BaseOutDTO findArticleById(Integer articleId) {
        return articleService.selectArticleById(articleId);
    }
}
