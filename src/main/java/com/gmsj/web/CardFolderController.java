package com.gmsj.web;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.model.UserInfo;
import com.gmsj.common.util.CheckUtil;
import com.gmsj.service.CardFolderService;
import com.gmsj.web.form.SaveCardInfoForm;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 名片
 *
 * @author baojieren
 * @date 2020/4/23 10:03
 */
@RestController
@RequestMapping("/card")
public class CardFolderController extends BaseController {

    @Resource
    CardFolderService cardFolderService;

    /**
     * 首页个人名片信息,保存,点赞数等
     */
    @GetMapping("myInfo")
    public BaseOutDTO getMyCardInfo(HttpServletRequest request) {
        UserInfo userInfo = getUserInfo(request);
        return cardFolderService.getMyCardInfo(userInfo);
    }

    /**
     * 首页查询名片信息
     */
    @GetMapping("profile")
    public BaseOutDTO getMyProfile(HttpServletRequest request) {
        UserInfo userInfo = getUserInfo(request);
        return cardFolderService.getMyProfile(userInfo.getId());
    }

    /**
     * 修改名片信息
     */
    @PostMapping("updateProfile")
    public BaseOutDTO updateCardInfo(@RequestBody SaveCardInfoForm form) {
        CheckUtil.isEmpty("用户id", form.getId());
        CheckUtil.isEmpty("用户名", form.getUserName());
        CheckUtil.isEmpty("手机号", form.getPhone());
        // CheckUtil.isEmpty("公司名称", form.getCompanyName());
        // CheckUtil.isEmpty("职务", form.getJob());
        // CheckUtil.isEmpty("公司行业", form.getIndustryList());
        return cardFolderService.updateProfile(form);
    }

    /**
     * 保存别人名片
     */
    @GetMapping("getBiz")
    public BaseOutDTO getBizUserCard(Integer bizUserId, HttpServletRequest request) {
        CheckUtil.isEmpty("对方id", bizUserId);
        UserInfo userInfo = getUserInfo(request);
        return cardFolderService.getOrRemoveCard(userInfo.getId(), bizUserId, 1);
    }

    /**
     * 取消保存别人名片
     */
    @GetMapping("removeBiz")
    public BaseOutDTO removeBizUserCard(Integer bizUserId, HttpServletRequest request) {
        CheckUtil.isEmpty("对方id", bizUserId);
        UserInfo userInfo = getUserInfo(request);
        return cardFolderService.getOrRemoveCard(userInfo.getId(), bizUserId, 0);
    }

    /**
     * 点赞别人名片
     */
    @GetMapping("getPraiseBiz")
    public BaseOutDTO getPraiseBizUserCard(Integer bizUserId, HttpServletRequest request) {
        CheckUtil.isEmpty("对方id", bizUserId);
        UserInfo userInfo = getUserInfo(request);
        return cardFolderService.praiseOrRemoveCard(userInfo.getId(), bizUserId, 1);
    }

    /**
     * 取消点赞别人名片
     */
    @GetMapping("removePraiseBiz")
    public BaseOutDTO getRemovePraiseBizUserCard(Integer bizUserId, HttpServletRequest request) {
        CheckUtil.isEmpty("对方id", bizUserId);
        UserInfo userInfo = getUserInfo(request);
        return cardFolderService.praiseOrRemoveCard(userInfo.getId(), bizUserId, 0);
    }

}
