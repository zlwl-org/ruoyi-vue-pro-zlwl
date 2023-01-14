package cn.iocoder.yudao.module.blockchain.service.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.user.vo.*;
import cn.iocoder.yudao.module.blockchain.convert.user.UserWalletConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.infra.NetDO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.user.UserWalletDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.user.UserWalletMapper;
import cn.iocoder.yudao.module.blockchain.service.account.Account;
import cn.iocoder.yudao.module.blockchain.service.account.AccountService;
import cn.iocoder.yudao.module.blockchain.service.infra.NetService;
import cn.iocoder.yudao.module.blockchain.util.EthUtil;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.NET_NOT_EXISTS;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.USER_WALLET_NOT_EXISTS;

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

    @Resource
    private AccountService accountService;

    @Resource
    private NetService netService;


    @Override
    public Long createUserWallet(UserWalletCreateReqVO createReqVO) {

        NetDO net = netService.getNet(createReqVO.getNet());
        if (net == null) {
            throw exception(NET_NOT_EXISTS);
        }

        Account account = accountService.generate(net.getType(), null, true);

        // 插入
        UserWalletDO userWallet = new UserWalletDO();
        userWallet.setName(createReqVO.getName());
        userWallet.setAddress(account.getAddress());
        userWallet.setNet(net.getName());
        userWallet.setSymbol(net.getSymbol());
        userWallet.setAddress(account.getAddress());

        Web3j web3j = Web3j.build(new HttpService(net.getPublicRpc()));
        try {
            BigDecimal balance = EthUtil.getEth(web3j, account.getAddress(), 18);
            userWallet.setBalance(balance);
        } catch (IOException e) {

        }
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

    @Override
    public Long importUserWallet(@Valid UserWalletImportReqVO createReqVO) {
        NetDO net = netService.getNet(createReqVO.getNet());
        if (net == null) {
            throw exception(NET_NOT_EXISTS);
        }

        Account account = accountService.generate(net.getType(), createReqVO.getMnemonic(), true);

        // 插入
        UserWalletDO userWallet = new UserWalletDO();
        userWallet.setName(createReqVO.getName());
        userWallet.setAddress(account.getAddress());
        userWallet.setNet(net.getName());
        userWallet.setSymbol(net.getSymbol());
        userWallet.setAddress(account.getAddress());

        Web3j web3j = Web3j.build(new HttpService(net.getPublicRpc()));
        try {
            BigDecimal balance = EthUtil.getEth(web3j, account.getAddress(), 18);
            userWallet.setBalance(balance);
        } catch (IOException e) {

        }
        userWalletMapper.insert(userWallet);
        // 返回
        return userWallet.getId();
    }

}
