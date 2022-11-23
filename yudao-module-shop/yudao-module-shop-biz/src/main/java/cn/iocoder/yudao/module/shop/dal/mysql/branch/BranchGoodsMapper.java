package cn.iocoder.yudao.module.shop.dal.mysql.branch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;

/**
 * 门店商品 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface BranchGoodsMapper extends BaseMapperX<BranchGoodsDO> {

    default PageResult<BranchGoodsDO> selectPage(BranchGoodsPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BranchGoodsDO>()
                .likeIfPresent(BranchGoodsDO::getName, reqVO.getName())
                .eqIfPresent(BranchGoodsDO::getPrice, reqVO.getPrice())
                .eqIfPresent(BranchGoodsDO::getProductId, reqVO.getProductId())
                .eqIfPresent(BranchGoodsDO::getBranchId, reqVO.getBranchId())
                .eqIfPresent(BranchGoodsDO::getBrandId, reqVO.getBrandId())
                .betweenIfPresent(BranchGoodsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BranchGoodsDO::getId));
    }

    default List<BranchGoodsDO> selectList(BranchGoodsExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BranchGoodsDO>()
                .likeIfPresent(BranchGoodsDO::getName, reqVO.getName())
                .eqIfPresent(BranchGoodsDO::getPrice, reqVO.getPrice())
                .eqIfPresent(BranchGoodsDO::getProductId, reqVO.getProductId())
                .eqIfPresent(BranchGoodsDO::getBranchId, reqVO.getBranchId())
                .eqIfPresent(BranchGoodsDO::getBrandId, reqVO.getBrandId())
                .betweenIfPresent(BranchGoodsDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BranchGoodsDO::getId));
    }

}
