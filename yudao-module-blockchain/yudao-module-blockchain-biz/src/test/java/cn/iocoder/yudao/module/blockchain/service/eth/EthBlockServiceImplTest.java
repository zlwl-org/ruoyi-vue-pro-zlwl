package cn.iocoder.yudao.module.blockchain.service.eth;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.annotation.Resource;

import cn.iocoder.yudao.framework.test.core.ut.BaseDbUnitTest;

import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthBlockDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.eth.EthBlockMapper;
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
* {@link EthBlockServiceImpl} 的单元测试类
*
* @author ruanzh
*/
@Import(EthBlockServiceImpl.class)
public class EthBlockServiceImplTest extends BaseDbUnitTest {

    @Resource
    private EthBlockServiceImpl ethBlockService;

    @Resource
    private EthBlockMapper ethBlockMapper;

    @Test
    public void testCreateEthBlock_success() {
        // 准备参数
        EthBlockCreateReqVO reqVO = randomPojo(EthBlockCreateReqVO.class);

        // 调用
        Long ethBlockId = ethBlockService.createEthBlock(reqVO);
        // 断言
        assertNotNull(ethBlockId);
        // 校验记录的属性是否正确
        EthBlockDO ethBlock = ethBlockMapper.selectById(ethBlockId);
        assertPojoEquals(reqVO, ethBlock);
    }

    @Test
    public void testUpdateEthBlock_success() {
        // mock 数据
        EthBlockDO dbEthBlock = randomPojo(EthBlockDO.class);
        ethBlockMapper.insert(dbEthBlock);// @Sql: 先插入出一条存在的数据
        // 准备参数
        EthBlockUpdateReqVO reqVO = randomPojo(EthBlockUpdateReqVO.class, o -> {
            o.setId(dbEthBlock.getId()); // 设置更新的 ID
        });

        // 调用
        ethBlockService.updateEthBlock(reqVO);
        // 校验是否更新正确
        EthBlockDO ethBlock = ethBlockMapper.selectById(reqVO.getId()); // 获取最新的
        assertPojoEquals(reqVO, ethBlock);
    }

    @Test
    public void testUpdateEthBlock_notExists() {
        // 准备参数
        EthBlockUpdateReqVO reqVO = randomPojo(EthBlockUpdateReqVO.class);

        // 调用, 并断言异常
        assertServiceException(() -> ethBlockService.updateEthBlock(reqVO), ETH_BLOCK_NOT_EXISTS);
    }

    @Test
    public void testDeleteEthBlock_success() {
        // mock 数据
        EthBlockDO dbEthBlock = randomPojo(EthBlockDO.class);
        ethBlockMapper.insert(dbEthBlock);// @Sql: 先插入出一条存在的数据
        // 准备参数
        Long id = dbEthBlock.getId();

        // 调用
        ethBlockService.deleteEthBlock(id);
       // 校验数据不存在了
       assertNull(ethBlockMapper.selectById(id));
    }

