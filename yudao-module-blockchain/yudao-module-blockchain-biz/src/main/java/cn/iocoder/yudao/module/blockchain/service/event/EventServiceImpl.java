package cn.iocoder.yudao.module.blockchain.service.event;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.blockchain.controller.admin.event.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.event.EventDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.blockchain.convert.event.EventConvert;
import cn.iocoder.yudao.module.blockchain.dal.mysql.event.EventMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.*;

/**
 * 事件 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class EventServiceImpl implements EventService {

    @Resource
    private EventMapper eventMapper;

    @Override
    public Long createEvent(EventCreateReqVO createReqVO) {
        // 插入
        EventDO event = EventConvert.INSTANCE.convert(createReqVO);
        eventMapper.insert(event);
        // 返回
        return event.getId();
    }

    @Override
    public void updateEvent(EventUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateEventExists(updateReqVO.getId());
        // 更新
        EventDO updateObj = EventConvert.INSTANCE.convert(updateReqVO);
        eventMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvent(Long id) {
        // 校验存在
        this.validateEventExists(id);
        // 删除
        eventMapper.deleteById(id);
    }

    private void validateEventExists(Long id) {
        if (eventMapper.selectById(id) == null) {
            throw exception(EVENT_NOT_EXISTS);
        }
    }

    @Override
    public EventDO getEvent(Long id) {
        return eventMapper.selectById(id);
    }

    @Override
    public List<EventDO> getEventList(Collection<Long> ids) {
        return eventMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EventDO> getEventPage(EventPageReqVO pageReqVO) {
        return eventMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EventDO> getEventList(EventExportReqVO exportReqVO) {
        return eventMapper.selectList(exportReqVO);
    }

}
