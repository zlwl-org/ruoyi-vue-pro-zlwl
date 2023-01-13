package cn.iocoder.yudao.module.blockchain.dal.mysql.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountPageReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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
                .eqIfPresent(EthAccountDO::getNet, reqVO.getNet())
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
                .eqIfPresent(EthAccountDO::getNet, reqVO.getNet())
                .orderByDesc(EthAccountDO::getId));
    }

    @Select("select * from blockchain_eth_account where owned = false and " +
            "(net is null or net not like concat('%', #{symbol}, ';%')) " +
            "limit 100")
    List<EthAccountDO> selectListLimit100(@Param("symbol")String symbol);

    @Update("update blockchain_eth_account " +
            "set net = concat(coalesce(net, ''),  #{netSymbol}, ';') " +
            "where address = #{address}")
    int updateNet(@Param("address")String address, @Param("netSymbol")String netSymbol);
}
