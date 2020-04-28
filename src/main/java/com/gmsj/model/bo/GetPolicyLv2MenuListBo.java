package com.gmsj.model.bo;

import com.gmsj.model.po.PolicyMenuPo;
import lombok.Data;

import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/24 14:49
 */
@Data
public class GetPolicyLv2MenuListBo {

    /**
     * 二级分类列表
     */
    List<PolicyMenuPo> menuList;

    /**
     * 默认文章列表
     */
    List<PolicyProfileBo> policyProfileList;

}
