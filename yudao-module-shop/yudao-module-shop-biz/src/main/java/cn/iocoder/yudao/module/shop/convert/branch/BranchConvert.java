package cn.iocoder.yudao.module.shop.convert.branch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchDO;

/**
 * 门店 Convert
 *
 * @author ZLWL
 */
@Mapper
public interface BranchConvert {

    BranchConvert INSTANCE = Mappers.getMapper(BranchConvert.class);

    BranchDO convert(BranchCreateReqVO bean);

    BranchDO convert(BranchUpdateReqVO bean);

    BranchRespVO convert(BranchDO bean);

    List<BranchRespVO> convertList(List<BranchDO> list);

    PageResult<BranchRespVO> convertPage(PageResult<BranchDO> page);

    List<BranchExcelVO> convertList02(List<BranchDO> list);

    List<BranchSimpleRespVO> convertList03(List<BranchDO> list);

}
