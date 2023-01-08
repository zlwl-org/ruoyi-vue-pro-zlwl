package cn.iocoder.yudao.module.blockchain.dal.mysql.eth;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;

/**
 * 以太坊账户 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface EthAccountMapper extends BaseMapperX<EthAccountDO> {

    default PageResult<EthAccountDO> selectPage(EthAccountPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EthAccountDO>()
                .likeIfPresent(EthAccountDO::getName, reqVO.getName())
                .eqIfPresent(EthAccountDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EthAccountDO::getMnemonic, reqVO.getMnemonic())
                .eqIfPresent(EthAccountDO::getPrivateKey, reqVO.getPrivateKey())
                .eqIfPresent(EthAccountDO::getOwned, reqVO.getOwned())
                .betweenIfPresent(EthAccountDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EthAccountDO::getId));
    }

    default List<EthAccountDO> selectList(EthAccountExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EthAccountDO>()
                .likeIfPresent(EthAccountDO::getName, reqVO.getName())
                .eqIfPresent(EthAccountDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EthAccountDO::getMnemonic, reqVO.getMnemonic())
                .eqIfPresent(EthAccountDO::getPrivateKey, reqVO.getPrivateKey())
                .eqIfPresent(EthAccountDO::getOwned, reqVO.getOwned())
                .betweenIfPresent(EthAccountDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EthAccountDO::getId));
    }

}
