package com.gmsj.model.bo;

import lombok.Data;

import java.util.List;

/**
 * @projectName: 贵煤数据
 * @author: zlh
 * @date: 2020/4/24 08:58
 * @description:
 */
@Data
public class SearchUserBo {

    /**
     * 用户列表
     */
    private List<UserCountCompareBo> userBos;

    /**
     * 公司列表
     */
    private List<CompanyBo> companyBos;


}
