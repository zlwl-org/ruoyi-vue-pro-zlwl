package cn.iocoder.yudao.module.shop.service.branch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockItemDO;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchStockItemMapper;
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
* {@link BranchStockItemServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(BranchStockItemServiceImpl.class)
public class BranchStockItemServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BranchStockItemServiceImpl branchStockItemService;

    @Resource
    private BranchStockItemMapper branchStockItemMapper;

    @Test
    public void testCreateBranchStockItem_success() {
        // 准备参数
        BranchStockItemCreateReqVO reqVO = randomPojo(BranchStockItemCreateReqVO.class);

        // 调用
        Long branchStockItemId = branchStockItemService.createBranchStockItem(reqVO);
        // 断言
        assertNotNull(branchStockItemId);
        // 校验记录的属性是否正确
        BranchStockItemDO branchStockItem = branchStockItemMapper.selectById(branchStockItemId);
        assertPojoEquals(reqVO, branchStockItem);
    }

    @Test
    public void testUpdateBranchStockItem_success() {
        // mock 数据
        BranchStockItemDO dbBranchStockItem = randomPojo(BranchStockItemDO.class);
        branchStockItemMapper.insert(dbBranchStockItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BranchStockItemUpdateReqVO reqVO = randomPojo(BranchStockItemUpdateReqVO.class, o -> {
            o.setId(dbBranchStockItem.getId()); // 设置更新的 ID
        });

        // 调用
        branchStockItemService.updateBranchStockItem(reqVO);
        // 校验是否更新正确
        BranchStockItemDO branchStockItem = branchStockItemMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, branchStockItem);
    }

    @Test
    public void testUpdateBranchStockItem_notExists() {
        // 准备参数
        BranchStockItemUpdateReqVO reqVO = randomPojo(BranchStockItemUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> branchStockItemService.updateBranchStockItem(reqVO), BRANCH_STOCK_ITEM_NOT_EXISTS);
    }

    @Test
    public void testDeleteBranchStockItem_success() {
        // mock 数据
        BranchStockItemDO dbBranchStockItem = randomPojo(BranchStockItemDO.class);
        branchStockItemMapper.insert(dbBranchStockItem);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBranchStockItem.getId();

        // 调用
        branchStockItemService.deleteBranchStockItem(id);
       // 校验数据不存在了
       assertNull(branchStockItemMapper.selectById(id));
    }

    @Test
    public void testDeleteBranchStockItem_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> branchStockItemService.deleteBranchStockItem(id), BRANCH_STOCK_ITEM_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBranchStockItemPage() {
       // mock 数据
       BranchStockItemDO dbBranchStockItem = randomPojo(BranchStockItemDO.class, o -> { // 等会查询到
           o.setStockId(null);
           o.setType(null);
           o.setBranchId(null);
           o.setProductId(null);
           o.setAmount(null);
           o.setCreateTime(null);
       });
       branchStockItemMapper.insert(dbBranchStockItem);
       // 测试 stockId 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setStockId(null)));
       // 测试 type 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setType(null)));
       // 测试 branchId 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setBranchId(null)));
       // 测试 productId 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setProductId(null)));
       // 测试 amount 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setAmount(null)));
       // 测试 createTime 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setCreateTime(null)));
       // 准备参数
       BranchStockItemPageReqVO reqVO = new BranchStockItemPageReqVO();
       reqVO.setStockId(null);
       reqVO.setType(null);
       reqVO.setBranchId(null);
       reqVO.setProductId(null);
       reqVO.setAmount(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<BranchStockItemDO> pageResult = branchStockItemService.getBranchStockItemPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBranchStockItem, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBranchStockItemList() {
       // mock 数据
       BranchStockItemDO dbBranchStockItem = randomPojo(BranchStockItemDO.class, o -> { // 等会查询到
           o.setStockId(null);
           o.setType(null);
           o.setBranchId(null);
           o.setProductId(null);
           o.setAmount(null);
           o.setCreateTime(null);
       });
       branchStockItemMapper.insert(dbBranchStockItem);
       // 测试 stockId 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setStockId(null)));
       // 测试 type 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setType(null)));
       // 测试 branchId 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setBranchId(null)));
       // 测试 productId 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setProductId(null)));
       // 测试 amount 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setAmount(null)));
       // 测试 createTime 不匹配
       branchStockItemMapper.insert(cloneIgnoreId(dbBranchStockItem, o -> o.setCreateTime(null)));
       // 准备参数
       BranchStockItemExportReqVO reqVO = new BranchStockItemExportReqVO();
       reqVO.setStockId(null);
       reqVO.setType(null);
       reqVO.setBranchId(null);
       reqVO.setProductId(null);
       reqVO.setAmount(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<BranchStockItemDO> list = branchStockItemService.getBranchStockItemList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBranchStockItem, list.get(0));
    }

}
