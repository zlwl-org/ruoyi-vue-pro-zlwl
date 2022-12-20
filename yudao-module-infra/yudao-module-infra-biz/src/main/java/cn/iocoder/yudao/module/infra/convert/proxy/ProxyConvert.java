package cn.iocoder.yudao.module.infra.convert.proxy;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.infra.controller.admin.proxy.vo.*;
import cn.iocoder.yudao.module.infra.dal.dataobject.proxy.ProxyDO;

/**
 * 网络代理 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface ProxyConvert {

    ProxyConvert INSTANCE = Mappers.getMapper(ProxyConvert.class);

    ProxyDO convert(ProxyCreateReqVO bean);

    ProxyDO convert(ProxyUpdateReqVO bean);

    ProxyRespVO convert(ProxyDO bean);

    List<ProxyRespVO> convertList(List<ProxyDO> list);

    PageResult<ProxyRespVO> convertPage(PageResult<ProxyDO> page);

    List<ProxyExcelVO> convertList02(List<ProxyDO> list);

}
