package cn.iocoder.yudao.module.blockchain.service.user;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.blockchain.controller.admin.user.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.user.UserWalletDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.blockchain.convert.user.UserWalletConvert;
import cn.iocoder.yudao.module.blockchain.dal.mysql.user.UserWalletMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.*;

/**
 * 用户钱包 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class UserWalletServiceImpl implements UserWalletService {

    @Resource
    private UserWalletMapper userWalletMapper;

    @Override
    public Long createUserWallet(UserWalletCreateReqVO createReqVO) {
        // 插入
        UserWalletDO userWallet = UserWalletConvert.INSTANCE.convert(createReqVO);
        userWalletMapper.insert(userWallet);
        // 返回
        return userWallet.getId();
    }

    @Override
    public void updateUserWallet(UserWalletUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateUserWalletExists(updateReqVO.getId());
        // 更新
        UserWalletDO updateObj = UserWalletConvert.INSTANCE.convert(updateReqVO);
        userWalletMapper.updateById(updateObj);
    }

    @Override
    public void deleteUserWallet(Long id) {
        // 校验存在
        this.validateUserWalletExists(id);
        // 删除
        userWalletMapper.deleteById(id);
    }

    private void validateUserWalletExists(Long id) {
        if (userWalletMapper.selectById(id) == null) {
            throw exception(USER_WALLET_NOT_EXISTS);
        }
    }

    @Override
    public UserWalletDO getUserWallet(Long id) {
        return userWalletMapper.selectById(id);
    }

    @Override
    public List<UserWalletDO> getUserWalletList(Collection<Long> ids) {
        return userWalletMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<UserWalletDO> getUserWalletPage(UserWalletPageReqVO pageReqVO) {
        return userWalletMapper.selectPage(pageReqVO);
    }

    @Override
    public List<UserWalletDO> getUserWalletList(UserWalletExportReqVO exportReqVO) {
        return userWalletMapper.selectList(exportReqVO);
    }

}
