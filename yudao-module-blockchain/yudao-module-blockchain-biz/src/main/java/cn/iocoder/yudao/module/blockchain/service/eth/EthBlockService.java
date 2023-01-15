package cn.iocoder.yudao.module.blockchain.service.eth;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockPageReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.EthBlockUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthBlockDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * Eth区块数据 Service 接口
 *
 * @author ruanzh
 */
public interface EthBlockService {

    /**
     * 创建Eth区块数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createEthBlock(@Valid EthBlockCreateReqVO createReqVO);

    /**
     * 更新Eth区块数据
     *
     * @param updateReqVO 更新信息
     */
    void updateEthBlock(@Valid EthBlockUpdateReqVO updateReqVO);

    /**
     * 删除Eth区块数据
     *
     * @param id 编号
     */
    void deleteEthBlock(Long id);

    /**
     * 获得Eth区块数据
     *
     * @param id 编号
     * @return Eth区块数据
     */
    EthBlockDO getEthBlock(Long id);

    /**
     * 获得Eth区块数据列表
     *
     * @param ids 编号
     * @return Eth区块数据列表
     */
    List<EthBlockDO> getEthBlockList(Collection<Long> ids);

    /**
     * 获得Eth区块数据分页
     *
     * @param pageReqVO 分页查询
     * @return Eth区块数据分页
     */
    PageResult<EthBlockDO> getEthBlockPage(EthBlockPageReqVO pageReqVO);

    /**
     * 获得Eth区块数据列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return Eth区块数据列表
     */
    List<EthBlockDO> getEthBlockList(EthBlockExportReqVO exportReqVO);


    Long save(EthBlockDO ethBlockDO);

    EthBlockDO getLatest();
}
