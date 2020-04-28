package com.gmsj.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.util.TimestampUtil;
import com.gmsj.constant.EducationEnum;
import com.gmsj.constant.WorkExperienceEnum;
import com.gmsj.dao.CardFolderDao;
import com.gmsj.dao.CompanyDao;
import com.gmsj.dao.JobMenuDao;
import com.gmsj.model.bo.GetListWorkBo;
import com.gmsj.model.bo.GetWorkInfoBo;
import com.gmsj.model.bo.GetWorkMenuBo;
import com.gmsj.model.bo.PublishWorkLv2MenuListBo;
import com.gmsj.model.po.CardFolderPo;
import com.gmsj.model.po.CompanyPo;
import com.gmsj.model.po.JobMenuPo;
import com.gmsj.model.po.WorkPo;
import com.gmsj.web.form.GetWorkListForm;
import com.gmsj.web.form.SaveWorkForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.gmsj.dao.WorkDao;
import com.gmsj.service.WorkService;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author baojieren
 * @date 2020/4/23 10:14
 */
@Service
public class WorkServiceImpl implements WorkService {

    @Resource
    WorkDao workDao;
    @Resource
    JobMenuDao jobMenuDao;
    @Resource
    CompanyDao companyDao;
    @Resource
    CardFolderDao cardFolderDao;

    @Override
    public BaseOutDTO getJobMenuList() {
        BaseOutDTO outDTO = new BaseOutDTO();
        GetWorkMenuBo bo = new GetWorkMenuBo();
        // 获取菜单
        List<JobMenuPo> jobMenuPoList = jobMenuDao.selectAllByValid(1);

        if (ObjectUtils.isEmpty(jobMenuPoList)) {
            return outDTO;
        }
        bo.setWorkMenuList(jobMenuPoList);

        // 取第一个的前15条招聘信息
        PageHelper.startPage(1, 15);
        List<WorkPo> workPoList = workDao.selectPageByMenuIdAndValid(jobMenuPoList.get(0).getId(), 1);

        List<GetListWorkBo> boList = new ArrayList<>();
        for (WorkPo work : workPoList) {
            GetListWorkBo getListWorkBo = new GetListWorkBo();
            boList.add(getListWorkBo);
            BeanUtils.copyProperties(work, getListWorkBo);
            // 查询公司名称
            CompanyPo companyPo = companyDao.selectByPrimaryKey(work.getCompanyId());
            getListWorkBo.setCompanyName(ObjectUtils.isEmpty(companyPo) ? "" : companyPo.getName());
            getListWorkBo.setExperience(WorkExperienceEnum.getValue(work.getExperience()));
            getListWorkBo.setEducation(EducationEnum.getValue(work.getEducation()));
            if (!StringUtils.isEmpty(work.getImages())) {
                String[] imgArr = work.getImages().split(",");
                getListWorkBo.setImage(imgArr[0]);
            }
            getListWorkBo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(work.getCreateTime()));
        }