    @Test
    public void testDeleteEthBlock_notExists() {
        // 准备参数
        Long id = randomLongId();

        // 调用, 并断言异常
        assertServiceException(() -> ethBlockService.deleteEthBlock(id), ETH_BLOCK_NOT_EXISTS);
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEthBlockPage() {
       // mock 数据
       EthBlockDO dbEthBlock = randomPojo(EthBlockDO.class, o -> { // 等会查询到
           o.setNumber(null);
           o.setHash(null);
           o.setParentHash(null);
           o.setNonce(null);
           o.setSha3uncles(null);
           o.setLogsBloom(null);
           o.setTransactionsRoot(null);
           o.setStateRoot(null);
           o.setReceiptsRoot(null);
           o.setAuthor(null);
           o.setMiner(null);
           o.setMixHash(null);
           o.setDifficulty(null);
           o.setTotalDifficulty(null);
           o.setSize(null);
           o.setGasLimit(null);
           o.setGasUsed(null);
           o.setTimestamp(null);
           o.setBaseFeePerGas(null);
           o.setDone(null);
           o.setInfo(null);
           o.setCreateTime(null);
       });
       ethBlockMapper.insert(dbEthBlock);
       // 测试 number 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setNumber(null)));
       // 测试 hash 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setHash(null)));
       // 测试 parentHash 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setParentHash(null)));
       // 测试 nonce 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setNonce(null)));
       // 测试 sha3uncles 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setSha3uncles(null)));
       // 测试 logsBloom 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setLogsBloom(null)));
       // 测试 transactionsRoot 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setTransactionsRoot(null)));
       // 测试 stateRoot 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setStateRoot(null)));
       // 测试 receiptsRoot 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setReceiptsRoot(null)));
       // 测试 author 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setAuthor(null)));
       // 测试 miner 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setMiner(null)));
       // 测试 mixHash 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setMixHash(null)));
       // 测试 difficulty 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setDifficulty(null)));
       // 测试 totalDifficulty 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setTotalDifficulty(null)));
       // 测试 size 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setSize(null)));
       // 测试 gasLimit 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setGasLimit(null)));
       // 测试 gasUsed 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setGasUsed(null)));
       // 测试 timestamp 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setTimestamp(null)));
       // 测试 baseFeePerGas 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setBaseFeePerGas(null)));
       // 测试 done 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setDone(null)));
       // 测试 info 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setInfo(null)));
       // 测试 createTime 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setCreateTime(null)));
       // 准备参数
       EthBlockPageReqVO reqVO = new EthBlockPageReqVO();
       reqVO.setNumber(null);
       reqVO.setHash(null);
       reqVO.setParentHash(null);
       reqVO.setNonce(null);
       reqVO.setSha3uncles(null);
       reqVO.setLogsBloom(null);
       reqVO.setTransactionsRoot(null);
       reqVO.setStateRoot(null);
       reqVO.setReceiptsRoot(null);
       reqVO.setAuthor(null);
       reqVO.setMiner(null);
       reqVO.setMixHash(null);
       reqVO.setDifficulty(null);
       reqVO.setTotalDifficulty(null);
       reqVO.setSize(null);
       reqVO.setGasLimit(null);
       reqVO.setGasUsed(null);
       reqVO.setTimestamp(null);
       reqVO.setBaseFeePerGas(null);
       reqVO.setDone(null);
       reqVO.setInfo(null);
       reqVO.setCreateTime((new LocalDateTime[]{}));

       // 调用
       PageResult<EthBlockDO> pageResult = ethBlockService.getEthBlockPage(reqVO);
       // 断言
       assertEquals(1, pageResult.getTotal());
       assertEquals(1, pageResult.getList().size());
       assertPojoEquals(dbEthBlock, pageResult.getList().get(0));
    }

    @Test
    @Disabled  // TODO 请修改 null 为需要的值，然后删除 @Disabled 注解
    public void testGetEthBlockList() {
       // mock 数据
       EthBlockDO dbEthBlock = randomPojo(EthBlockDO.class, o -> { // 等会查询到
           o.setNumber(null);
           o.setHash(null);
           o.setParentHash(null);
           o.setNonce(null);
           o.setSha3uncles(null);
           o.setLogsBloom(null);
           o.setTransactionsRoot(null);
           o.setStateRoot(null);
           o.setReceiptsRoot(null);
           o.setAuthor(null);
           o.setMiner(null);
           o.setMixHash(null);
           o.setDifficulty(null);
           o.setTotalDifficulty(null);
           o.setSize(null);
           o.setGasLimit(null);
           o.setGasUsed(null);
           o.setTimestamp(null);
           o.setBaseFeePerGas(null);
           o.setDone(null);
           o.setInfo(null);
           o.setCreateTime(null);
       });
       ethBlockMapper.insert(dbEthBlock);
       // 测试 number 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setNumber(null)));
       // 测试 hash 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setHash(null)));
       // 测试 parentHash 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setParentHash(null)));
       // 测试 nonce 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setNonce(null)));
       // 测试 sha3uncles 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setSha3uncles(null)));
       // 测试 logsBloom 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setLogsBloom(null)));
       // 测试 transactionsRoot 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setTransactionsRoot(null)));
       // 测试 stateRoot 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setStateRoot(null)));
       // 测试 receiptsRoot 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setReceiptsRoot(null)));
       // 测试 author 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setAuthor(null)));
       // 测试 miner 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setMiner(null)));
       // 测试 mixHash 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setMixHash(null)));
       // 测试 difficulty 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setDifficulty(null)));
       // 测试 totalDifficulty 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setTotalDifficulty(null)));
       // 测试 size 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setSize(null)));
       // 测试 gasLimit 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setGasLimit(null)));
       // 测试 gasUsed 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setGasUsed(null)));
       // 测试 timestamp 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setTimestamp(null)));
       // 测试 baseFeePerGas 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setBaseFeePerGas(null)));
       // 测试 done 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setDone(null)));
       // 测试 info 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setInfo(null)));
       // 测试 createTime 不匹配
       ethBlockMapper.insert(cloneIgnoreId(dbEthBlock, o -> o.setCreateTime(null)));
       // 准备参数
       EthBlockExportReqVO reqVO = new EthBlockExportReqVO();
       reqVO.setNumber(null);
       reqVO.setHash(null);
       reqVO.setParentHash(null);
       reqVO.setNonce(null);
       reqVO.setSha3uncles(null);
       reqVO.setLogsBloom(null);
       reqVO.setTransactionsRoot(null);
       reqVO.setStateRoot(null);
       reqVO.setReceiptsRoot(null);
       reqVO.setAuthor(null);
       reqVO.setMiner(null);
       reqVO.setMixHash(null);
       reqVO.setDifficulty(null);
       reqVO.setTotalDifficulty(null);
       reqVO.setSize(null);
       reqVO.setGasLimit(null);
       reqVO.setGasUsed(null);
       reqVO.setTimestamp(null);
       reqVO.setBaseFeePerGas(null);
       reqVO.setDone(null);
       reqVO.setInfo(null);
       reqVO.setCreateTime((new LocalDateTime[]{}));

       // 调用
       List<EthBlockDO> list = ethBlockService.getEthBlockList(reqVO);
       // 断言
       assertEquals(1, list.size());
       assertPojoEquals(dbEthBlock, list.get(0));
    }

}
