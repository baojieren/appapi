package com.gmsj.web.form;

import com.gmsj.common.dto.BasePageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author baojieren
 * @date 2020/4/22 15:48
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetCommentListForm extends BasePageForm {

    /**
     * 文章id
     */
    public Integer articleId;
}
