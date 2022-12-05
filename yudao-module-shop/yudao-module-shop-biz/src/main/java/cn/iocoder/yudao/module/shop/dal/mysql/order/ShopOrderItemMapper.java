package cn.iocoder.yudao.module.shop.dal.mysql.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemPageReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderItemDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 门店订单明细 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface ShopOrderItemMapper extends BaseMapperX<ShopOrderItemDO> {

    default PageResult<ShopOrderItemDO> selectPage(ShopOrderItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShopOrderItemDO>()
                .eqIfPresent(ShopOrderItemDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(ShopOrderItemDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(ShopOrderItemDO::getGoodId, reqVO.getGoodId())
                .likeIfPresent(ShopOrderItemDO::getGoodName, reqVO.getGoodName())
                .eqIfPresent(ShopOrderItemDO::getGoodPrice, reqVO.getGoodPrice())
                .eqIfPresent(ShopOrderItemDO::getAmount, reqVO.getAmount())
                .betweenIfPresent(ShopOrderItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopOrderItemDO::getId));
    }

    default List<ShopOrderItemDO> selectList(ShopOrderItemExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShopOrderItemDO>()
                .eqIfPresent(ShopOrderItemDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(ShopOrderItemDO::getOrderId, reqVO.getOrderId())
                .eqIfPresent(ShopOrderItemDO::getGoodId, reqVO.getGoodId())
                .likeIfPresent(ShopOrderItemDO::getGoodName, reqVO.getGoodName())
                .eqIfPresent(ShopOrderItemDO::getGoodPrice, reqVO.getGoodPrice())
                .eqIfPresent(ShopOrderItemDO::getAmount, reqVO.getAmount())
                .betweenIfPresent(ShopOrderItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopOrderItemDO::getId));
    }

    default List<ShopOrderItemDO> selectListByOrderId(Long orderId) {
        return selectList(new LambdaQueryWrapperX<ShopOrderItemDO>()
                .eq(ShopOrderItemDO::getOrderId, orderId));
    }
}
