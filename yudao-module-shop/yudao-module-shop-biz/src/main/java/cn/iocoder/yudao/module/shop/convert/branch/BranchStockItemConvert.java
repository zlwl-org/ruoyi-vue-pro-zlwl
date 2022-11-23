package cn.iocoder.yudao.module.shop.convert.branch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockItemDO;

/**
 * 门店出入库明细 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface BranchStockItemConvert {

    BranchStockItemConvert INSTANCE = Mappers.getMapper(BranchStockItemConvert.class);

    BranchStockItemDO convert(BranchStockItemCreateReqVO bean);

    BranchStockItemDO convert(BranchStockItemUpdateReqVO bean);

    BranchStockItemRespVO convert(BranchStockItemDO bean);

    List<BranchStockItemRespVO> convertList(List<BranchStockItemDO> list);

    PageResult<BranchStockItemRespVO> convertPage(PageResult<BranchStockItemDO> page);

    List<BranchStockItemExcelVO> convertList02(List<BranchStockItemDO> list);

}
