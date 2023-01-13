package cn.iocoder.yudao.module.blockchain.service.event;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.blockchain.controller.admin.event.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.event.EventDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 事件 Service 接口
 *
 * @author ruanzh
 */
public interface EventService {

    /**
     * 创建事件
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEvent(@Valid EventCreateReqVO createReqVO);

    /**
     * 更新事件
     *
     * @param updateReqVO 更新信息
     */
    void updateEvent(@Valid EventUpdateReqVO updateReqVO);

    /**
     * 删除事件
     *
     * @param id 编号
     */
    void deleteEvent(Long id);

    /**
     * 获得事件
     *
     * @param id 编号
     * @return 事件
     */
    EventDO getEvent(Long id);

    /**
     * 获得事件列表
     *
     * @param ids 编号
     * @return 事件列表
     */
    List<EventDO> getEventList(Collection<Long> ids);

    /**
     * 获得事件分页
     *
     * @param pageReqVO 分页查询
     * @return 事件分页
     */
    PageResult<EventDO> getEventPage(EventPageReqVO pageReqVO);

    /**
     * 获得事件列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 事件列表
     */
    List<EventDO> getEventList(EventExportReqVO exportReqVO);

}
