package cn.iocoder.yudao.module.blockchain.service.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountPageReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.convert.eth.EthAccountConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;
import cn.iocoder.yudao.module.blockchain.dal.mysql.eth.EthAccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.blockchain.enums.ErrorCodeConstants.ETH_ACCOUNT_NOT_EXISTS;

/**
 * 以太坊账户 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
public class EthAccountServiceImpl implements EthAccountService {

    @Resource
    private EthAccountMapper ethAccountMapper;

    @Override
    public Long createEthAccount(EthAccountCreateReqVO createReqVO) {
        // 插入
        EthAccountDO ethAccount = EthAccountConvert.INSTANCE.convert(createReqVO);
        ethAccountMapper.insert(ethAccount);
        // 返回
        return ethAccount.getId();
    }

    @Override
    public void updateEthAccount(EthAccountUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateEthAccountExists(updateReqVO.getId());
        // 更新
        EthAccountDO updateObj = EthAccountConvert.INSTANCE.convert(updateReqVO);
        ethAccountMapper.updateById(updateObj);
    }

    @Override
    public void deleteEthAccount(Long id) {
        // 校验存在
        this.validateEthAccountExists(id);
        // 删除
        ethAccountMapper.deleteById(id);
    }

    private void validateEthAccountExists(Long id) {
        if (ethAccountMapper.selectById(id) == null) {
            throw exception(ETH_ACCOUNT_NOT_EXISTS);
        }
    }

    @Override
    public EthAccountDO getEthAccount(Long id) {
        return ethAccountMapper.selectById(id);
    }

    @Override
    public List<EthAccountDO> getEthAccountList(Collection<Long> ids) {
        return ethAccountMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<EthAccountDO> getEthAccountPage(EthAccountPageReqVO pageReqVO) {
        return ethAccountMapper.selectPage(pageReqVO);
    }

    @Override
    public List<EthAccountDO> getEthAccountList(EthAccountExportReqVO exportReqVO) {
        return ethAccountMapper.selectList(exportReqVO);
    }

    @Override
    public List<EthAccountDO> getEthAccountList(String symbol) {
        return ethAccountMapper.selectListLimit100(symbol);
    }

    @Override
    public int updateEthAccountNet(String address, String netSymbol) {
        return ethAccountMapper.updateNet(address, netSymbol);
    }

}
