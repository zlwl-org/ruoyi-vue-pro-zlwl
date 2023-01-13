package cn.iocoder.yudao.module.blockchain.convert.eth;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthMainNetAddressDO;

/**
 * 以太坊主网地址 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface EthMainNetAddressConvert {

    EthMainNetAddressConvert INSTANCE = Mappers.getMapper(EthMainNetAddressConvert.class);

    EthMainNetAddressDO convert(EthMainNetAddressCreateReqVO bean);

    EthMainNetAddressDO convert(EthMainNetAddressUpdateReqVO bean);

    EthMainNetAddressRespVO convert(EthMainNetAddressDO bean);

    List<EthMainNetAddressRespVO> convertList(List<EthMainNetAddressDO> list);

    PageResult<EthMainNetAddressRespVO> convertPage(PageResult<EthMainNetAddressDO> page);

    List<EthMainNetAddressExcelVO> convertList02(List<EthMainNetAddressDO> list);

}
