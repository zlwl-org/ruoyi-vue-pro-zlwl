package cn.iocoder.yudao.module.blockchain.service.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressPageReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthMainNetAddressUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthMainNetAddressDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 以太坊主网地址 Service 接口
 *
 * @author ruanzh
 */
public interface EthMainNetAddressService {

    /**
     * 创建以太坊主网地址
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEthMainNetAddress(@Valid EthMainNetAddressCreateReqVO createReqVO);

    /**
     * 更新以太坊主网地址
     *
     * @param updateReqVO 更新信息
     */
    void updateEthMainNetAddress(@Valid EthMainNetAddressUpdateReqVO updateReqVO);

    /**
     * 删除以太坊主网地址
     *
     * @param id 编号
     */
    void deleteEthMainNetAddress(Long id);

    /**
     * 获得以太坊主网地址
     *
     * @param id 编号
     * @return 以太坊主网地址
     */
    EthMainNetAddressDO getEthMainNetAddress(Long id);

    /**
     * 获得以太坊主网地址列表
     *
     * @param ids 编号
     * @return 以太坊主网地址列表
     */
    List<EthMainNetAddressDO> getEthMainNetAddressList(Collection<Long> ids);

    /**
     * 获得以太坊主网地址分页
     *
     * @param pageReqVO 分页查询
     * @return 以太坊主网地址分页
     */
    PageResult<EthMainNetAddressDO> getEthMainNetAddressPage(EthMainNetAddressPageReqVO pageReqVO);

    /**
     * 获得以太坊主网地址列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 以太坊主网地址列表
     */
    List<EthMainNetAddressDO> getEthMainNetAddressList(EthMainNetAddressExportReqVO exportReqVO);

    EthMainNetAddressDO getLatest();

}
