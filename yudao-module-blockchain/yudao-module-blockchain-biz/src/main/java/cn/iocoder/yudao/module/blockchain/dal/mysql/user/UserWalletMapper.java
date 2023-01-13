package cn.iocoder.yudao.module.blockchain.dal.mysql.user;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.user.UserWalletDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.blockchain.controller.admin.user.vo.*;

/**
 * 用户钱包 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface UserWalletMapper extends BaseMapperX<UserWalletDO> {

    default PageResult<UserWalletDO> selectPage(UserWalletPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<UserWalletDO>()
                .eqIfPresent(UserWalletDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserWalletDO::getName, reqVO.getName())
                .eqIfPresent(UserWalletDO::getAddress, reqVO.getAddress())
                .eqIfPresent(UserWalletDO::getNet, reqVO.getNet())
                .eqIfPresent(UserWalletDO::getSymbol, reqVO.getSymbol())
                .eqIfPresent(UserWalletDO::getBalance, reqVO.getBalance())
                .betweenIfPresent(UserWalletDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserWalletDO::getId));
    }

    default List<UserWalletDO> selectList(UserWalletExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<UserWalletDO>()
                .eqIfPresent(UserWalletDO::getUserId, reqVO.getUserId())
                .likeIfPresent(UserWalletDO::getName, reqVO.getName())
                .eqIfPresent(UserWalletDO::getAddress, reqVO.getAddress())
                .eqIfPresent(UserWalletDO::getNet, reqVO.getNet())
                .eqIfPresent(UserWalletDO::getSymbol, reqVO.getSymbol())
                .eqIfPresent(UserWalletDO::getBalance, reqVO.getBalance())
                .betweenIfPresent(UserWalletDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(UserWalletDO::getId));
    }

}
