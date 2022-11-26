package cn.iocoder.yudao.module.shop.service.order;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.mysql.order.ShopOrderMapper;
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
* {@link ShopOrderServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(ShopOrderServiceImpl.class)
public class ShopOrderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private ShopOrderServiceImpl orderService;

    @Resource
    private ShopOrderMapper orderMapper;

    @Test
    public void testCreateOrder_success() {
        // 准备参数
        ShopOrderCreateReqVO reqVO = randomPojo(ShopOrderCreateReqVO.class);

        // 调用
        Long orderId = orderService.createOrder(reqVO);
        // 断言
        assertNotNull(orderId);
        // 校验记录的属性是否正确
        ShopOrderDO order = orderMapper.selectById(orderId);
        assertPojoEquals(reqVO, order);
    }

    @Test
    public void testUpdateOrder_success() {
        // mock 数据
        ShopOrderDO dbOrder = randomPojo(ShopOrderDO.class);
        orderMapper.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        ShopOrderUpdateReqVO reqVO = randomPojo(ShopOrderUpdateReqVO.class, o -> {
            o.setId(dbOrder.getId()); // 设置更新的 ID
        });

        // 调用
        orderService.updateOrder(reqVO);
        // 校验是否更新正确
        ShopOrderDO order = orderMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, order);
    }

    @Test
    public void testUpdateOrder_notExists() {
        // 准备参数
        ShopOrderUpdateReqVO reqVO = randomPojo(ShopOrderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> orderService.updateOrder(reqVO), ORDER_NOT_EXISTS);
    }

    @Test
    public void testDeleteOrder_success() {
        // mock 数据
        ShopOrderDO dbOrder = randomPojo(ShopOrderDO.class);
        orderMapper.insert(dbOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbOrder.getId();

        // 调用
        orderService.deleteOrder(id);
       // 校验数据不存在了
       assertNull(orderMapper.selectById(id));
    }

    @Test
    public void testDeleteOrder_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> orderService.deleteOrder(id), ORDER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderPage() {
       // mock 数据
       ShopOrderDO dbOrder = randomPojo(ShopOrderDO.class, o -> { // 等会查询到
           o.setMemberId(null);
           o.setOrderType(null);
           o.setOrderNo(null);
           o.setOrderStatus(null);
           o.setPayType(null);
           o.setPayStatus(null);
           o.setPayTime(null);
           o.setCashier(null);
           o.setBranchId(null);
           o.setCreateTime(null);
       });
       orderMapper.insert(dbOrder);
       // 测试 memberId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setMemberId(null)));
       // 测试 orderType 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderType(null)));
       // 测试 orderNo 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderNo(null)));
       // 测试 orderStatus 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderStatus(null)));
       // 测试 payType 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPayType(null)));
       // 测试 payStatus 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPayStatus(null)));
       // 测试 payTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPayTime(null)));
       // 测试 cashier 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCashier(null)));
       // 测试 branchId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setBranchId(null)));
       // 测试 createTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCreateTime(null)));
       // 准备参数
       ShopOrderPageReqVO reqVO = new ShopOrderPageReqVO();
       reqVO.setMemberId(null);
       reqVO.setOrderType(null);
       reqVO.setOrderNo(null);
       reqVO.setOrderStatus(null);
       reqVO.setPayType(null);
       reqVO.setPayStatus(null);
       reqVO.setPayTime((new Date[]{}));
       reqVO.setCashier(null);
       reqVO.setBranchId(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<ShopOrderDO> pageResult = orderService.getOrderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbOrder, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetOrderList() {
       // mock 数据
       ShopOrderDO dbOrder = randomPojo(ShopOrderDO.class, o -> { // 等会查询到
           o.setMemberId(null);
           o.setOrderType(null);
           o.setOrderNo(null);
           o.setOrderStatus(null);
           o.setPayType(null);
           o.setPayStatus(null);
           o.setPayTime(null);
           o.setCashier(null);
           o.setBranchId(null);
           o.setCreateTime(null);
       });
       orderMapper.insert(dbOrder);
       // 测试 memberId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setMemberId(null)));
       // 测试 orderType 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderType(null)));
       // 测试 orderNo 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderNo(null)));
       // 测试 orderStatus 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setOrderStatus(null)));
       // 测试 payType 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPayType(null)));
       // 测试 payStatus 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPayStatus(null)));
       // 测试 payTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setPayTime(null)));
       // 测试 cashier 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCashier(null)));
       // 测试 branchId 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setBranchId(null)));
       // 测试 createTime 不匹配
       orderMapper.insert(cloneIgnoreId(dbOrder, o -> o.setCreateTime(null)));
       // 准备参数
       ShopOrderExportReqVO reqVO = new ShopOrderExportReqVO();
       reqVO.setMemberId(null);
       reqVO.setOrderType(null);
       reqVO.setOrderNo(null);
       reqVO.setOrderStatus(null);
       reqVO.setPayType(null);
       reqVO.setPayStatus(null);
       reqVO.setPayTime((new Date[]{}));
       reqVO.setCashier(null);
       reqVO.setBranchId(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<ShopOrderDO> list = orderService.getOrderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbOrder, list.get(0));
    }

}
