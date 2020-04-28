package com.gmsj.service;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.web.form.GetWorkListForm;
import com.gmsj.web.form.SaveWorkForm;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author baojieren
 * @date 2020/4/23 10:14
 */
public interface WorkService {

    /**
     * 获取招聘分类列表
     */
    BaseOutDTO getJobMenuList();

    /**
     * 搜索
     */
    BaseOutPageDTO searchWork(String keyword);

    /**
     * 发布职位
     */
    BaseOutDTO saveWork(SaveWorkForm form);

    /**
     * 获取招聘类的二级分类列表
     */
    BaseOutDTO getLv2List();

    /**
     * 查询招聘信息列表
     */
    BaseOutPageDTO getWorkList(GetWorkListForm form);

    /**
     * 查询招聘详情
     */
    BaseOutDTO getWorkInfo(int workId);

    /**
     * 对招聘信息发送名片
     */
    BaseOutDTO sendCard2work(int myId, int bizUserId);

}
