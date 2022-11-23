package cn.iocoder.yudao.module.shop.service.branch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchGoodsMapper;
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
* {@link BranchGoodsServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(BranchGoodsServiceImpl.class)
public class BranchGoodsServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BranchGoodsServiceImpl branchGoodsService;

    @Resource
    private BranchGoodsMapper branchGoodsMapper;

    @Test
    public void testCreateBranchGoods_success() {
        // 准备参数
        BranchGoodsCreateReqVO reqVO = randomPojo(BranchGoodsCreateReqVO.class);

        // 调用
        Long branchGoodsId = branchGoodsService.createBranchGoods(reqVO);
        // 断言
        assertNotNull(branchGoodsId);
        // 校验记录的属性是否正确
        BranchGoodsDO branchGoods = branchGoodsMapper.selectById(branchGoodsId);
        assertPojoEquals(reqVO, branchGoods);
    }

    @Test
    public void testUpdateBranchGoods_success() {
        // mock 数据
        BranchGoodsDO dbBranchGoods = randomPojo(BranchGoodsDO.class);
        branchGoodsMapper.insert(dbBranchGoods);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BranchGoodsUpdateReqVO reqVO = randomPojo(BranchGoodsUpdateReqVO.class, o -> {
            o.setId(dbBranchGoods.getId()); // 设置更新的 ID
        });

        // 调用
        branchGoodsService.updateBranchGoods(reqVO);
        // 校验是否更新正确
        BranchGoodsDO branchGoods = branchGoodsMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, branchGoods);
    }

    @Test
    public void testUpdateBranchGoods_notExists() {
        // 准备参数
        BranchGoodsUpdateReqVO reqVO = randomPojo(BranchGoodsUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> branchGoodsService.updateBranchGoods(reqVO), BRANCH_GOODS_NOT_EXISTS);
    }

    @Test
    public void testDeleteBranchGoods_success() {
        // mock 数据
        BranchGoodsDO dbBranchGoods = randomPojo(BranchGoodsDO.class);
        branchGoodsMapper.insert(dbBranchGoods);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBranchGoods.getId();

        // 调用
        branchGoodsService.deleteBranchGoods(id);
       // 校验数据不存在了
       assertNull(branchGoodsMapper.selectById(id));
    }

    @Test
    public void testDeleteBranchGoods_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> branchGoodsService.deleteBranchGoods(id), BRANCH_GOODS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBranchGoodsPage() {
       // mock 数据
       BranchGoodsDO dbBranchGoods = randomPojo(BranchGoodsDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPrice(null);
           o.setProductId(null);
           o.setBranchId(null);
           o.setBrandId(null);
           o.setCreateTime(null);
       });
       branchGoodsMapper.insert(dbBranchGoods);
       // 测试 name 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setName(null)));
       // 测试 price 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setPrice(null)));
       // 测试 productId 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setProductId(null)));
       // 测试 branchId 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setBranchId(null)));
       // 测试 brandId 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setBrandId(null)));
       // 测试 createTime 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setCreateTime(null)));
       // 准备参数
       BranchGoodsPageReqVO reqVO = new BranchGoodsPageReqVO();
       reqVO.setName(null);
       reqVO.setPrice(null);
       reqVO.setProductId(null);
       reqVO.setBranchId(null);
       reqVO.setBrandId(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<BranchGoodsDO> pageResult = branchGoodsService.getBranchGoodsPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBranchGoods, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBranchGoodsList() {
       // mock 数据
       BranchGoodsDO dbBranchGoods = randomPojo(BranchGoodsDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setPrice(null);
           o.setProductId(null);
           o.setBranchId(null);
           o.setBrandId(null);
           o.setCreateTime(null);
       });
       branchGoodsMapper.insert(dbBranchGoods);
       // 测试 name 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setName(null)));
       // 测试 price 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setPrice(null)));
       // 测试 productId 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setProductId(null)));
       // 测试 branchId 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setBranchId(null)));
       // 测试 brandId 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setBrandId(null)));
       // 测试 createTime 不匹配
       branchGoodsMapper.insert(cloneIgnoreId(dbBranchGoods, o -> o.setCreateTime(null)));
       // 准备参数
       BranchGoodsExportReqVO reqVO = new BranchGoodsExportReqVO();
       reqVO.setName(null);
       reqVO.setPrice(null);
       reqVO.setProductId(null);
       reqVO.setBranchId(null);
       reqVO.setBrandId(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<BranchGoodsDO> list = branchGoodsService.getBranchGoodsList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBranchGoods, list.get(0));
    }

}
