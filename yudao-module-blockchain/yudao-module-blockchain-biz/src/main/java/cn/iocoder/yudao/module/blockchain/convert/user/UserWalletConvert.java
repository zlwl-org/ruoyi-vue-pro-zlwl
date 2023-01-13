package cn.iocoder.yudao.module.blockchain.convert.user;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.blockchain.controller.admin.user.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.user.UserWalletDO;

/**
 * 用户钱包 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface UserWalletConvert {

    UserWalletConvert INSTANCE = Mappers.getMapper(UserWalletConvert.class);

    UserWalletDO convert(UserWalletCreateReqVO bean);

    UserWalletDO convert(UserWalletUpdateReqVO bean);

    UserWalletRespVO convert(UserWalletDO bean);

    List<UserWalletRespVO> convertList(List<UserWalletDO> list);

    PageResult<UserWalletRespVO> convertPage(PageResult<UserWalletDO> page);

    List<UserWalletExcelVO> convertList02(List<UserWalletDO> list);

}
