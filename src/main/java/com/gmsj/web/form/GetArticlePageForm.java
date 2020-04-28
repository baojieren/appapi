package com.gmsj.web.form;

import com.gmsj.common.dto.BasePageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author baojieren
 * @date 2020/4/22 10:42
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetArticlePageForm extends BasePageForm {

    /**
     * 分类id
     */
    public Integer demandMenuId;
}
