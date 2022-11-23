package cn.iocoder.yudao.module.shop.dal.mysql.branch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;

/**
 * 门店出入库 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface BranchStockMapper extends BaseMapperX<BranchStockDO> {

    default PageResult<BranchStockDO> selectPage(BranchStockPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BranchStockDO>()
                .eqIfPresent(BranchStockDO::getBranchId, reqVO.getBranchId())
                .betweenIfPresent(BranchStockDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BranchStockDO::getId));
    }

    default List<BranchStockDO> selectList(BranchStockExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BranchStockDO>()
                .eqIfPresent(BranchStockDO::getBranchId, reqVO.getBranchId())
                .betweenIfPresent(BranchStockDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BranchStockDO::getId));
    }

}
