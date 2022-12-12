package cn.iocoder.yudao.module.shop.convert.promotion;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.promotion.PromotionDO;

/**
 * 促销活动 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface PromotionConvert {

    PromotionConvert INSTANCE = Mappers.getMapper(PromotionConvert.class);

    PromotionDO convert(PromotionCreateReqVO bean);

    PromotionDO convert(PromotionUpdateReqVO bean);

    PromotionRespVO convert(PromotionDO bean);

    List<PromotionRespVO> convertList(List<PromotionDO> list);

    PageResult<PromotionRespVO> convertPage(PageResult<PromotionDO> page);

    List<PromotionExcelVO> convertList02(List<PromotionDO> list);

}
