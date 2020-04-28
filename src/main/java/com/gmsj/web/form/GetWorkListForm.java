package com.gmsj.web.form;

import com.gmsj.common.dto.BasePageForm;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author baojieren
 * @date 2020/4/23 18:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GetWorkListForm extends BasePageForm {

    public Integer lv2MenuId;
}
