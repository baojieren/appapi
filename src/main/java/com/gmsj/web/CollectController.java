package com.gmsj.web;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.model.UserInfo;
import com.gmsj.service.CollectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 收藏
 *
 * @author baojieren
 * @date 2020/4/22 18:41
 */
@RestController
@RequestMapping("/collect")
public class CollectController extends BaseController {

    @Resource
    CollectService collectService;

    /**
     * 收藏文章
     */
    @GetMapping("incr")
    public BaseOutDTO incrCollect(Integer articleId, HttpServletRequest request) {
        UserInfo userInfo = getUserInfo(request);
        return collectService.doCollect(userInfo.getId(), articleId,1);
    }

    /**
     * 取消收藏文章
     */
    @GetMapping("rcni")
    public BaseOutDTO rcniCollect(Integer articleId, HttpServletRequest request) {
        UserInfo userInfo = getUserInfo(request);
        return collectService.doCollect(userInfo.getId(), articleId,-1);
    }
}
