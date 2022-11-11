package cn.iocoder.yudao.module.shop.dal.mysql.recharge;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;

/**
 * 会员充值套餐 Mapper
 *
 * @author ZLWL
 */
@Mapper
public interface RechargeMapper extends BaseMapperX<RechargeDO> {

    default PageResult<RechargeDO> selectPage(RechargePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RechargeDO>()
                .likeIfPresent(RechargeDO::getName, reqVO.getName())
                .eqIfPresent(RechargeDO::getFaceValue, reqVO.getFaceValue())
                .eqIfPresent(RechargeDO::getPrice, reqVO.getPrice())
                .eqIfPresent(RechargeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RechargeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RechargeDO::getId));
    }

    default List<RechargeDO> selectList(RechargeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RechargeDO>()
                .likeIfPresent(RechargeDO::getName, reqVO.getName())
                .eqIfPresent(RechargeDO::getFaceValue, reqVO.getFaceValue())
                .eqIfPresent(RechargeDO::getPrice, reqVO.getPrice())
                .eqIfPresent(RechargeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RechargeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RechargeDO::getId));
    }

}
