package cn.iocoder.yudao.module.blockchain.convert.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockExcelVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockRespVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthBlockDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.web3j.protocol.core.methods.response.EthBlock;

import java.util.List;

/**
 * Eth区块数据 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface EthBlockConvert {

    EthBlockConvert INSTANCE = Mappers.getMapper(EthBlockConvert.class);

    EthBlockDO convert(EthBlockCreateReqVO bean);

    EthBlockDO convert(EthBlockUpdateReqVO bean);

    EthBlockRespVO convert(EthBlockDO bean);

    List<EthBlockRespVO> convertList(List<EthBlockDO> list);

    PageResult<EthBlockRespVO> convertPage(PageResult<EthBlockDO> page);

    List<EthBlockExcelVO> convertList02(List<EthBlockDO> list);

    EthBlockDO convert(EthBlock.Block result);
}
