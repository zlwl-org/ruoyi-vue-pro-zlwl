package cn.iocoder.yudao.module.shop.dal.mysql.recharge;

import cn.hutool.core.date.DateUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargePageReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 充值活动 Mapper
 *
 * @author ZLWL
 */
@Mapper
public interface RechargeMapper extends BaseMapperX<RechargeDO> {


    default PageResult<RechargeDO> selectPage(RechargePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RechargeDO>()
                .likeIfPresent(RechargeDO::getName, reqVO.getName())
                .eqIfPresent(RechargeDO::getPrice, reqVO.getPrice())
                .eqIfPresent(RechargeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RechargeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RechargeDO::getId));
    }

    default List<RechargeDO> selectList(RechargeExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<RechargeDO>()
                .likeIfPresent(RechargeDO::getName, reqVO.getName())
                .eqIfPresent(RechargeDO::getPrice, reqVO.getPrice())
                .eqIfPresent(RechargeDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(RechargeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RechargeDO::getId));
    }

    default List<RechargeDO> selectActiveList(){
        return selectList(new LambdaQueryWrapperX<RechargeDO>()
                .eq(RechargeDO::getStatus, 0));
    }

    default List<RechargeDO> selectTodayOrder() {
        return selectList(new LambdaQueryWrapperX<RechargeDO>().between(BaseDO::getCreateTime, DateUtil.today(), DateUtil.today() + " " +
                "23:59:59"));
    }

}
