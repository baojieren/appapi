package com.gmsj.service.impl;

import com.gmsj.base.CustomError;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.exception.BaseRuntimeException;
import com.gmsj.common.model.UserInfo;
import com.gmsj.dao.*;
import com.gmsj.model.bo.GetMyProfileBo;
import com.gmsj.model.bo.IndustryBo;
import com.gmsj.model.bo.UserCardInfoBo;
import com.gmsj.model.po.*;
import com.gmsj.web.form.SaveCardInfoForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.gmsj.service.CardFolderService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baojieren
 * @date 2020/4/23 10:02
 */
@Slf4j
@Service
public class CardFolderServiceImpl implements CardFolderService {

    @Resource
    CardFolderDao cardFolderDao;
    @Resource
    CardPraiseDao cardPraiseDao;
    @Resource
    CardBrowseDao cardBrowseDao;
    @Resource
    CompanyDao companyDao;
    @Resource
    UserDao userDao;
    @Resource
    CompanyIndustryDao companyIndustryDao;
    @Resource
    IndustryDao industryDao;

    @Override
    public BaseOutDTO getMyCardInfo(UserInfo userInfo) {
        BaseOutDTO outDTO = new BaseOutDTO();
        UserCardInfoBo bo = new UserCardInfoBo();
        outDTO.setData(bo);

        BeanUtils.copyProperties(userInfo, bo);
        bo.setUserId(userInfo.getId());
        // 公司名称
        CompanyPo companyPo = companyDao.selectByPrimaryKey(userInfo.getCompanyId());
        bo.setCompanyName(ObjectUtils.isEmpty(companyPo) ? "" : companyPo.getName());

        // 名片被保存数
        Integer saveSum = cardFolderDao.countByBizUserId(userInfo.getId());
        bo.setCardSaveSum(saveSum == null ? 0 : saveSum);

        // 名片被点赞数
        Integer praiseSum = cardPraiseDao.countByBizUserId(userInfo.getId());
        bo.setPraiseSum(praiseSum == null ? 0 : praiseSum);

        // 名片被浏览数
        Integer browseSum = cardBrowseDao.countByBizUserId(userInfo.getId());
        bo.setBrowseSum(browseSum == null ? 0 : browseSum);
        return outDTO;
    }

    @Override
    public BaseOutDTO getMyProfile(int userId) {
        UserPo userPo = userDao.selectByPrimaryKey(userId);
        if (ObjectUtils.isEmpty(userPo)) {
            throw new BaseRuntimeException(new CustomError("用户不存在"));
        }

        GetMyProfileBo bo = new GetMyProfileBo();
        BeanUtils.copyProperties(userPo, bo);

        List<IndustryBo> allIndustryList = new ArrayList<>();
        IndustryBo industryBo;
        // 查询所有行业
        List<IndustryPo> industryPoList = industryDao.selectAll();
        for (IndustryPo industryPo : industryPoList) {
            industryBo = new IndustryBo();
            BeanUtils.copyProperties(industryPo, industryBo);
            allIndustryList.add(industryBo);
        }

        // 查询我公司的行业
        List<Integer> myIndustryIdList = new ArrayList<>();
        if (userPo.getCompanyId() != null) {
            List<CompanyIndustryPo> companyIndustryPos = companyIndustryDao.selectAllByCompanyId(userPo.getCompanyId());
            if (!ObjectUtils.isEmpty(companyIndustryPos)) {
                for (CompanyIndustryPo industryPo : companyIndustryPos) {
                    myIndustryIdList.add(industryPo.getIndustryId());
                }
            }

        }
        bo.setMyIndustryIdList(myIndustryIdList);
        bo.setIndustryList(allIndustryList);
        return new BaseOutDTO().setData(bo);
    }

    @Override
    @Transactional
    public BaseOutDTO updateProfile(SaveCardInfoForm form) {
        UserPo userPo = new UserPo();
        BeanUtils.copyProperties(form, userPo);

        if (!StringUtils.isEmpty(form.getCompanyName())) {
            CompanyPo companyPo = companyDao.selectOneByName(form.getCompanyName());
            if (ObjectUtils.isEmpty(companyPo)) {
                companyPo = new CompanyPo();
                companyPo.setName(form.getCompanyName());
                companyDao.insertSelective(companyPo);
                userPo.setCompanyId(companyPo.getId());
            }

            // 保存公司行业
            if (!ObjectUtils.isEmpty(form.getIndustryList())) {
                // 先删除
                companyIndustryDao.deleteByCompanyId(companyPo.getId());
                for (Integer industryId : form.getIndustryList()) {
                    CompanyIndustryPo industryPo = new CompanyIndustryPo();
                    industryPo.setCompanyId(companyPo.getId());
                    industryPo.setIndustryId(industryId);
                    companyIndustryDao.insertSelective(industryPo);
                }
            }
        }
        userDao.updateByPrimaryKeySelective(userPo);
        return new BaseOutDTO();
    }

    @Override
    public BaseOutDTO getOrRemoveCard(int myId, int bizUserId, int isGet) {
        CardFolderPo cardFolderPo = cardFolderDao.selectByBizUserId(myId, bizUserId);
        if (isGet == 1) {
            // 保存对方名片
            if (ObjectUtils.isEmpty(cardFolderPo)) {
                cardFolderPo = new CardFolderPo();
                cardFolderPo.setUserId(myId);
                cardFolderPo.setBizUserId(bizUserId);
                cardFolderDao.insertSelective(cardFolderPo);
            }
        } else {
            // 取消保存对方名片
            if (!ObjectUtils.isEmpty(cardFolderPo)) {
                cardFolderDao.deleteByPrimaryKey(cardFolderPo.getId());
            }
        }
        return new BaseOutDTO();
    }

    @Override
    public BaseOutDTO praiseOrRemoveCard(int myId, int bizUserId, int isPraise) {
        CardPraisePo cardPraisePo = cardPraiseDao.selectByBizUserId(myId, bizUserId);
        if (isPraise == 1) {
            // 点赞对方名片
            if (ObjectUtils.isEmpty(cardPraisePo)) {
                cardPraisePo = new CardPraisePo();
                cardPraisePo.setUserId(myId);
                cardPraisePo.setBizUserId(bizUserId);
                cardPraiseDao.insertSelective(cardPraisePo);
            }
        } else {
            // 取消点赞对方名片
            if (!ObjectUtils.isEmpty(cardPraisePo)) {
                cardPraiseDao.deleteByPrimaryKey(cardPraisePo.getId());
            }
        }
        return new BaseOutDTO();
    }
}
