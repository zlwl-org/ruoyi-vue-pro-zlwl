package cn.iocoder.yudao.module.blockchain.service.event;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.blockchain.controller.admin.event.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.event.EventDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.event.EventMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link EventServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(EventServiceImpl.class)
public class EventServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EventServiceImpl eventService;

    @Resource
    private EventMapper eventMapper;

    @Test
    public void testCreateEvent_success() {
        // 准备参数
        EventCreateReqVO reqVO = randomPojo(EventCreateReqVO.class);

        // 调用
        Long eventId = eventService.createEvent(reqVO);
        // 断言
        assertNotNull(eventId);
        // 校验记录的属性是否正确
        EventDO event = eventMapper.selectById(eventId);
        assertPojoEquals(reqVO, event);
    }

    @Test
    public void testUpdateEvent_success() {
        // mock 数据
        EventDO dbEvent = randomPojo(EventDO.class);
        eventMapper.insert(dbEvent);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EventUpdateReqVO reqVO = randomPojo(EventUpdateReqVO.class, o -> {
            o.setId(dbEvent.getId()); // 设置更新的 ID
        });

        // 调用
        eventService.updateEvent(reqVO);
        // 校验是否更新正确
        EventDO event = eventMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, event);
    }

    @Test
    public void testUpdateEvent_notExists() {
        // 准备参数
        EventUpdateReqVO reqVO = randomPojo(EventUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> eventService.updateEvent(reqVO), EVENT_NOT_EXISTS);
    }

    @Test
    public void testDeleteEvent_success() {
        // mock 数据
        EventDO dbEvent = randomPojo(EventDO.class);
        eventMapper.insert(dbEvent);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEvent.getId();

        // 调用
        eventService.deleteEvent(id);
       // 校验数据不存在了
       assertNull(eventMapper.selectById(id));
    }

    @Test
    public void testDeleteEvent_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> eventService.deleteEvent(id), EVENT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEventPage() {
       // mock 数据
       EventDO dbEvent = randomPojo(EventDO.class, o -> { // 等会查询到
           o.setTopic(null);
           o.setAddress(null);
           o.setNet(null);
           o.setInfo(null);
           o.setCreateTime(null);
       });
       eventMapper.insert(dbEvent);
       // 测试 topic 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setTopic(null)));
       // 测试 address 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setAddress(null)));
       // 测试 net 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setNet(null)));
       // 测试 info 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setInfo(null)));
       // 测试 createTime 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setCreateTime(null)));
       // 准备参数
       EventPageReqVO reqVO = new EventPageReqVO();
       reqVO.setTopic(null);
       reqVO.setAddress(null);
       reqVO.setNet(null);
       reqVO.setInfo(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<EventDO> pageResult = eventService.getEventPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEvent, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEventList() {
       // mock 数据
       EventDO dbEvent = randomPojo(EventDO.class, o -> { // 等会查询到
           o.setTopic(null);
           o.setAddress(null);
           o.setNet(null);
           o.setInfo(null);
           o.setCreateTime(null);
       });
       eventMapper.insert(dbEvent);
       // 测试 topic 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setTopic(null)));
       // 测试 address 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setAddress(null)));
       // 测试 net 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setNet(null)));
       // 测试 info 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setInfo(null)));
       // 测试 createTime 不匹配
       eventMapper.insert(cloneIgnoreId(dbEvent, o -> o.setCreateTime(null)));
       // 准备参数
       EventExportReqVO reqVO = new EventExportReqVO();
       reqVO.setTopic(null);
       reqVO.setAddress(null);
       reqVO.setNet(null);
       reqVO.setInfo(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<EventDO> list = eventService.getEventList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEvent, list.get(0));
    }

}
