package cn.iocoder.yudao.module.shop.dal.mysql.recharge;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;

/**
 * 充值订单 Mapper
 *
 * @author ZLWL
 */
@Mapper
public interface RechargeOrderMapper extends BaseMapperX<RechargeOrderDO> {

    default PageResult<RechargeOrderDO> selectPage(RechargeOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RechargeOrderDO>()
                .eqIfPresent(RechargeOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(RechargeOrderDO::getOutTradeNo, reqVO.getOutTradeNo())
                .eqIfPresent(RechargeOrderDO::getRechargeAmount, reqVO.getRechargeAmount())
                .eqIfPresent(RechargeOrderDO::getPayType, reqVO.getPayType())
                .eqIfPresent(RechargeOrderDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RechargeOrderDO::getPayTime, reqVO.getPayTime())
                .eqIfPresent(RechargeOrderDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(RechargeOrderDO::getOrderFrom, reqVO.getOrderFrom())
                .likeIfPresent(RechargeOrderDO::getOrderFromName, reqVO.getOrderFromName())
                .betweenIfPresent(RechargeOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RechargeOrderDO::getId));
    }

    default List<RechargeOrderDO> selectList(RechargeOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RechargeOrderDO>()
                .eqIfPresent(RechargeOrderDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(RechargeOrderDO::getOutTradeNo, reqVO.getOutTradeNo())
                .eqIfPresent(RechargeOrderDO::getRechargeAmount, reqVO.getRechargeAmount())
                .eqIfPresent(RechargeOrderDO::getPayType, reqVO.getPayType())
                .eqIfPresent(RechargeOrderDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RechargeOrderDO::getPayTime, reqVO.getPayTime())
                .eqIfPresent(RechargeOrderDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(RechargeOrderDO::getOrderFrom, reqVO.getOrderFrom())
                .likeIfPresent(RechargeOrderDO::getOrderFromName, reqVO.getOrderFromName())
                .betweenIfPresent(RechargeOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RechargeOrderDO::getId));
    }

}
