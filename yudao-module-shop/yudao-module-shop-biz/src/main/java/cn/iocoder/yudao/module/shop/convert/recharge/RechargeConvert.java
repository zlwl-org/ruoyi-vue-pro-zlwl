package cn.iocoder.yudao.module.shop.convert.recharge;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;

/**
 * 会员充值套餐 Convert
 *
 * @author ZLWL
 */
@Mapper
public interface RechargeConvert {

    RechargeConvert INSTANCE = Mappers.getMapper(RechargeConvert.class);

    RechargeDO convert(RechargeCreateReqVO bean);

    RechargeDO convert(RechargeUpdateReqVO bean);

    RechargeRespVO convert(RechargeDO bean);

    List<RechargeRespVO> convertList(List<RechargeDO> list);

    PageResult<RechargeRespVO> convertPage(PageResult<RechargeDO> page);

    List<RechargeExcelVO> convertList02(List<RechargeDO> list);

}
