package cn.iocoder.yudao.module.blockchain.convert.infra;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.infra.NetDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 网络 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface NetConvert {

    NetConvert INSTANCE = Mappers.getMapper(NetConvert.class);

    NetDO convert(NetCreateReqVO bean);

    NetDO convert(NetUpdateReqVO bean);

    NetRespVO convert(NetDO bean);

    List<NetRespVO> convertList(List<NetDO> list);

    PageResult<NetRespVO> convertPage(PageResult<NetDO> page);

    List<NetExcelVO> convertList02(List<NetDO> list);

    List<NetRespSimpleVO> convertList03(List<NetDO> list);
}
