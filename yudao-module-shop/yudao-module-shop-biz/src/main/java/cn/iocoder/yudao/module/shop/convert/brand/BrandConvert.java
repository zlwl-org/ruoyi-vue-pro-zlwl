package cn.iocoder.yudao.module.shop.convert.brand;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.brand.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.brand.BrandDO;

/**
 * 商品品牌 Convert
 *
 * @author ZLWL
 */
@Mapper
public interface BrandConvert {

    BrandConvert INSTANCE = Mappers.getMapper(BrandConvert.class);

    BrandDO convert(BrandCreateReqVO bean);

    BrandDO convert(BrandUpdateReqVO bean);

    BrandRespVO convert(BrandDO bean);

    List<BrandRespVO> convertList(List<BrandDO> list);

    PageResult<BrandRespVO> convertPage(PageResult<BrandDO> page);

    List<BrandExcelVO> convertList02(List<BrandDO> list);

}
