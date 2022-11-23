package cn.iocoder.yudao.module.shop.convert.branch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockDO;

/**
 * 门店出入库 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface BranchStockConvert {

    BranchStockConvert INSTANCE = Mappers.getMapper(BranchStockConvert.class);

    BranchStockDO convert(BranchStockCreateReqVO bean);

    BranchStockDO convert(BranchStockUpdateReqVO bean);

    BranchStockRespVO convert(BranchStockDO bean);

    List<BranchStockRespVO> convertList(List<BranchStockDO> list);

    PageResult<BranchStockRespVO> convertPage(PageResult<BranchStockDO> page);

    List<BranchStockExcelVO> convertList02(List<BranchStockDO> list);

}
