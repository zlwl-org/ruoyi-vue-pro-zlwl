package cn.iocoder.yudao.module.shop.dal.mysql.branch;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;

/**
 * 门店 Mapper
 *
 * @author ZLWL
 */
@Mapper
public interface BranchMapper extends BaseMapperX<BranchDO> {

    default PageResult<BranchDO> selectPage(BranchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BranchDO>()
                .likeIfPresent(BranchDO::getName, reqVO.getName())
                .eqIfPresent(BranchDO::getAddress, reqVO.getAddress())
                .eqIfPresent(BranchDO::getTel, reqVO.getTel())
                .eqIfPresent(BranchDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BranchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BranchDO::getId));
    }

    default List<BranchDO> selectList(BranchExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<BranchDO>()
                .likeIfPresent(BranchDO::getName, reqVO.getName())
                .eqIfPresent(BranchDO::getAddress, reqVO.getAddress())
                .eqIfPresent(BranchDO::getTel, reqVO.getTel())
                .eqIfPresent(BranchDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(BranchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(BranchDO::getId));
    }

    default List<BranchDO> selectListByStatus(Integer status){
        return selectList(BranchDO:: getStatus, status);
    }
}
