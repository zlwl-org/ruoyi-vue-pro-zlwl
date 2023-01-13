package cn.iocoder.yudao.module.blockchain.dal.mysql.infra;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.infra.NetDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.*;

/**
 * 网络 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface NetMapper extends BaseMapperX<NetDO> {

    default PageResult<NetDO> selectPage(NetPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<NetDO>()
                .likeIfPresent(NetDO::getName, reqVO.getName())
                .eqIfPresent(NetDO::getNameZh, reqVO.getNameZh())
                .eqIfPresent(NetDO::getSymbol, reqVO.getSymbol())
                .eqIfPresent(NetDO::getType, reqVO.getType())
                .eqIfPresent(NetDO::getChainId, reqVO.getChainId())
                .orderByDesc(NetDO::getId));
    }

    default List<NetDO> selectList(NetExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<NetDO>()
                .likeIfPresent(NetDO::getName, reqVO.getName())
                .eqIfPresent(NetDO::getNameZh, reqVO.getNameZh())
                .eqIfPresent(NetDO::getSymbol, reqVO.getSymbol())
                .eqIfPresent(NetDO::getType, reqVO.getType())
                .eqIfPresent(NetDO::getChainId, reqVO.getChainId())
                .orderByDesc(NetDO::getId));
    }

}
