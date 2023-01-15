package cn.iocoder.yudao.module.blockchain.dal.mysql.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockPageReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthBlockDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Eth区块数据 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface EthBlockMapper extends BaseMapperX<EthBlockDO> {

    default PageResult<EthBlockDO> selectPage(EthBlockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EthBlockDO>()
                .eqIfPresent(EthBlockDO::getNumber, reqVO.getNumber())
                .eqIfPresent(EthBlockDO::getHash, reqVO.getHash())
                .eqIfPresent(EthBlockDO::getParentHash, reqVO.getParentHash())
                .eqIfPresent(EthBlockDO::getNonce, reqVO.getNonce())
                .eqIfPresent(EthBlockDO::getSha3uncles, reqVO.getSha3uncles())
                .eqIfPresent(EthBlockDO::getLogsBloom, reqVO.getLogsBloom())
                .eqIfPresent(EthBlockDO::getTransactionsRoot, reqVO.getTransactionsRoot())
                .eqIfPresent(EthBlockDO::getStateRoot, reqVO.getStateRoot())
                .eqIfPresent(EthBlockDO::getReceiptsRoot, reqVO.getReceiptsRoot())
                .eqIfPresent(EthBlockDO::getAuthor, reqVO.getAuthor())
                .eqIfPresent(EthBlockDO::getMiner, reqVO.getMiner())
                .eqIfPresent(EthBlockDO::getMixHash, reqVO.getMixHash())
                .eqIfPresent(EthBlockDO::getDifficulty, reqVO.getDifficulty())
                .eqIfPresent(EthBlockDO::getTotalDifficulty, reqVO.getTotalDifficulty())
                .eqIfPresent(EthBlockDO::getSize, reqVO.getSize())
                .eqIfPresent(EthBlockDO::getGasLimit, reqVO.getGasLimit())
                .eqIfPresent(EthBlockDO::getGasUsed, reqVO.getGasUsed())
                .eqIfPresent(EthBlockDO::getTimestamp, reqVO.getTimestamp())
                .eqIfPresent(EthBlockDO::getBaseFeePerGas, reqVO.getBaseFeePerGas())
                .eqIfPresent(EthBlockDO::getDone, reqVO.getDone())
                .eqIfPresent(EthBlockDO::getInfo, reqVO.getInfo())
                .betweenIfPresent(EthBlockDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EthBlockDO::getId));
    }

    default List<EthBlockDO> selectList(EthBlockExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EthBlockDO>()
                .eqIfPresent(EthBlockDO::getNumber, reqVO.getNumber())
                .eqIfPresent(EthBlockDO::getHash, reqVO.getHash())
                .eqIfPresent(EthBlockDO::getParentHash, reqVO.getParentHash())
                .eqIfPresent(EthBlockDO::getNonce, reqVO.getNonce())
                .eqIfPresent(EthBlockDO::getSha3uncles, reqVO.getSha3uncles())
                .eqIfPresent(EthBlockDO::getLogsBloom, reqVO.getLogsBloom())
                .eqIfPresent(EthBlockDO::getTransactionsRoot, reqVO.getTransactionsRoot())
                .eqIfPresent(EthBlockDO::getStateRoot, reqVO.getStateRoot())
                .eqIfPresent(EthBlockDO::getReceiptsRoot, reqVO.getReceiptsRoot())
                .eqIfPresent(EthBlockDO::getAuthor, reqVO.getAuthor())
                .eqIfPresent(EthBlockDO::getMiner, reqVO.getMiner())
                .eqIfPresent(EthBlockDO::getMixHash, reqVO.getMixHash())
                .eqIfPresent(EthBlockDO::getDifficulty, reqVO.getDifficulty())
                .eqIfPresent(EthBlockDO::getTotalDifficulty, reqVO.getTotalDifficulty())
                .eqIfPresent(EthBlockDO::getSize, reqVO.getSize())
                .eqIfPresent(EthBlockDO::getGasLimit, reqVO.getGasLimit())
                .eqIfPresent(EthBlockDO::getGasUsed, reqVO.getGasUsed())
                .eqIfPresent(EthBlockDO::getTimestamp, reqVO.getTimestamp())
                .eqIfPresent(EthBlockDO::getBaseFeePerGas, reqVO.getBaseFeePerGas())
                .eqIfPresent(EthBlockDO::getDone, reqVO.getDone())
                .eqIfPresent(EthBlockDO::getInfo, reqVO.getInfo())
                .betweenIfPresent(EthBlockDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EthBlockDO::getId));
    }

    default EthBlockDO selectLatest() {
        return selectOne(new LambdaQueryWrapperX<EthBlockDO>().orderByDesc(BaseDO::getCreateTime));
    }
}
