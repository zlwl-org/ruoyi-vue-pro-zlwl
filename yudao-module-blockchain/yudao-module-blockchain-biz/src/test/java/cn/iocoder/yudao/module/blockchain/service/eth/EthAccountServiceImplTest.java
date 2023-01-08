package cn.iocoder.yudao.module.blockchain.service.eth;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.eth.EthAccountMapper;
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
* {@link EthAccountServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(EthAccountServiceImpl.class)
public class EthAccountServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EthAccountServiceImpl ethAccountService;

    @Resource
    private EthAccountMapper ethAccountMapper;

    @Test
    public void testCreateEthAccount_success() {
        // 准备参数
        EthAccountCreateReqVO reqVO = randomPojo(EthAccountCreateReqVO.class);

        // 调用
        Long ethAccountId = ethAccountService.createEthAccount(reqVO);
        // 断言
        assertNotNull(ethAccountId);
        // 校验记录的属性是否正确
        EthAccountDO ethAccount = ethAccountMapper.selectById(ethAccountId);
        assertPojoEquals(reqVO, ethAccount);
    }

    @Test
    public void testUpdateEthAccount_success() {
        // mock 数据
        EthAccountDO dbEthAccount = randomPojo(EthAccountDO.class);
        ethAccountMapper.insert(dbEthAccount);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EthAccountUpdateReqVO reqVO = randomPojo(EthAccountUpdateReqVO.class, o -> {
            o.setId(dbEthAccount.getId()); // 设置更新的 ID
        });

        // 调用
        ethAccountService.updateEthAccount(reqVO);
        // 校验是否更新正确
        EthAccountDO ethAccount = ethAccountMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, ethAccount);
    }

    @Test
    public void testUpdateEthAccount_notExists() {
        // 准备参数
        EthAccountUpdateReqVO reqVO = randomPojo(EthAccountUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> ethAccountService.updateEthAccount(reqVO), ETH_ACCOUNT_NOT_EXISTS);
    }

    @Test
    public void testDeleteEthAccount_success() {
        // mock 数据
        EthAccountDO dbEthAccount = randomPojo(EthAccountDO.class);
        ethAccountMapper.insert(dbEthAccount);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEthAccount.getId();

        // 调用
        ethAccountService.deleteEthAccount(id);
       // 校验数据不存在了
       assertNull(ethAccountMapper.selectById(id));
    }

    @Test
    public void testDeleteEthAccount_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> ethAccountService.deleteEthAccount(id), ETH_ACCOUNT_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEthAccountPage() {
       // mock 数据
       EthAccountDO dbEthAccount = randomPojo(EthAccountDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setAddress(null);
           o.setMnemonic(null);
           o.setPrivateKey(null);
           o.setOwned(null);
           o.setCreateTime(null);
       });
       ethAccountMapper.insert(dbEthAccount);
       // 测试 name 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setName(null)));
       // 测试 address 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setAddress(null)));
       // 测试 mnemonic 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setMnemonic(null)));
       // 测试 privateKey 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setPrivateKey(null)));
       // 测试 owned 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setOwned(null)));
       // 测试 createTime 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setCreateTime(null)));
       // 准备参数
       EthAccountPageReqVO reqVO = new EthAccountPageReqVO();
       reqVO.setName(null);
       reqVO.setAddress(null);
       reqVO.setMnemonic(null);
       reqVO.setPrivateKey(null);
       reqVO.setOwned(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       PageResult<EthAccountDO> pageResult = ethAccountService.getEthAccountPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEthAccount, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEthAccountList() {
       // mock 数据
       EthAccountDO dbEthAccount = randomPojo(EthAccountDO.class, o -> { // 等会查询到
           o.setName(null);
           o.setAddress(null);
           o.setMnemonic(null);
           o.setPrivateKey(null);
           o.setOwned(null);
           o.setCreateTime(null);
       });
       ethAccountMapper.insert(dbEthAccount);
       // 测试 name 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setName(null)));
       // 测试 address 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setAddress(null)));
       // 测试 mnemonic 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setMnemonic(null)));
       // 测试 privateKey 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setPrivateKey(null)));
       // 测试 owned 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setOwned(null)));
       // 测试 createTime 不匹配
       ethAccountMapper.insert(cloneIgnoreId(dbEthAccount, o -> o.setCreateTime(null)));
       // 准备参数
       EthAccountExportReqVO reqVO = new EthAccountExportReqVO();
       reqVO.setName(null);
       reqVO.setAddress(null);
       reqVO.setMnemonic(null);
       reqVO.setPrivateKey(null);
       reqVO.setOwned(null);
       reqVO.setCreateTime((new Date[]{}));

       // 调用
       List<EthAccountDO> list = ethAccountService.getEthAccountList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEthAccount, list.get(0));
    }

}
