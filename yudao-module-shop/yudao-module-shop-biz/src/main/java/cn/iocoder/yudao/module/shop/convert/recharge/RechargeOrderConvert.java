package cn.iocoder.yudao.module.shop.convert.recharge;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;

/**
 * 充值订单 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface RechargeOrderConvert {

    RechargeOrderConvert INSTANCE = Mappers.getMapper(RechargeOrderConvert.class);

    RechargeOrderDO convert(RechargeOrderCreateReqVO bean);

    RechargeOrderDO convert(RechargeOrderUpdateReqVO bean);

    RechargeOrderRespVO convert(RechargeOrderDO bean);

    List<RechargeOrderRespVO> convertList(List<RechargeOrderDO> list);

    PageResult<RechargeOrderRespVO> convertPage(PageResult<RechargeOrderDO> page);

    List<RechargeOrderExcelVO> convertList02(List<RechargeOrderDO> list);

}
