package cn.iocoder.yudao.module.shop.convert.member;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogExcelVO;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogRespVO;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogUpdateReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.MemberAccountLogDO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 会员账户流水 Convert
 *
 * @author ZLWL
 */
@Mapper
public interface MemberAccountLogConvert {

    MemberAccountLogConvert INSTANCE = Mappers.getMapper(MemberAccountLogConvert.class);

    MemberAccountLogDO convert(MemberAccountLogCreateReqVO bean);

    MemberAccountLogDO convert(MemberAccountLogUpdateReqVO bean);

    MemberAccountLogRespVO convert(MemberAccountLogDO bean);

    List<MemberAccountLogRespVO> convertList(List<MemberAccountLogDO> list);

    PageResult<MemberAccountLogRespVO> convertPage(PageResult<MemberAccountLogDO> page);

    List<MemberAccountLogExcelVO> convertList02(List<MemberAccountLogDO> list);

}
