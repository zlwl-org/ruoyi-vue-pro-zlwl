package cn.iocoder.yudao.module.shop.service.recharge;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import cn.iocoder.yudao.module.shop.dal.mysql.recharge.RechargeOrderMapper;
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
* {@link RechargeOrderServiceImpl} 的单元测试类
*
* @author ZLWL
*/
@Import(RechargeOrderServiceImpl.class)
public class RechargeOrderServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RechargeOrderServiceImpl rechargeOrderService;

    @Resource
    private RechargeOrderMapper rechargeOrderMapper;

    @Test
    public void testCreateRechargeOrder_success() {
        // 准备参数
        RechargeOrderCreateReqVO reqVO = randomPojo(RechargeOrderCreateReqVO.class);

        // 调用
        Long rechargeOrderId = rechargeOrderService.createRechargeOrder(reqVO);
        // 断言
        assertNotNull(rechargeOrderId);
        // 校验记录的属性是否正确
        RechargeOrderDO rechargeOrder = rechargeOrderMapper.selectById(rechargeOrderId);
        assertPojoEquals(reqVO, rechargeOrder);
    }

    @Test
    public void testUpdateRechargeOrder_success() {
        // mock 数据
        RechargeOrderDO dbRechargeOrder = randomPojo(RechargeOrderDO.class);
        rechargeOrderMapper.insert(dbRechargeOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RechargeOrderUpdateReqVO reqVO = randomPojo(RechargeOrderUpdateReqVO.class, o -> {
            o.setId(dbRechargeOrder.getId()); // 设置更新的 ID
        });

        // 调用
        rechargeOrderService.updateRechargeOrder(reqVO);
        // 校验是否更新正确
        RechargeOrderDO rechargeOrder = rechargeOrderMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, rechargeOrder);
    }

    @Test
    public void testUpdateRechargeOrder_notExists() {
        // 准备参数
        RechargeOrderUpdateReqVO reqVO = randomPojo(RechargeOrderUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> rechargeOrderService.updateRechargeOrder(reqVO), RECHARGE_ORDER_NOT_EXISTS);
    }

    @Test
    public void testDeleteRechargeOrder_success() {
        // mock 数据
        RechargeOrderDO dbRechargeOrder = randomPojo(RechargeOrderDO.class);
        rechargeOrderMapper.insert(dbRechargeOrder);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRechargeOrder.getId();

        // 调用
        rechargeOrderService.deleteRechargeOrder(id);
       // 校验数据不存在了
       assertNull(rechargeOrderMapper.selectById(id));
    }

    @Test
    public void testDeleteRechargeOrder_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> rechargeOrderService.deleteRechargeOrder(id), RECHARGE_ORDER_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRechargeOrderPage() {
       // mock 数据
       RechargeOrderDO dbRechargeOrder = randomPojo(RechargeOrderDO.class, o -> { // 等会查询到
           o.setOrderNo(null);
           o.setOutTradeNo(null);
           o.setRechargeAmount(null);
           o.setPayType(null);
           o.setStatus(null);
           o.setPayTime(null);
           o.setMemberId(null);
           o.setOrderFrom(null);
           o.setOrderFromName(null);
           o.setCreateTime(null);
       });
       rechargeOrderMapper.insert(dbRechargeOrder);
       // 测试 orderNo 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setOrderNo(null)));
       // 测试 outTradeNo 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setOutTradeNo(null)));
       // 测试 rechargeAmount 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setRechargeAmount(null)));
       // 测试 payType 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setPayType(null)));
       // 测试 status 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setStatus(null)));
       // 测试 payTime 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setPayTime(null)));
       // 测试 memberId 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setMemberId(null)));
       // 测试 orderFrom 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setOrderFrom(null)));
       // 测试 orderFromName 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setOrderFromName(null)));
       // 测试 createTime 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setCreateTime(null)));
       // 准备参数
       RechargeOrderPageReqVO reqVO = new RechargeOrderPageReqVO();
       reqVO.setOrderNo(null);
       reqVO.setOutTradeNo(null);
       reqVO.setRechargeAmount(null);
       reqVO.setPayType(null);
       reqVO.setStatus(null);
//       reqVO.setPayTime((new Date[]{}));
       reqVO.setMemberId(null);
       reqVO.setOrderFrom(null);
       reqVO.setOrderFromName(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<RechargeOrderDO> pageResult = rechargeOrderService.getRechargeOrderPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRechargeOrder, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRechargeOrderList() {
       // mock 数据
       RechargeOrderDO dbRechargeOrder = randomPojo(RechargeOrderDO.class, o -> { // 等会查询到
           o.setOrderNo(null);
           o.setOutTradeNo(null);
           o.setRechargeAmount(null);
           o.setPayType(null);
           o.setStatus(null);
           o.setPayTime(null);
           o.setMemberId(null);
           o.setOrderFrom(null);
           o.setOrderFromName(null);
           o.setCreateTime(null);
       });
       rechargeOrderMapper.insert(dbRechargeOrder);
       // 测试 orderNo 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setOrderNo(null)));
       // 测试 outTradeNo 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setOutTradeNo(null)));
       // 测试 rechargeAmount 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setRechargeAmount(null)));
       // 测试 payType 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setPayType(null)));
       // 测试 status 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setStatus(null)));
       // 测试 payTime 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setPayTime(null)));
       // 测试 memberId 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setMemberId(null)));
       // 测试 orderFrom 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setOrderFrom(null)));
       // 测试 orderFromName 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setOrderFromName(null)));
       // 测试 createTime 不匹配
       rechargeOrderMapper.insert(cloneIgnoreId(dbRechargeOrder, o -> o.setCreateTime(null)));
       // 准备参数
       RechargeOrderExportReqVO reqVO = new RechargeOrderExportReqVO();
       reqVO.setOrderNo(null);
       reqVO.setOutTradeNo(null);
       reqVO.setRechargeAmount(null);
       reqVO.setPayType(null);
       reqVO.setStatus(null);
//       reqVO.setPayTime((new Date[]{}));
       reqVO.setMemberId(null);
       reqVO.setOrderFrom(null);
       reqVO.setOrderFromName(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<RechargeOrderDO> list = rechargeOrderService.getRechargeOrderList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRechargeOrder, list.get(0));
    }

}
