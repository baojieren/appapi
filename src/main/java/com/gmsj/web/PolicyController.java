package com.gmsj.web;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.util.CheckUtil;
import com.gmsj.service.PolicyService;
import com.gmsj.web.form.GetPolicyPageForm;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author baojieren
 * @date 2020/4/24 14:41
 */
@RestController
@RequestMapping("/policy")
public class PolicyController {

    @Resource
    PolicyService policyService;

    /**
     * 获取政策资讯二级菜单及默认文章列表
     */
    @GetMapping("menu/lv2")
    public BaseOutDTO getPolicyLv2MenuAndDefaultPolicy() {
        return policyService.getPolicyLv2MenuAndDefaultPolicy();
    }

    /**
     * 根据政策分类id分页查询政策文章 (首页资讯文章和资讯页文章都可用)
     */
    @PostMapping("pageList")
    public BaseOutPageDTO getPolicyPageByMenuId(@RequestBody GetPolicyPageForm form) {
        return policyService.getPolicyPageByMenuId(form);
    }

    /**
     * 用资讯文章id查询详情
     */
    @GetMapping("one/info")
    public BaseOutDTO getPolicyInfo(Integer policyId) {
        CheckUtil.isEmpty("文章id", policyId);
        return policyService.getPolicyInfo(policyId);
    }

}
