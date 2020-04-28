package com.gmsj.web.form;

import com.gmsj.common.dto.BasePageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author baojieren
 * @date 2020/4/24 15:12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetPolicyPageForm extends BasePageForm {

    /**
     * 政策资讯二级分类id
     */
    public Integer menuId;

}
