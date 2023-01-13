package cn.iocoder.yudao.module.shop.service.branch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockDO;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchStockMapper;
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
* {@link BranchStockServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(BranchStockServiceImpl.class)
public class BranchStockServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BranchStockServiceImpl branchStockService;

    @Resource
    private BranchStockMapper branchStockMapper;

    @Test
    public void testCreateBranchStock_success() {
        // 准备参数
        BranchStockCreateReqVO reqVO = randomPojo(BranchStockCreateReqVO.class);

        // 调用
        Long branchStockId = branchStockService.createBranchStock(reqVO);
        // 断言
        assertNotNull(branchStockId);
        // 校验记录的属性是否正确
        BranchStockDO branchStock = branchStockMapper.selectById(branchStockId);
        assertPojoEquals(reqVO, branchStock);
    }

    @Test
    public void testUpdateBranchStock_success() {
        // mock 数据
        BranchStockDO dbBranchStock = randomPojo(BranchStockDO.class);
        branchStockMapper.insert(dbBranchStock);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BranchStockUpdateReqVO reqVO = randomPojo(BranchStockUpdateReqVO.class, o -> {
            o.setId(dbBranchStock.getId()); // 设置更新的 ID
        });

        // 调用
        branchStockService.updateBranchStock(reqVO);
        // 校验是否更新正确
        BranchStockDO branchStock = branchStockMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, branchStock);
    }

    @Test
    public void testUpdateBranchStock_notExists() {
        // 准备参数
        BranchStockUpdateReqVO reqVO = randomPojo(BranchStockUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> branchStockService.updateBranchStock(reqVO), BRANCH_STOCK_NOT_EXISTS);
    }

    @Test
    public void testDeleteBranchStock_success() {
        // mock 数据
        BranchStockDO dbBranchStock = randomPojo(BranchStockDO.class);
        branchStockMapper.insert(dbBranchStock);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBranchStock.getId();

        // 调用
        branchStockService.deleteBranchStock(id);
       // 校验数据不存在了
       assertNull(branchStockMapper.selectById(id));
    }

    @Test
    public void testDeleteBranchStock_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> branchStockService.deleteBranchStock(id), BRANCH_STOCK_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBranchStockPage() {
       // mock 数据
       BranchStockDO dbBranchStock = randomPojo(BranchStockDO.class, o -> { // 等会查询到
           o.setBranchId(null);
           o.setCreateTime(null);
       });
       branchStockMapper.insert(dbBranchStock);
       // 测试 branchId 不匹配
       branchStockMapper.insert(cloneIgnoreId(dbBranchStock, o -> o.setBranchId(null)));
       // 测试 createTime 不匹配
       branchStockMapper.insert(cloneIgnoreId(dbBranchStock, o -> o.setCreateTime(null)));
       // 准备参数
       BranchStockPageReqVO reqVO = new BranchStockPageReqVO();
       reqVO.setBranchId(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<BranchStockDO> pageResult = branchStockService.getBranchStockPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBranchStock, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBranchStockList() {
       // mock 数据
       BranchStockDO dbBranchStock = randomPojo(BranchStockDO.class, o -> { // 等会查询到
           o.setBranchId(null);
           o.setCreateTime(null);
       });
       branchStockMapper.insert(dbBranchStock);
       // 测试 branchId 不匹配
       branchStockMapper.insert(cloneIgnoreId(dbBranchStock, o -> o.setBranchId(null)));
       // 测试 createTime 不匹配
       branchStockMapper.insert(cloneIgnoreId(dbBranchStock, o -> o.setCreateTime(null)));
       // 准备参数
       BranchStockExportReqVO reqVO = new BranchStockExportReqVO();
       reqVO.setBranchId(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<BranchStockDO> list = branchStockService.getBranchStockList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBranchStock, list.get(0));
    }

}
