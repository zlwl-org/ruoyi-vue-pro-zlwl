package cn.iocoder.yudao.module.shop.dal.mysql.member;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;

/**
 * 会员 Mapper
 *
 * @author ZLWL
 */
@Mapper
public interface ShopMemberMapper extends BaseMapperX<ShopMemberDO> {

    default PageResult<ShopMemberDO> selectPage(ShopMemberPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ShopMemberDO>()
                .likeIfPresent(ShopMemberDO::getName, reqVO.getName())
                .likeIfPresent(ShopMemberDO::getNickname, reqVO.getNickname())
                .eqIfPresent(ShopMemberDO::getMobile, reqVO.getMobile())
                .eqIfPresent(ShopMemberDO::getSalesman, reqVO.getSalesman())
                .eqIfPresent(ShopMemberDO::getType, reqVO.getType())
                .eqIfPresent(ShopMemberDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ShopMemberDO::getBranchId, reqVO.getBranchId())
                .betweenIfPresent(ShopMemberDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopMemberDO::getId));
    }

    default List<ShopMemberDO> selectList(ShopMemberExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<ShopMemberDO>()
                .likeIfPresent(ShopMemberDO::getName, reqVO.getName())
                .likeIfPresent(ShopMemberDO::getNickname, reqVO.getNickname())
                .eqIfPresent(ShopMemberDO::getMobile, reqVO.getMobile())
                .eqIfPresent(ShopMemberDO::getSalesman, reqVO.getSalesman())
                .eqIfPresent(ShopMemberDO::getType, reqVO.getType())
                .eqIfPresent(ShopMemberDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ShopMemberDO::getBranchId, reqVO.getBranchId())
                .betweenIfPresent(ShopMemberDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ShopMemberDO::getId));
    }

}
