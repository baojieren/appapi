package com.gmsj.web;

import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.dto.BaseOutPageDTO;
import com.gmsj.common.model.UserInfo;
import com.gmsj.common.util.CheckUtil;
import com.gmsj.service.WorkService;
import com.gmsj.web.form.GetWorkListForm;
import com.gmsj.web.form.SaveWorkForm;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author baojieren
 * @date 2020/4/23 10:15
 */
@RestController
@RequestMapping("/work")
public class WorkController extends BaseController {
    @Resource
    WorkService workService;

    /**
     * 获取招聘分类列表(首页点击求职招聘进来)
     */
    @GetMapping("jobmenu")
    public BaseOutDTO getJobMenuList() {
        return workService.getJobMenuList();
    }

    /**
     * 搜索
     */
    @GetMapping("search")
    public BaseOutPageDTO searchWork(String keyword) {
        CheckUtil.isEmpty("关键字", keyword);
        return workService.searchWork(keyword);
    }

    /**
     * 点击发布按钮,获取招聘类的二级分类
     */
    @GetMapping("lv2/list")
    public BaseOutDTO getLv2List() {
        return workService.getLv2List();
    }

    /**
     * 发布岗位招聘
     */
    @PostMapping("save")
    public BaseOutDTO createWork(@RequestBody SaveWorkForm form, HttpServletRequest request) {
        CheckUtil.isEmpty("分类id", form.getJobMenuId());
        CheckUtil.isEmpty("岗位名称", form.getWorkName());
        CheckUtil.isEmpty("待遇", form.getSalary());
        CheckUtil.isEmpty("工作地点", form.getWorkAddr());
        CheckUtil.isEmpty("工作经验", form.getExperience());
        CheckUtil.isEmpty("学历", form.getEducation());
        CheckUtil.isEmpty("工作内容描述", form.getWorkDesc());
        CheckUtil.isEmpty("工作单位描述", form.getCompanyDesc());
        UserInfo userInfo = getUserInfo(request);
        form.setUserId(userInfo.getId());
        form.setCompanyId(userInfo.getCompanyId());
        return workService.saveWork(form);
    }

    /**
     * 查询招聘岗位列表
     */
    @PostMapping("list")
    public BaseOutPageDTO getWorkList(@RequestBody GetWorkListForm form) {
        CheckUtil.isEmpty("二级分类id", form.getLv2MenuId());
        return workService.getWorkList(form);
    }

    /**
     * 查询招聘详情
     */
    @GetMapping("one/info")
    public BaseOutDTO getWorkInfo(Integer workId) {
        CheckUtil.isEmpty("招聘id", workId);
        return workService.getWorkInfo(workId);
    }

    /**
     * 对招聘岗位递名片
     */
    @GetMapping("sendCard2work")
    public BaseOutDTO sendCard2work(int bizUserId, HttpServletRequest request) {
        CheckUtil.isEmpty("对方名片id", bizUserId);
        UserInfo userInfo = getUserInfo(request);
        return workService.sendCard2work(userInfo.getId(), bizUserId);
    }
}
