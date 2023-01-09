package cn.iocoder.yudao.module.blockchain.convert.event;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import cn.iocoder.yudao.module.blockchain.controller.admin.event.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.event.EventDO;

/**
 * 事件 Convert
 *
 * @author ruanzh
 */
@Mapper
public interface EventConvert {

    EventConvert INSTANCE = Mappers.getMapper(EventConvert.class);

    EventDO convert(EventCreateReqVO bean);

    EventDO convert(EventUpdateReqVO bean);

    EventRespVO convert(EventDO bean);

    List<EventRespVO> convertList(List<EventDO> list);

    PageResult<EventRespVO> convertPage(PageResult<EventDO> page);

    List<EventExcelVO> convertList02(List<EventDO> list);

}
