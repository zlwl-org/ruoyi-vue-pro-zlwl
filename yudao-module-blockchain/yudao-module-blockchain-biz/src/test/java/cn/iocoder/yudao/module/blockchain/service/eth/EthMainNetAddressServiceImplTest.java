package cn.iocoder.yudao.module.blockchain.service.eth;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthMainNetAddressDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.eth.EthMainNetAddressMapper;
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
* {@link EthMainNetAddressServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(EthMainNetAddressServiceImpl.class)
public class EthMainNetAddressServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EthMainNetAddressServiceImpl ethMainNetAddressService;

    @Resource
    private EthMainNetAddressMapper ethMainNetAddressMapper;

    @Test
    public void testCreateEthMainNetAddress_success() {
        // 准备参数
        EthMainNetAddressCreateReqVO reqVO = randomPojo(EthMainNetAddressCreateReqVO.class);

        // 调用
        Long ethMainNetAddressId = ethMainNetAddressService.createEthMainNetAddress(reqVO);
        // 断言
        assertNotNull(ethMainNetAddressId);
        // 校验记录的属性是否正确
        EthMainNetAddressDO ethMainNetAddress = ethMainNetAddressMapper.selectById(ethMainNetAddressId);
        assertPojoEquals(reqVO, ethMainNetAddress);
    }

    @Test
    public void testUpdateEthMainNetAddress_success() {
        // mock 数据
        EthMainNetAddressDO dbEthMainNetAddress = randomPojo(EthMainNetAddressDO.class);
        ethMainNetAddressMapper.insert(dbEthMainNetAddress);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EthMainNetAddressUpdateReqVO reqVO = randomPojo(EthMainNetAddressUpdateReqVO.class, o -> {
            o.setId(dbEthMainNetAddress.getId()); // 设置更新的 ID
        });

        // 调用
        ethMainNetAddressService.updateEthMainNetAddress(reqVO);
        // 校验是否更新正确
        EthMainNetAddressDO ethMainNetAddress = ethMainNetAddressMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, ethMainNetAddress);
    }

    @Test
    public void testUpdateEthMainNetAddress_notExists() {
        // 准备参数
        EthMainNetAddressUpdateReqVO reqVO = randomPojo(EthMainNetAddressUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> ethMainNetAddressService.updateEthMainNetAddress(reqVO), ETH_MAIN_NET_ADDRESS_NOT_EXISTS);
    }

    @Test
    public void testDeleteEthMainNetAddress_success() {
        // mock 数据
        EthMainNetAddressDO dbEthMainNetAddress = randomPojo(EthMainNetAddressDO.class);
        ethMainNetAddressMapper.insert(dbEthMainNetAddress);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEthMainNetAddress.getId();

        // 调用
        ethMainNetAddressService.deleteEthMainNetAddress(id);
       // 校验数据不存在了
       assertNull(ethMainNetAddressMapper.selectById(id));
    }

    @Test
    public void testDeleteEthMainNetAddress_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> ethMainNetAddressService.deleteEthMainNetAddress(id), ETH_MAIN_NET_ADDRESS_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEthMainNetAddressPage() {
       // mock 数据
       EthMainNetAddressDO dbEthMainNetAddress = randomPojo(EthMainNetAddressDO.class, o -> { // 等会查询到
           o.setAddress(null);
           o.setTags(null);
       });
       ethMainNetAddressMapper.insert(dbEthMainNetAddress);
       // 测试 address 不匹配
       ethMainNetAddressMapper.insert(cloneIgnoreId(dbEthMainNetAddress, o -> o.setAddress(null)));
       // 测试 tags 不匹配
       ethMainNetAddressMapper.insert(cloneIgnoreId(dbEthMainNetAddress, o -> o.setTags(null)));
       // 准备参数
       EthMainNetAddressPageReqVO reqVO = new EthMainNetAddressPageReqVO();
       reqVO.setAddress(null);
       reqVO.setTags(null);

       // 调用
       PageResult<EthMainNetAddressDO> pageResult = ethMainNetAddressService.getEthMainNetAddressPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEthMainNetAddress, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEthMainNetAddressList() {
       // mock 数据
       EthMainNetAddressDO dbEthMainNetAddress = randomPojo(EthMainNetAddressDO.class, o -> { // 等会查询到
           o.setAddress(null);
           o.setTags(null);
       });
       ethMainNetAddressMapper.insert(dbEthMainNetAddress);
       // 测试 address 不匹配
       ethMainNetAddressMapper.insert(cloneIgnoreId(dbEthMainNetAddress, o -> o.setAddress(null)));
       // 测试 tags 不匹配
       ethMainNetAddressMapper.insert(cloneIgnoreId(dbEthMainNetAddress, o -> o.setTags(null)));
       // 准备参数
       EthMainNetAddressExportReqVO reqVO = new EthMainNetAddressExportReqVO();
       reqVO.setAddress(null);
       reqVO.setTags(null);

       // 调用
       List<EthMainNetAddressDO> list = ethMainNetAddressService.getEthMainNetAddressList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEthMainNetAddress, list.get(0));
    }

}
