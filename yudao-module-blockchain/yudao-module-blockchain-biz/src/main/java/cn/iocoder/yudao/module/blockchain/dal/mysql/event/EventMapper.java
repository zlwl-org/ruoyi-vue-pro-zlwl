package cn.iocoder.yudao.module.blockchain.dal.mysql.event;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.event.EventDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.blockchain.controller.admin.event.vo.*;

/**
 * 事件 Mapper
 *
 * @author ruanzh
 */
@Mapper
public interface EventMapper extends BaseMapperX<EventDO> {

    default PageResult<EventDO> selectPage(EventPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EventDO>()
                .eqIfPresent(EventDO::getTopic, reqVO.getTopic())
                .eqIfPresent(EventDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EventDO::getNet, reqVO.getNet())
                .eqIfPresent(EventDO::getInfo, reqVO.getInfo())
                .betweenIfPresent(EventDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventDO::getId));
    }

    default List<EventDO> selectList(EventExportReqVO reqVO) {
        return selectList(new LambdaQueryWrapperX<EventDO>()
                .eqIfPresent(EventDO::getTopic, reqVO.getTopic())
                .eqIfPresent(EventDO::getAddress, reqVO.getAddress())
                .eqIfPresent(EventDO::getNet, reqVO.getNet())
                .eqIfPresent(EventDO::getInfo, reqVO.getInfo())
                .betweenIfPresent(EventDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EventDO::getId));
    }

}
