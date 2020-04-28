package com.gmsj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.util.TimestampUtil;
import com.gmsj.dao.PolicyMenuDao;
import com.gmsj.model.bo.GetPolicyLv2MenuListBo;
import com.gmsj.model.bo.PolicyProfileBo;
import com.gmsj.model.po.PolicyMenuPo;
import com.gmsj.model.po.PolicyPo;
import com.gmsj.model.po.WorkPo;
import com.gmsj.web.form.GetPolicyPageForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.gmsj.dao.PolicyDao;
import com.gmsj.service.PolicyService;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/24 14:41
 */
@Slf4j
@Service
public class PolicyServiceImpl implements PolicyService {

    @Resource
    PolicyDao policyDao;
    @Resource
    PolicyMenuDao policyMenuDao;

    @Override
    public BaseOutDTO getPolicyLv2MenuAndDefaultPolicy() {
        BaseOutDTO outDTO = new BaseOutDTO();
        GetPolicyLv2MenuListBo bo = new GetPolicyLv2MenuListBo();
        outDTO.setData(bo);

        List<PolicyMenuPo> policyMenuPos = policyMenuDao.selectAll();
        if (ObjectUtils.isEmpty(policyMenuPos)) {
            return outDTO;
        }
        bo.setMenuList(policyMenuPos);

        List<PolicyProfileBo> policyProfileList = new ArrayList<>();
        PolicyProfileBo policyProfileBo;

        PageHelper.startPage(1, 10);
        List<PolicyPo> policyPoList = policyDao.selectPageByPolicyMenuIdAndValid(policyMenuPos.get(0).getId());
        for (PolicyPo policyPo : policyPoList) {
            policyProfileBo = new PolicyProfileBo();
            policyProfileList.add(policyProfileBo);
            BeanUtils.copyProperties(policyPo, policyProfileBo);

            if (!StringUtils.isEmpty(policyPo.getImages())) {
                String[] imgArr = policyPo.getImages().split(",");
                policyProfileBo.setImages(Arrays.asList(imgArr));
            }

            PolicyMenuPo policyMenuPo = policyMenuDao.selectByPrimaryKey(policyPo.getPolicyMenuId());
            policyProfileBo.setMenuName(policyMenuPo == null ? "" : policyMenuPo.getName());
            policyProfileBo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(policyPo.getCreateTime()));

        }

        bo.setPolicyProfileList(policyProfileList);
        return outDTO;
    }

    @Override
    public BaseOutPageDTO getPolicyPageByMenuId(GetPolicyPageForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<PolicyPo> policyPoList = policyDao.selectPageByPolicyMenuIdAndValid(form.getMenuId());
        PageInfo pageInfo = new PageInfo<>(policyPoList);

        List<PolicyProfileBo> policyProfileList = new ArrayList<>();
        PolicyProfileBo policyProfileBo;

        for (PolicyPo policyPo : policyPoList) {
            policyProfileBo = new PolicyProfileBo();
            policyProfileList.add(policyProfileBo);
            BeanUtils.copyProperties(policyPo, policyProfileBo);

            if (!StringUtils.isEmpty(policyPo.getImages())) {
                String[] imgArr = policyPo.getImages().split(",");
                policyProfileBo.setImages(Arrays.asList(imgArr));
            }

            PolicyMenuPo policyMenuPo = policyMenuDao.selectByPrimaryKey(policyPo.getPolicyMenuId());
            policyProfileBo.setMenuName(policyMenuPo == null ? "" : policyMenuPo.getName());
            policyProfileBo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(policyPo.getCreateTime()));

        }
        return new BaseOutPageDTO().setData((int) pageInfo.getTotal(), policyProfileList);
    }

    @Override
    public BaseOutDTO getPolicyInfo(Integer policyId) {
        BaseOutDTO outDTO = new BaseOutDTO();
        PolicyPo policyPo = policyDao.selectByPrimaryKey(policyId);
        if (ObjectUtils.isEmpty(policyPo)) {
            return outDTO.fail("文章不存在");
        }

        PolicyProfileBo bo = new PolicyProfileBo();
        BeanUtils.copyProperties(policyPo, bo);

        if (!StringUtils.isEmpty(policyPo.getImages())) {
            String[] imgArr = policyPo.getImages().split(",");
            bo.setImages(Arrays.asList(imgArr));
        }

        PolicyMenuPo policyMenuPo = policyMenuDao.selectByPrimaryKey(policyPo.getPolicyMenuId());
        bo.setMenuName(policyMenuPo == null ? "" : policyMenuPo.getName());
        bo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(policyPo.getCreateTime()));

        return outDTO.setData(bo);
    }
}
