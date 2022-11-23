package cn.iocoder.yudao.module.shop.dal.mysql.branch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockItemDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;

/**
 * 门店出入库明细 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface BranchStockItemMapper extends BaseMapperX<BranchStockItemDO> {

    default PageResult<BranchStockItemDO> selectPage(BranchStockItemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BranchStockItemDO>()
                .eqIfPresent(BranchStockItemDO::getStockId, reqVO.getStockId())
                .eqIfPresent(BranchStockItemDO::getType, reqVO.getType())
                .eqIfPresent(BranchStockItemDO::getBranchId, reqVO.getBranchId())
                .eqIfPresent(BranchStockItemDO::getProductId, reqVO.getProductId())
                .eqIfPresent(BranchStockItemDO::getAmount, reqVO.getAmount())
                .betweenIfPresent(BranchStockItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BranchStockItemDO::getId));
    }

    default List<BranchStockItemDO> selectList(BranchStockItemExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BranchStockItemDO>()
                .eqIfPresent(BranchStockItemDO::getStockId, reqVO.getStockId())
                .eqIfPresent(BranchStockItemDO::getType, reqVO.getType())
                .eqIfPresent(BranchStockItemDO::getBranchId, reqVO.getBranchId())
                .eqIfPresent(BranchStockItemDO::getProductId, reqVO.getProductId())
                .eqIfPresent(BranchStockItemDO::getAmount, reqVO.getAmount())
                .betweenIfPresent(BranchStockItemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BranchStockItemDO::getId));
    }

}
