package cn.iocoder.yudao.module.shop.dal.mysql.member;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.ShopMemberExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.ShopMemberPageReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.List;

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

    default List<ShopMemberDO> selectListBySalesman(Long userId){
        return selectList(new LambdaQueryWrapperX<ShopMemberDO>().eq(ShopMemberDO::getSalesman, userId));
    }

    @Update("update shop_member set balance = balance + #{balance},gift = gift + #{gift},point = point + #{point},growth = growth + " +
            "#{growth} where id = #{id} and balance + #{balance} >= 0 and gift + #{gift} >= 0 and point + #{point} >= 0 and growth + #{growth} >= 0 ")
    int updateAccount(@Param("balance")BigDecimal balance, @Param("gift")BigDecimal gift, @Param("point")BigDecimal point, @Param("growth")BigDecimal growth, @Param("id")Long id);

}
