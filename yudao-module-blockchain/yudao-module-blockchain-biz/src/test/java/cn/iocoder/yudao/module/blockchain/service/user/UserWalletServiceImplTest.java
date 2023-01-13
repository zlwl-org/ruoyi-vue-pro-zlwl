package cn.iocoder.yudao.module.blockchain.service.user;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.blockchain.controller.admin.user.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.user.UserWalletDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.user.UserWalletMapper;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import javax.annotation.Resource;
import org.springframework.context.annotation.Import;
import java.util.*;
import java.time.LocalDateTime;

import static cn.hutool.core.util.RandomUtil.*;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.framework.test.core.util.AssertUtils.*;
import static cn.iocoder.yudao.framework.test.core.util.RandomUtils.*;
import static cn.iocoder.yudao.framework.common.util.object.ObjectUtils.*;
import static cn.iocoder.yudao.framework.common.util.date.DateUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
* {@link UserWalletServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(UserWalletServiceImpl.class)
public class UserWalletServiceImplTest extends BaseDbUnitTest {

    @Resource
    private UserWalletServiceImpl userWalletService;

    @Resource
    private UserWalletMapper userWalletMapper;

    @Test
    public void testCreateUserWallet_success() {
        // 准备参数
        UserWalletCreateReqVO reqVO = randomPojo(UserWalletCreateReqVO.class);

        // 调用
        Long userWalletId = userWalletService.createUserWallet(reqVO);
        // 断言
        assertNotNull(userWalletId);
        // 校验记录的属性是否正确
        UserWalletDO userWallet = userWalletMapper.selectById(userWalletId);
        assertPojoEquals(reqVO, userWallet);
    }

    @Test
    public void testUpdateUserWallet_success() {
        // mock 数据
        UserWalletDO dbUserWallet = randomPojo(UserWalletDO.class);
        userWalletMapper.insert(dbUserWallet);// @Sql: 先插入出一条存在的数据
        // 准备参数
        UserWalletUpdateReqVO reqVO = randomPojo(UserWalletUpdateReqVO.class, o -> {
            o.setId(dbUserWallet.getId()); // 设置更新的 ID
        });

        // 调用
        userWalletService.updateUserWallet(reqVO);
        // 校验是否更新正确
        UserWalletDO userWallet = userWalletMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, userWallet);
    }

    @Test
    public void testUpdateUserWallet_notExists() {
        // 准备参数
        UserWalletUpdateReqVO reqVO = randomPojo(UserWalletUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> userWalletService.updateUserWallet(reqVO), USER_WALLET_NOT_EXISTS);
    }

    @Test
    public void testDeleteUserWallet_success() {
        // mock 数据
        UserWalletDO dbUserWallet = randomPojo(UserWalletDO.class);
        userWalletMapper.insert(dbUserWallet);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbUserWallet.getId();

        // 调用
        userWalletService.deleteUserWallet(id);
       // 校验数据不存在了
       assertNull(userWalletMapper.selectById(id));
    }

    @Test
    public void testDeleteUserWallet_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> userWalletService.deleteUserWallet(id), USER_WALLET_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetUserWalletPage() {
       // mock 数据
       UserWalletDO dbUserWallet = randomPojo(UserWalletDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setName(null);
           o.setAddress(null);
           o.setNet(null);
           o.setSymbol(null);
           o.setBalance(null);
           o.setCreateTime(null);
       });
       userWalletMapper.insert(dbUserWallet);
       // 测试 userId 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setUserId(null)));
       // 测试 name 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setName(null)));
       // 测试 address 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setAddress(null)));
       // 测试 net 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setNet(null)));
       // 测试 symbol 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setSymbol(null)));
       // 测试 balance 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setBalance(null)));
       // 测试 createTime 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setCreateTime(null)));
       // 准备参数
       UserWalletPageReqVO reqVO = new UserWalletPageReqVO();
       reqVO.setUserId(null);
       reqVO.setName(null);
       reqVO.setAddress(null);
       reqVO.setNet(null);
       reqVO.setSymbol(null);
       reqVO.setBalance(null);
       reqVO.setCreateTime((new LocalDateTime[]{}));

       // 调用
       PageResult<UserWalletDO> pageResult = userWalletService.getUserWalletPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbUserWallet, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetUserWalletList() {
       // mock 数据
       UserWalletDO dbUserWallet = randomPojo(UserWalletDO.class, o -> { // 等会查询到
           o.setUserId(null);
           o.setName(null);
           o.setAddress(null);
           o.setNet(null);
           o.setSymbol(null);
           o.setBalance(null);
           o.setCreateTime(null);
       });
       userWalletMapper.insert(dbUserWallet);
       // 测试 userId 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setUserId(null)));
       // 测试 name 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setName(null)));
       // 测试 address 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setAddress(null)));
       // 测试 net 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setNet(null)));
       // 测试 symbol 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setSymbol(null)));
       // 测试 balance 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setBalance(null)));
       // 测试 createTime 不匹配
       userWalletMapper.insert(cloneIgnoreId(dbUserWallet, o -> o.setCreateTime(null)));
       // 准备参数
       UserWalletExportReqVO reqVO = new UserWalletExportReqVO();
       reqVO.setUserId(null);
       reqVO.setName(null);
       reqVO.setAddress(null);
       reqVO.setNet(null);
       reqVO.setSymbol(null);
       reqVO.setBalance(null);
       reqVO.setCreateTime((new LocalDateTime[]{}));

       // 调用
       List<UserWalletDO> list = userWalletService.getUserWalletList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbUserWallet, list.get(0));
    }

}
