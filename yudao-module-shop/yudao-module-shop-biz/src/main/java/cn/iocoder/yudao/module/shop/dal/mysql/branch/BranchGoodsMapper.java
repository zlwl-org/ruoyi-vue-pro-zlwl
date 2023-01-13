package cn.iocoder.yudao.module.shop.dal.mysql.branch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.BranchGoodsExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.BranchGoodsPageReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    default BranchGoodsDO selectByBranchIdAndProductId(Long branchId, Long productId) {
        return selectOne(new LambdaQueryWrapperX<BranchGoodsDO>().eq(BranchGoodsDO::getBranchId, branchId)
                .eq(BranchGoodsDO::getProductId, productId));
    }

    @Update("update shop_branch_goods set stock = stock + #{amount} where id = #{id} and stock + #{amount}>=0")
    int updateGoodStock(@Param("id") Long id,@Param("amount") int amount);
}
