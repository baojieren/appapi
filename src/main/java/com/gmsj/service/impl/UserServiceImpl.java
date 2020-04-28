package com.gmsj.service.impl;

import com.gmsj.base.CustomError;
import com.gmsj.common.dto.BaseOutDTO;
import com.gmsj.common.exception.BaseRuntimeException;
import com.gmsj.common.model.UserInfo;
import com.gmsj.dao.*;
import com.gmsj.model.bo.*;
import com.gmsj.model.po.ArticlePo;
import com.gmsj.model.po.CardFolderPo;
import com.gmsj.model.po.CollectArticlePo;
import com.gmsj.model.po.UserPo;
import com.gmsj.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ProjectName: 贵煤数据
 * @Author: zlh
 * @Date: 2020/4/23 17:56
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;

    @Resource
    CardFolderDao cardFolderDao;

    @Resource
    SearchHistoryMapper searchHistoryMapper;

    @Resource
    CompanyDao companyDao;

    @Resource
    ArticleDao articleDao;

    @Resource
    CommentDao commentDao;

    @Resource
    CollectArticleDao collectArticleDao;

    /**
     * @return
     * @Description: 极速找人搜索
     * @Param [userName] 用户名
     * @Author: zlh
     * @Date: 2020/4/23 19:04
     */
    @Override
    public BaseOutDTO searchUser(UserInfo userInfo, Integer menuId, String keyword) {
        //获取满足条件用户列表
        List<UserCountCompareBo> userCountCompareBos = userDao.searchUserBo(keyword);
        //获取满足条件的公司列表
        List<CompanyBo> companyBos = companyDao.matchCompany(keyword);
        SearchUserBo searchUserBo = new SearchUserBo();
        for (UserCountCompareBo user : userCountCompareBos) {
            //获取用户名片夹数量
            Integer countFolder = cardFolderDao.countByBizUserId(user.getId());
            //添加数量
            user.setCountFolder(countFolder);
        }
        //排序
        Collections.sort(userCountCompareBos);
        //添加用户列表
        searchUserBo.setUserBos(userCountCompareBos);
        //添加公司列表
        searchUserBo.setCompanyBos(companyBos);
        //添加搜索记录
        Integer integer = searchHistoryMapper.insertHistory(userInfo.getId(), menuId, keyword);
        if (integer == null){
            throw new BaseRuntimeException(new CustomError("搜索历史插入失败"));
        }
        return new BaseOutDTO().setData(searchUserBo);
    }


    /**
     * @Description: 极速找人页面数据
     * @Param [userInfo, menuId]
     * @Author: zlh
     * @Date: 2020/4/24 12:52
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @Override
    public BaseOutDTO findUserFast(UserInfo userInfo) {
        //排序保存数
        List<CardFolderCountBo> cardFolderCountBos = cardFolderDao.cardFolderCount();
        Collections.sort(cardFolderCountBos);
        //取前15个
        if (cardFolderCountBos.size() > 15) {
            cardFolderCountBos.subList(0, 15);
        }
        //保存用户数据
        List<UserPo> userPos = new ArrayList<>();
        UserFindFastBo userFindFastBos = new UserFindFastBo();

        //添加数据
        for (CardFolderCountBo cardFolderCountBo : cardFolderCountBos) {
            Integer userId = cardFolderCountBo.getBizUserId();
            //查找用户
            UserPo userPo = userDao.findUserById(userId);
            userPos.add(userPo);
        }
        userFindFastBos.setUserPos(userPos);
        return new BaseOutDTO().setData(userFindFastBos);
    }


    /**
     * @Description: 我的发布
     * @Param [userId]
     * @Author: zlh
     * @Date: 2020/4/24 15:18
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @Override
    public BaseOutDTO findArticleByUserId(UserInfo userInfo) {
        return new BaseOutDTO().setData(articleDao.selectByOwnerId(userInfo.getId()));
    }

    /**
     * @Description: 我的评论
     * @Param [userInfo]
     * @Author: zlh
     * @Date: 2020/4/24 15:30
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @Override
    public BaseOutDTO findUserCommentByUserId(UserInfo userInfo) {
        return new BaseOutDTO().setData(commentDao.selectByUserId(userInfo.getId()));
    }

    /**
     * @Description: 我的收藏
     * @Param [userInfo]
     * @Author: zlh
     * @Date: 2020/4/24 16:04
     * @Return com.gmsj.common.dto.BaseOutDTO
     */
    @Override
    public BaseOutDTO findArticleCollect(UserInfo userInfo) {
        List<CollectArticlePo> collectArticlePos = collectArticleDao.selectByUserId(userInfo.getId());
        List<ArticlePo> articlePos = new ArrayList<>();
        for (CollectArticlePo collectArticlePo : collectArticlePos){
            int articleId = collectArticlePo.getArticleId();
            ArticlePo articlePo = articleDao.selectById(articleId);
            articlePos.add(articlePo);
        }
        return new BaseOutDTO().setData(articlePos);

    }

    @Override
    public BaseOutDTO findUserCardFolder(UserInfo userInfo) {
        List<CardFolderPo> cardFolderPos = cardFolderDao.selectByUserId(userInfo.getId());
        List<UserPo> userPos = new ArrayList<>();
        for (CardFolderPo cardFolderPo : cardFolderPos){
            Integer bizUserId = cardFolderPo.getBizUserId();
            UserPo user = userDao.findUserById(bizUserId);
            userPos.add(user);
        }
        return new BaseOutDTO().setData(userPos);
    }
}
