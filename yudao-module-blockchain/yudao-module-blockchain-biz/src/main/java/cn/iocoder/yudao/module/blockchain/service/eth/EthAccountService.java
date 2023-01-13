package cn.iocoder.yudao.module.blockchain.service.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountPageReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthAccountUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 以太坊账户 Service 接口
 *
 * @author ruanzh
 */
public interface EthAccountService {

    /**
     * 创建以太坊账户
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEthAccount(@Valid EthAccountCreateReqVO createReqVO);

    /**
     * 更新以太坊账户
     *
     * @param updateReqVO 更新信息
     */
    void updateEthAccount(@Valid EthAccountUpdateReqVO updateReqVO);

    /**
     * 删除以太坊账户
     *
     * @param id 编号
     */
    void deleteEthAccount(Long id);

    /**
     * 获得以太坊账户
     *
     * @param id 编号
     * @return 以太坊账户
     */
    EthAccountDO getEthAccount(Long id);

    /**
     * 获得以太坊账户列表
     *
     * @param ids 编号
     * @return 以太坊账户列表
     */
    List<EthAccountDO> getEthAccountList(Collection<Long> ids);

    /**
     * 获得以太坊账户分页
     *
     * @param pageReqVO 分页查询
     * @return 以太坊账户分页
     */
    PageResult<EthAccountDO> getEthAccountPage(EthAccountPageReqVO pageReqVO);

    /**
     * 获得以太坊账户列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 以太坊账户列表
     */
    List<EthAccountDO> getEthAccountList(EthAccountExportReqVO exportReqVO);


    List<EthAccountDO> getEthAccountList(String symbol);

    int updateEthAccountNet(String address, String netSymbol);
}
