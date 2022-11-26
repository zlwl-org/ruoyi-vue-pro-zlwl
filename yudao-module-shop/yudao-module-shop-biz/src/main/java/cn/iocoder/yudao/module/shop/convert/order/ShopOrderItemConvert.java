package cn.iocoder.yudao.module.shop.convert.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderItemDO;

/**
 * 门店订单明细 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface ShopOrderItemConvert {

    ShopOrderItemConvert INSTANCE = Mappers.getMapper(ShopOrderItemConvert.class);

    ShopOrderItemDO convert(ShopOrderItemCreateReqVO bean);

    ShopOrderItemDO convert(ShopOrderItemUpdateReqVO bean);

    ShopOrderItemRespVO convert(ShopOrderItemDO bean);

    List<ShopOrderItemRespVO> convertList(List<ShopOrderItemDO> list);

    PageResult<ShopOrderItemRespVO> convertPage(PageResult<ShopOrderItemDO> page);

    List<ShopOrderItemExcelVO> convertList02(List<ShopOrderItemDO> list);

}
