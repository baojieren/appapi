package com.gmsj.web;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.model.UserInfo;
import com.gmsj.common.util.CheckUtil;
import com.gmsj.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @ProjectName: 贵煤数据
 * @Author: zlh
 * @Date: 2020/4/23 17:43
 * @Description: 用户相关接口
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Resource
    UserService userService;

    /**
     * @Description: 极速找人页面数据接口
     * @Param [request, menuId]
     * @Author: zlh
     * @Date: 2020/4/24 10:15
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @GetMapping("fastFind")
    public BaseOutDTO userFastFind(HttpServletRequest request){
        UserInfo userInfo = getUserInfo(request);
        return userService.findUserFast(userInfo);
    }

    /**
     * @Description: 极速找人搜索
     * @Param [userName]
     * @Author: zlh
     * @Date: 2020/4/24 10:14
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @GetMapping("search")
    public BaseOutDTO userSearch(HttpServletRequest request, Integer menuId, String keyword){
        CheckUtil.isEmpty("菜单id", menuId);
        CheckUtil.isEmpty("关键字", keyword);
        UserInfo userInfo = getUserInfo(request);
        return userService.searchUser(userInfo, menuId, keyword);
    }

    /**
     * @Description: 我的发布
     * @Param [request]
     * @Author: zlh
     * @Date: 2020/4/24 15:33
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @GetMapping("publish")
    public BaseOutDTO findUserArticle(HttpServletRequest request){
        UserInfo userInfo = getUserInfo(request);
        return userService.findArticleByUserId(userInfo);
    }

    /**
     * @Description: 我的评论
     * @Param [request]
     * @Author: zlh
     * @Date: 2020/4/24 15:35
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @GetMapping("comment")
    public BaseOutDTO findUserComment(HttpServletRequest request){
        UserInfo userInfo = getUserInfo(request);
        return userService.findUserCommentByUserId(userInfo);
    }

    /**
     * @Description: 我的收藏
     * @Param [request]
     * @Author: zlh
     * @Date: 2020/4/24 16:06
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @GetMapping("collection")
    public BaseOutDTO findUserCollection(HttpServletRequest request){
        UserInfo userInfo = getUserInfo(request);
        return userService.findArticleCollect(userInfo);
    }

    @GetMapping("cardFolder")
    public BaseOutDTO findUserCardFolder(HttpServletRequest request){
        UserInfo userInfo = getUserInfo(request);
        return userService.findUserCardFolder(userInfo);
    }
}
