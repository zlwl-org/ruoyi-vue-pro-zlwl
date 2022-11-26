package cn.iocoder.yudao.module.shop.dal.mysql.order;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;

/**
 * 门店订单 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface ShopOrderMapper extends BaseMapperX<ShopOrderDO> {

    default PageResult<ShopOrderDO> selectPage(ShopOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShopOrderDO>()
                .eqIfPresent(ShopOrderDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(ShopOrderDO::getOrderType, reqVO.getOrderType())
                .eqIfPresent(ShopOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ShopOrderDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(ShopOrderDO::getPayType, reqVO.getPayType())
                .eqIfPresent(ShopOrderDO::getPayStatus, reqVO.getPayStatus())
                .betweenIfPresent(ShopOrderDO::getPayTime, reqVO.getPayTime())
                .eqIfPresent(ShopOrderDO::getCashier, reqVO.getCashier())
                .eqIfPresent(ShopOrderDO::getBranchId, reqVO.getBranchId())
                .betweenIfPresent(ShopOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopOrderDO::getId));
    }

    default List<ShopOrderDO> selectList(ShopOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShopOrderDO>()
                .eqIfPresent(ShopOrderDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(ShopOrderDO::getOrderType, reqVO.getOrderType())
                .eqIfPresent(ShopOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(ShopOrderDO::getOrderStatus, reqVO.getOrderStatus())
                .eqIfPresent(ShopOrderDO::getPayType, reqVO.getPayType())
                .eqIfPresent(ShopOrderDO::getPayStatus, reqVO.getPayStatus())
                .betweenIfPresent(ShopOrderDO::getPayTime, reqVO.getPayTime())
                .eqIfPresent(ShopOrderDO::getCashier, reqVO.getCashier())
                .eqIfPresent(ShopOrderDO::getBranchId, reqVO.getBranchId())
                .betweenIfPresent(ShopOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopOrderDO::getId));
    }

}
