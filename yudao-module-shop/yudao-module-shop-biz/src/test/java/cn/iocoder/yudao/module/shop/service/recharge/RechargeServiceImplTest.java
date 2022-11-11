package cn.iocoder.yudao.module.shop.service.recharge;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.dal.mysql.recharge.RechargeMapper;
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
* {@link RechargeServiceImpl} 的单元测试类
*
* @author ZLWL
*/
@Import(RechargeServiceImpl.class)
public class RechargeServiceImplTest extends BaseDbUnitTest {

    @Resource
    private RechargeServiceImpl rechargeService;

    @Resource
    private RechargeMapper rechargeMapper;

    @Test
    public void testCreateRecharge_success() {
        // 准备参数
        RechargeCreateReqVO reqVO = randomPojo(RechargeCreateReqVO.class);

        // 调用
        Long rechargeId = rechargeService.createRecharge(reqVO);
        // 断言
        assertNotNull(rechargeId);
        // 校验记录的属性是否正确
        RechargeDO recharge = rechargeMapper.selectById(rechargeId);
        assertPojoEquals(reqVO, recharge);
    }

    @Test
    public void testUpdateRecharge_success() {
        // mock 数据
        RechargeDO dbRecharge = randomPojo(RechargeDO.class);
        rechargeMapper.insert(dbRecharge);// @Sql: 先插入出一条存在的数据
        // 准备参数
        RechargeUpdateReqVO reqVO = randomPojo(RechargeUpdateReqVO.class, o -> {
            o.setId(dbRecharge.getId()); // 设置更新的 ID
        });

        // 调用
        rechargeService.updateRecharge(reqVO);
        // 校验是否更新正确
        RechargeDO recharge = rechargeMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, recharge);
    }

    @Test
    public void testUpdateRecharge_notExists() {
        // 准备参数
        RechargeUpdateReqVO reqVO = randomPojo(RechargeUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> rechargeService.updateRecharge(reqVO), RECHARGE_NOT_EXISTS);
    }

    @Test
    public void testDeleteRecharge_success() {
        // mock 数据
        RechargeDO dbRecharge = randomPojo(RechargeDO.class);
        rechargeMapper.insert(dbRecharge);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbRecharge.getId();

        // 调用
        rechargeService.deleteRecharge(id);
       // 校验数据不存在了
       assertNull(rechargeMapper.selectById(id));
    }

    @Test
    public void testDeleteRecharge_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> rechargeService.deleteRecharge(id), RECHARGE_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRechargePage() {
       // mock 数据
       RechargeDO dbRecharge = randomPojo(RechargeDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPrice(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       rechargeMapper.insert(dbRecharge);
       // 测试 name 不匹配
       rechargeMapper.insert(cloneIgnoreId(dbRecharge, o -> o.setName(null)));
       // 测试 price 不匹配
       rechargeMapper.insert(cloneIgnoreId(dbRecharge, o -> o.setPrice(null)));
       // 测试 status 不匹配
       rechargeMapper.insert(cloneIgnoreId(dbRecharge, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       rechargeMapper.insert(cloneIgnoreId(dbRecharge, o -> o.setCreateTime(null)));
       // 准备参数
       RechargePageReqVO reqVO = new RechargePageReqVO();
       reqVO.setName(null);
       reqVO.setPrice(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<RechargeDO> pageResult = rechargeService.getRechargePage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbRecharge, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetRechargeList() {
       // mock 数据
       RechargeDO dbRecharge = randomPojo(RechargeDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPrice(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       rechargeMapper.insert(dbRecharge);
       // 测试 name 不匹配
       rechargeMapper.insert(cloneIgnoreId(dbRecharge, o -> o.setName(null)));
       // 测试 price 不匹配
       rechargeMapper.insert(cloneIgnoreId(dbRecharge, o -> o.setPrice(null)));
       // 测试 status 不匹配
       rechargeMapper.insert(cloneIgnoreId(dbRecharge, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       rechargeMapper.insert(cloneIgnoreId(dbRecharge, o -> o.setCreateTime(null)));
       // 准备参数
       RechargeExportReqVO reqVO = new RechargeExportReqVO();
       reqVO.setName(null);
       reqVO.setPrice(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<RechargeDO> list = rechargeService.getRechargeList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbRecharge, list.get(0));
    }

}
