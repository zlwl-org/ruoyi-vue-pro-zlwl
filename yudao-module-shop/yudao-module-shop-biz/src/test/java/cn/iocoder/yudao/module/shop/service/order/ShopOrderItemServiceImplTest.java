package cn.iocoder.yudao.module.shop.service.order;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderItemDO;
import cn.iocoder.yudao.module.shop.dal.mysql.order.ShopOrderItemMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link ShopOrderItemServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(ShopOrderItemServiceImpl.class)
public class ShopOrderItemServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ShopOrderItemServiceImpl orderItemService;

    @Resource
    private ShopOrderItemMapper orderItemMapper;

    @Test
    public void testCreateOrderItem_success() {
        // 准备参数
        ShopOrderItemCreateReqVO reqVO = randomPojo(ShopOrderItemCreateReqVO.class);

        // 调用
        Long orderItemId = orderItemService.createOrderItem(reqVO);
        // 断言
        assertNotNull(orderItemId);
        // 校验记录的属性是否正确
        ShopOrderItemDO orderItem = orderItemMapper.selectById(orderItemId);
        assertPojoEquals(reqVO, orderItem);
    }

    @Test
    public void testUpdateOrderItem_success() {
        // mock 数据
        ShopOrderItemDO dbOrderItem = randomPojo(ShopOrderItemDO.class);
        orderItemMapper.insert(dbOrderItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ShopOrderItemUpdateReqVO reqVO = randomPojo(ShopOrderItemUpdateReqVO.class, o -> {
            o.setId(dbOrderItem.getId()); // 设置更新的 ID
        });

        // 调用
        orderItemService.updateOrderItem(reqVO);
        // 校验是否更新正确
        ShopOrderItemDO orderItem = orderItemMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, orderItem);
    }

    @Test
    public void testUpdateOrderItem_notExists() {
        // 准备参数
        ShopOrderItemUpdateReqVO reqVO = randomPojo(ShopOrderItemUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderItemService.updateOrderItem(reqVO), ORDER_ITEM_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrderItem_success() {
        // mock 数据
        ShopOrderItemDO dbOrderItem = randomPojo(ShopOrderItemDO.class);
        orderItemMapper.insert(dbOrderItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOrderItem.getId();

        // 调用
        orderItemService.deleteOrderItem(id);
       // 校验数据不存在了
       assertNull(orderItemMapper.selectById(id));
    }

    @Test
    public void testDeleteOrderItem_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> orderItemService.deleteOrderItem(id), ORDER_ITEM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderItemPage() {
       // mock 数据
       ShopOrderItemDO dbOrderItem = randomPojo(ShopOrderItemDO.class, o -> { // 等会查询到
           o.setMemberId(null);
           o.setOrderId(null);
           o.setGoodId(null);
           o.setGoodName(null);
           o.setGoodPrice(null);
           o.setAmount(null);
           o.setCreateTime(null);
       });
       orderItemMapper.insert(dbOrderItem);
       // 测试 memberId 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setMemberId(null)));
       // 测试 orderId 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setOrderId(null)));
       // 测试 goodId 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setGoodId(null)));
       // 测试 goodName 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setGoodName(null)));
       // 测试 goodPrice 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setGoodPrice(null)));
       // 测试 amount 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setAmount(null)));
       // 测试 createTime 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setCreateTime(null)));
       // 准备参数
       ShopOrderItemPageReqVO reqVO = new ShopOrderItemPageReqVO();
       reqVO.setMemberId(null);
       reqVO.setOrderId(null);
       reqVO.setGoodId(null);
       reqVO.setGoodName(null);
       reqVO.setGoodPrice(null);
       reqVO.setAmount(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<ShopOrderItemDO> pageResult = orderItemService.getOrderItemPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrderItem, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderItemList() {
       // mock 数据
       ShopOrderItemDO dbOrderItem = randomPojo(ShopOrderItemDO.class, o -> { // 等会查询到
           o.setMemberId(null);
           o.setOrderId(null);
           o.setGoodId(null);
           o.setGoodName(null);
           o.setGoodPrice(null);
           o.setAmount(null);
           o.setCreateTime(null);
       });
       orderItemMapper.insert(dbOrderItem);
       // 测试 memberId 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setMemberId(null)));
       // 测试 orderId 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setOrderId(null)));
       // 测试 goodId 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setGoodId(null)));
       // 测试 goodName 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setGoodName(null)));
       // 测试 goodPrice 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setGoodPrice(null)));
       // 测试 amount 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setAmount(null)));
       // 测试 createTime 不匹配
       orderItemMapper.insert(cloneIgnoreId(dbOrderItem, o -> o.setCreateTime(null)));
       // 准备参数
       ShopOrderItemExportReqVO reqVO = new ShopOrderItemExportReqVO();
       reqVO.setMemberId(null);
       reqVO.setOrderId(null);
       reqVO.setGoodId(null);
       reqVO.setGoodName(null);
       reqVO.setGoodPrice(null);
       reqVO.setAmount(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<ShopOrderItemDO> list = orderItemService.getOrderItemList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOrderItem, list.get(0));
    }

}