        bo.setWorkBoList(boList);
        return outDTO.setData(bo);
    }

    @Override
    public BaseOutPageDTO searchWork(String keyword) {
        BaseOutPageDTO outPageDTO = new BaseOutPageDTO();

        List<WorkPo> workPoList = workDao.selectFuzzyWork(keyword);
        List<GetListWorkBo> boList = workPoList.stream().map(work -> {
            GetListWorkBo getListWorkBo = new GetListWorkBo();
            BeanUtils.copyProperties(work, getListWorkBo);
            // 查询公司名称
            CompanyPo companyPo = companyDao.selectByPrimaryKey(work.getCompanyId());
            getListWorkBo.setCompanyName(ObjectUtils.isEmpty(companyPo) ? "" : companyPo.getName());
            getListWorkBo.setExperience(WorkExperienceEnum.getValue(work.getExperience()));
            getListWorkBo.setEducation(EducationEnum.getValue(work.getEducation()));
            if (!StringUtils.isEmpty(work.getImages())) {
                String[] imgArr = work.getImages().split(",");
                getListWorkBo.setImage(imgArr[0]);
            }
            getListWorkBo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(work.getCreateTime()));
            return getListWorkBo;
        }).collect(Collectors.toList());

        return outPageDTO.setData(0, boList);
    }

    @Override
    public BaseOutDTO saveWork(SaveWorkForm form) {
        WorkPo workPo = new WorkPo();
        BeanUtils.copyProperties(form, workPo);
        if (!StringUtils.isEmpty(form.getImages())) {
            workPo.setImages(form.getImages().toString().replace(" ", "").replace("[", "").replace("]", ""));
        }
        workDao.insertSelective(workPo);
        return new BaseOutDTO();
    }

    @Override
    public BaseOutDTO getLv2List() {
        BaseOutDTO outDTO = new BaseOutDTO();
        PublishWorkLv2MenuListBo bo = new PublishWorkLv2MenuListBo();
        List<JobMenuPo> jobMenuPoList = jobMenuDao.selectAllByValid(1);
        bo.setMenuList(jobMenuPoList);
        bo.setEducationList(EducationEnum.toList());
        bo.setWorkExperienceList(WorkExperienceEnum.toList());
        return outDTO.setData(bo);
    }

    @Override
    public BaseOutPageDTO getWorkList(GetWorkListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<WorkPo> workPoList = workDao.selectPageByMenuIdAndValid(form.getLv2MenuId(), 1);
        PageInfo pageInfo = new PageInfo<>(workPoList);

        List<GetListWorkBo> boList = workPoList.stream().map(work -> {
            GetListWorkBo getListWorkBo = new GetListWorkBo();
            BeanUtils.copyProperties(work, getListWorkBo);
            // 查询公司名称
            CompanyPo companyPo = companyDao.selectByPrimaryKey(work.getCompanyId());
            getListWorkBo.setCompanyName(ObjectUtils.isEmpty(companyPo) ? "" : companyPo.getName());
            getListWorkBo.setExperience(WorkExperienceEnum.getValue(work.getExperience()));
            getListWorkBo.setEducation(EducationEnum.getValue(work.getEducation()));
            if (!StringUtils.isEmpty(work.getImages())) {
                String[] imgArr = work.getImages().split(",");
                getListWorkBo.setImage(imgArr[0]);
            }
            getListWorkBo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(work.getCreateTime()));
            return getListWorkBo;
        }).collect(Collectors.toList());

        return new BaseOutPageDTO().setData((int) pageInfo.getTotal(), boList);
    }

    @Override
    public BaseOutDTO getWorkInfo(int workId) {
        BaseOutDTO outDTO = new BaseOutDTO();
        WorkPo workPo = workDao.selectByPrimaryKey(workId);
        if (ObjectUtils.isEmpty(workId)) {
            return outDTO.fail("招聘信息不存在");
        }

        GetWorkInfoBo bo = new GetWorkInfoBo();
        BeanUtils.copyProperties(workPo, bo);
        if (workPo.getCompanyId() != null) {
            CompanyPo companyPo = companyDao.selectByPrimaryKey(workPo.getCompanyId());
            bo.setCompanyName(companyPo == null ? "" : companyPo.getName());
        }

        bo.setExperience(WorkExperienceEnum.getValue(workPo.getExperience()));
        bo.setEducation(EducationEnum.getValue(workPo.getEducation()));

        if (!StringUtils.isEmpty(workPo.getImages())) {
            bo.setImages(Arrays.asList(workPo.getImages().split(",")));
        }
        bo.setCreateTime(TimestampUtil.formatStandardLocalDateTime(workPo.getCreateTime()));
        return outDTO.setData(bo);
    }

    @Override
    public BaseOutDTO sendCard2work(int myId, int bizUserId) {
        CardFolderPo cardFolderPo = cardFolderDao.selectByBizUserId(bizUserId, myId);
        if (ObjectUtils.isEmpty(cardFolderPo)) {
            cardFolderPo = new CardFolderPo();
            cardFolderPo.setUserId(bizUserId);
            cardFolderPo.setBizUserId(myId);
            cardFolderDao.insertSelective(cardFolderPo);
        }
        return new BaseOutDTO();
    }
}
