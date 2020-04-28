package com.gmsj.model.bo;

import lombok.Data;


/**
 * @projectName: 贵煤数据
 * @author: zlh
 * @date: 2020/4/24 12:23
 * @description:
 */
@Data
public class CompanyBo {


    private Integer id;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 备注
     */
    private String remark;

}
