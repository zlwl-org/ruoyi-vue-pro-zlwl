package cn.iocoder.yudao.module.blockchain.dal.mysql.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressPageReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthMainNetAddressDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 以太坊主网地址 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface EthMainNetAddressMapper extends BaseMapperX<EthMainNetAddressDO> {

    default PageResult<EthMainNetAddressDO> selectPage(EthMainNetAddressPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EthMainNetAddressDO>()
                .eqIfPresent(EthMainNetAddressDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EthMainNetAddressDO::getTags, reqVO.getTags())
                .orderByDesc(EthMainNetAddressDO::getId));
    }

    default List<EthMainNetAddressDO> selectList(EthMainNetAddressExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EthMainNetAddressDO>()
                .eqIfPresent(EthMainNetAddressDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EthMainNetAddressDO::getTags, reqVO.getTags())
                .orderByDesc(EthMainNetAddressDO::getId));
    }

    default EthMainNetAddressDO selectByLatest() {
        return selectOne(new LambdaQueryWrapperX<EthMainNetAddressDO>().orderByDesc(BaseDO::getCreateTime).last("limit 1"));
    }
}
