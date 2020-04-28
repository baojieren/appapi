package com.gmsj.service;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.web.form.GetPolicyPageForm;

/**
 * @author baojieren
 * @date 2020/4/24 14:41
 */
public interface PolicyService {

    /**
     * 获取政策资讯二级菜单列表和默认10条政策资讯
     */
    BaseOutDTO getPolicyLv2MenuAndDefaultPolicy();

    /**
     * 分页查询政策资讯文章列表
     */
    BaseOutPageDTO getPolicyPageByMenuId(GetPolicyPageForm form);

    /**
     * 查询文章详情
     */
    BaseOutDTO getPolicyInfo(Integer policyId);

}
