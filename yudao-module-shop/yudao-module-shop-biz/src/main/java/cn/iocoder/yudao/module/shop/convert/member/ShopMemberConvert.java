package cn.iocoder.yudao.module.shop.convert.member;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;

/**
 * 会员 Convert
 *
 * @author ZLWL
 */
@Mapper
public interface ShopMemberConvert {

    ShopMemberConvert INSTANCE = Mappers.getMapper(ShopMemberConvert.class);

    ShopMemberDO convert(ShopMemberCreateReqVO bean);

    ShopMemberDO convert(ShopMemberUpdateReqVO bean);

    ShopMemberRespVO convert(ShopMemberDO bean);

    List<ShopMemberRespVO> convertList(List<ShopMemberDO> list);

    PageResult<ShopMemberRespVO> convertPage(PageResult<ShopMemberDO> page);

    List<ShopMemberExcelVO> convertList02(List<ShopMemberDO> list);

    List<ShopMemberSimpleRespVO> convertList03(List<ShopMemberDO> list);
}
