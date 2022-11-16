package cn.iocoder.yudao.module.shop.dal.mysql.recharge;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderPageReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 充值订单 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface RechargeOrderMapper extends BaseMapperX<RechargeOrderDO> {

    default PageResult<RechargeOrderDO> selectPage(RechargeOrderPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RechargeOrderDO>()
                .eqIfPresent(RechargeOrderDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(RechargeOrderDO::getAmount, reqVO.getAmount())
                .eqIfPresent(RechargeOrderDO::getRechargeId, reqVO.getRechargeId())
                .likeIfPresent(RechargeOrderDO::getRechargeName, reqVO.getRechargeName())
                .eqIfPresent(RechargeOrderDO::getPayType, reqVO.getPayType())
                .eqIfPresent(RechargeOrderDO::getPayStatus, reqVO.getPayStatus())
                .betweenIfPresent(RechargeOrderDO::getPayTime, reqVO.getPayTime())
                .betweenIfPresent(RechargeOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RechargeOrderDO::getId));
    }

    default List<RechargeOrderDO> selectList(RechargeOrderExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RechargeOrderDO>()
                .eqIfPresent(RechargeOrderDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(RechargeOrderDO::getAmount, reqVO.getAmount())
                .eqIfPresent(RechargeOrderDO::getRechargeId, reqVO.getRechargeId())
                .likeIfPresent(RechargeOrderDO::getRechargeName, reqVO.getRechargeName())
                .eqIfPresent(RechargeOrderDO::getPayType, reqVO.getPayType())
                .eqIfPresent(RechargeOrderDO::getPayStatus, reqVO.getPayStatus())
                .betweenIfPresent(RechargeOrderDO::getPayTime, reqVO.getPayTime())
                .betweenIfPresent(RechargeOrderDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RechargeOrderDO::getId));
    }

}
