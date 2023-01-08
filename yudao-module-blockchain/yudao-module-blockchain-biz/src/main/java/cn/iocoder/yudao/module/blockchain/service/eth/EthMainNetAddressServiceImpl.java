package cn.iocoder.yudao.module.blockchain.service.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressPageReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.convert.eth.EthMainNetAddressConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthMainNetAddressDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.eth.EthMainNetAddressMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.ETH_MAIN_NET_ADDRESS_NOT_EXISTS;

/**
 * 以太坊主网地址 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class EthMainNetAddressServiceImpl implements EthMainNetAddressService {

    @Resource
    private EthMainNetAddressMapper ethMainNetAddressMapper;

    @Override
    public Long createEthMainNetAddress(EthMainNetAddressCreateReqVO createReqVO) {
        // 插入
        EthMainNetAddressDO ethMainNetAddress = EthMainNetAddressConvert.INSTANCE.convert(createReqVO);
        ethMainNetAddressMapper.insert(ethMainNetAddress);
        // 返回
        return ethMainNetAddress.getId();
    }

    @Override
    public void updateEthMainNetAddress(EthMainNetAddressUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateEthMainNetAddressExists(updateReqVO.getId());
        // 更新
        EthMainNetAddressDO updateObj = EthMainNetAddressConvert.INSTANCE.convert(updateReqVO);
        ethMainNetAddressMapper.updateById(updateObj);
    }

    @Override
    public void deleteEthMainNetAddress(Long id) {
        // 校验存在
        this.validateEthMainNetAddressExists(id);
        // 删除
        ethMainNetAddressMapper.deleteById(id);
    }

    private void validateEthMainNetAddressExists(Long id) {
        if (ethMainNetAddressMapper.selectById(id) == null) {
            throw exception(ETH_MAIN_NET_ADDRESS_NOT_EXISTS);
        }
    }

    @Override
    public EthMainNetAddressDO getEthMainNetAddress(Long id) {
        return ethMainNetAddressMapper.selectById(id);
    }

    @Override
    public List<EthMainNetAddressDO> getEthMainNetAddressList(Collection<Long> ids) {
        return ethMainNetAddressMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EthMainNetAddressDO> getEthMainNetAddressPage(EthMainNetAddressPageReqVO pageReqVO) {
        return ethMainNetAddressMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EthMainNetAddressDO> getEthMainNetAddressList(EthMainNetAddressExportReqVO exportReqVO) {
        return ethMainNetAddressMapper.selectList(exportReqVO);
    }

    @Override
    public EthMainNetAddressDO getLatest() {
        return ethMainNetAddressMapper.selectByLatest();
    }

}
