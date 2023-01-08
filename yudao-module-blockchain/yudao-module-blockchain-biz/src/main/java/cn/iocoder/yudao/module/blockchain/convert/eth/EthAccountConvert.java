package cn.iocoder.yudao.module.blockchain.convert.eth;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;

/**
 * 以太坊账户 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface EthAccountConvert {

    EthAccountConvert INSTANCE = Mappers.getMapper(EthAccountConvert.class);

    EthAccountDO convert(EthAccountCreateReqVO bean);

    EthAccountDO convert(EthAccountUpdateReqVO bean);

    EthAccountRespVO convert(EthAccountDO bean);

    List<EthAccountRespVO> convertList(List<EthAccountDO> list);

    PageResult<EthAccountRespVO> convertPage(PageResult<EthAccountDO> page);

    List<EthAccountExcelVO> convertList02(List<EthAccountDO> list);

}
