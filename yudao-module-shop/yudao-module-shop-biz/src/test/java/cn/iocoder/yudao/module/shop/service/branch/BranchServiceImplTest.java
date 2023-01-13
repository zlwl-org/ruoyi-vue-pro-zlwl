package cn.iocoder.yudao.module.shop.service.branch;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchDO;
import cn.iocoder.yudao.module.shop.dal.mysql.branch.BranchMapper;
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
* {@link BranchServiceImpl} 的单元测试类
*
* @author ZLWL
*/
@Import(BranchServiceImpl.class)
public class BranchServiceImplTest extends BaseDbUnitTest {

    @Resource
    private BranchServiceImpl branchService;

    @Resource
    private BranchMapper branchMapper;

    @Test
    public void testCreateBranch_success() {
        // 准备参数
        BranchCreateReqVO reqVO = randomPojo(BranchCreateReqVO.class);

        // 调用
        Long branchId = branchService.createBranch(reqVO);
        // 断言
        assertNotNull(branchId);
        // 校验记录的属性是否正确
        BranchDO branch = branchMapper.selectById(branchId);
        assertPojoEquals(reqVO, branch);
    }

    @Test
    public void testUpdateBranch_success() {
        // mock 数据
        BranchDO dbBranch = randomPojo(BranchDO.class);
        branchMapper.insert(dbBranch);// @Sql: 先插入出一条存在的数据
        // 准备参数
        BranchUpdateReqVO reqVO = randomPojo(BranchUpdateReqVO.class, o -> {
            o.setId(dbBranch.getId()); // 设置更新的 ID
        });

        // 调用
        branchService.updateBranch(reqVO);
        // 校验是否更新正确
        BranchDO branch = branchMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, branch);
    }

    @Test
    public void testUpdateBranch_notExists() {
        // 准备参数
        BranchUpdateReqVO reqVO = randomPojo(BranchUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> branchService.updateBranch(reqVO), BRANCH_NOT_EXISTS);
    }

    @Test
    public void testDeleteBranch_success() {
        // mock 数据
        BranchDO dbBranch = randomPojo(BranchDO.class);
        branchMapper.insert(dbBranch);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbBranch.getId();

        // 调用
        branchService.deleteBranch(id);
       // 校验数据不存在了
       assertNull(branchMapper.selectById(id));
    }

    @Test
    public void testDeleteBranch_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> branchService.deleteBranch(id), BRANCH_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBranchPage() {
       // mock 数据
       BranchDO dbBranch = randomPojo(BranchDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setAddress(null);
           o.setTel(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       branchMapper.insert(dbBranch);
       // 测试 name 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setName(null)));
       // 测试 address 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setAddress(null)));
       // 测试 tel 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setTel(null)));
       // 测试 status 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setCreateTime(null)));
       // 准备参数
       BranchPageReqVO reqVO = new BranchPageReqVO();
       reqVO.setName(null);
       reqVO.setAddress(null);
       reqVO.setTel(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<BranchDO> pageResult = branchService.getBranchPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbBranch, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetBranchList() {
       // mock 数据
       BranchDO dbBranch = randomPojo(BranchDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setAddress(null);
           o.setTel(null);
           o.setStatus(null);
           o.setCreateTime(null);
       });
       branchMapper.insert(dbBranch);
       // 测试 name 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setName(null)));
       // 测试 address 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setAddress(null)));
       // 测试 tel 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setTel(null)));
       // 测试 status 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setStatus(null)));
       // 测试 createTime 不匹配
       branchMapper.insert(cloneIgnoreId(dbBranch, o -> o.setCreateTime(null)));
       // 准备参数
       BranchExportReqVO reqVO = new BranchExportReqVO();
       reqVO.setName(null);
       reqVO.setAddress(null);
       reqVO.setTel(null);
       reqVO.setStatus(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<BranchDO> list = branchService.getBranchList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbBranch, list.get(0));
    }

}
