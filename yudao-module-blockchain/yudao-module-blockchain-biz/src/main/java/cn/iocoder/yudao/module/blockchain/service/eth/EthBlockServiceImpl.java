package cn.iocoder.yudao.module.blockchain.service.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockPageReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.convert.eth.EthBlockConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthBlockDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.eth.EthBlockMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.ETH_BLOCK_NOT_EXISTS;

/**
 * Eth区块数据 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class EthBlockServiceImpl implements EthBlockService {

    @Resource
    private EthBlockMapper ethBlockMapper;

    @Override
    public Long createEthBlock(EthBlockCreateReqVO createReqVO) {
        // 插入
        EthBlockDO ethBlock = EthBlockConvert.INSTANCE.convert(createReqVO);
        ethBlockMapper.insert(ethBlock);
        // 返回
        return ethBlock.getId();
    }

    @Override
    public void updateEthBlock(EthBlockUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateEthBlockExists(updateReqVO.getId());
        // 更新
        EthBlockDO updateObj = EthBlockConvert.INSTANCE.convert(updateReqVO);
        ethBlockMapper.updateById(updateObj);
    }

    @Override
    public void deleteEthBlock(Long id) {
        // 校验存在
        this.validateEthBlockExists(id);
        // 删除
        ethBlockMapper.deleteById(id);
    }

    private void validateEthBlockExists(Long id) {
        if (ethBlockMapper.selectById(id) == null) {
            throw exception(ETH_BLOCK_NOT_EXISTS);
        }
    }

    @Override
    public EthBlockDO getEthBlock(Long id) {
        return ethBlockMapper.selectById(id);
    }

    @Override
    public List<EthBlockDO> getEthBlockList(Collection<Long> ids) {
        return ethBlockMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EthBlockDO> getEthBlockPage(EthBlockPageReqVO pageReqVO) {
        return ethBlockMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EthBlockDO> getEthBlockList(EthBlockExportReqVO exportReqVO) {
        return ethBlockMapper.selectList(exportReqVO);
    }

    @Override
    public Long save(EthBlockDO ethBlockDO) {
        ethBlockMapper.insert(ethBlockDO);
        return ethBlockDO.getId();
    }

    @Override
    public EthBlockDO getLatest() {
        return ethBlockMapper.selectLatest();
    }

}
