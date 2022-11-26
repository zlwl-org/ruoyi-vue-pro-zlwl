package cn.iocoder.yudao.module.shop.convert.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;

/**
 * 门店订单 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface ShopOrderConvert {

    ShopOrderConvert INSTANCE = Mappers.getMapper(ShopOrderConvert.class);

    ShopOrderDO convert(ShopOrderCreateReqVO bean);

    ShopOrderDO convert(ShopOrderUpdateReqVO bean);

    ShopOrderRespVO convert(ShopOrderDO bean);

    List<ShopOrderRespVO> convertList(List<ShopOrderDO> list);

    PageResult<ShopOrderRespVO> convertPage(PageResult<ShopOrderDO> page);

    List<ShopOrderExcelVO> convertList02(List<ShopOrderDO> list);

}
