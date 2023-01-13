package cn.iocoder.yudao.module.shop.dal.mysql.member;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogPageReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.MemberAccountLogDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 会员账户流水 Mapper
 *
 * @author ZLWL
 */
@Mapper
public interface MemberAccountLogMapper extends BaseMapperX<MemberAccountLogDO> {

    default PageResult<MemberAccountLogDO> selectPage(MemberAccountLogPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MemberAccountLogDO>()
                .eqIfPresent(MemberAccountLogDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(MemberAccountLogDO::getAction, reqVO.getAction())
                .eqIfPresent(MemberAccountLogDO::getBalance, reqVO.getBalance())
                .eqIfPresent(MemberAccountLogDO::getGift, reqVO.getGift())
                .eqIfPresent(MemberAccountLogDO::getPoint, reqVO.getPoint())
                .eqIfPresent(MemberAccountLogDO::getGrowth, reqVO.getGrowth())
                .eqIfPresent(MemberAccountLogDO::getInfo, reqVO.getInfo())
                .betweenIfPresent(MemberAccountLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberAccountLogDO::getId));
    }

    default List<MemberAccountLogDO> selectList(MemberAccountLogExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<MemberAccountLogDO>()
                .eqIfPresent(MemberAccountLogDO::getMemberId, reqVO.getMemberId())
                .eqIfPresent(MemberAccountLogDO::getAction, reqVO.getAction())
                .eqIfPresent(MemberAccountLogDO::getBalance, reqVO.getBalance())
                .eqIfPresent(MemberAccountLogDO::getGift, reqVO.getGift())
                .eqIfPresent(MemberAccountLogDO::getPoint, reqVO.getPoint())
                .eqIfPresent(MemberAccountLogDO::getGrowth, reqVO.getGrowth())
                .eqIfPresent(MemberAccountLogDO::getInfo, reqVO.getInfo())
                .betweenIfPresent(MemberAccountLogDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MemberAccountLogDO::getId));
    }

}
