package cn.iocoder.yudao.module.blockchain.service.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.user.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.user.UserWalletDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 用户钱包 Service 接口
 *
 * @author ruanzh
 */
public interface UserWalletService {

    /**
     * 创建用户钱包
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createUserWallet(@Valid UserWalletCreateReqVO createReqVO);

    /**
     * 更新用户钱包
     *
     * @param updateReqVO 更新信息
     */
    void updateUserWallet(@Valid UserWalletUpdateReqVO updateReqVO);

    /**
     * 删除用户钱包
     *
     * @param id 编号
     */
    void deleteUserWallet(Long id);

    /**
     * 获得用户钱包
     *
     * @param id 编号
     * @return 用户钱包
     */
    UserWalletDO getUserWallet(Long id);

    /**
     * 获得用户钱包列表
     *
     * @param ids 编号
     * @return 用户钱包列表
     */
    List<UserWalletDO> getUserWalletList(Collection<Long> ids);

    /**
     * 获得用户钱包分页
     *
     * @param pageReqVO 分页查询
     * @return 用户钱包分页
     */
    PageResult<UserWalletDO> getUserWalletPage(UserWalletPageReqVO pageReqVO);

    /**
     * 获得用户钱包列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 用户钱包列表
     */
    List<UserWalletDO> getUserWalletList(UserWalletExportReqVO exportReqVO);

    Long importUserWallet(@Valid UserWalletImportReqVO createReqVO);
}
