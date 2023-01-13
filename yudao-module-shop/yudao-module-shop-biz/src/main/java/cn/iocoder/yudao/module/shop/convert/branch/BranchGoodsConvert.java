package cn.iocoder.yudao.module.shop.convert.branch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;

/**
 * 门店商品 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface BranchGoodsConvert {

    BranchGoodsConvert INSTANCE = Mappers.getMapper(BranchGoodsConvert.class);

    BranchGoodsDO convert(BranchGoodsCreateReqVO bean);

    BranchGoodsDO convert(BranchGoodsUpdateReqVO bean);

    BranchGoodsRespVO convert(BranchGoodsDO bean);

    List<BranchGoodsRespVO> convertList(List<BranchGoodsDO> list);

    PageResult<BranchGoodsRespVO> convertPage(PageResult<BranchGoodsDO> page);

    List<BranchGoodsExcelVO> convertList02(List<BranchGoodsDO> list);

}
