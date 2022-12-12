package cn.iocoder.yudao.module.shop.dal.mysql.promotion;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.promotion.PromotionDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.*;

/**
 * 促销活动 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface PromotionMapper extends BaseMapperX<PromotionDO> {

    default PageResult<PromotionDO> selectPage(PromotionPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PromotionDO>()
                .likeIfPresent(PromotionDO::getName, reqVO.getName())
                .eqIfPresent(PromotionDO::getPromotionType, reqVO.getPromotionType())
                .eqIfPresent(PromotionDO::getStatus, reqVO.getStatus())
                .orderByDesc(PromotionDO::getId));
    }

    default List<PromotionDO> selectList(PromotionExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<PromotionDO>()
                .likeIfPresent(PromotionDO::getName, reqVO.getName())
                .eqIfPresent(PromotionDO::getPromotionType, reqVO.getPromotionType())
                .eqIfPresent(PromotionDO::getStatus, reqVO.getStatus())
                .orderByDesc(PromotionDO::getId));
    }

}
