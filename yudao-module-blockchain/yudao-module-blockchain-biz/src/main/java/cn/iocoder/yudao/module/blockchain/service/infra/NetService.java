package cn.iocoder.yudao.module.blockchain.service.infra;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.NetCreateReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.NetExportReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.NetPageReqVO;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.NetUpdateReqVO;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.infra.NetDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 网络 Service 接口
 *
 * @author ruanzh
 */
public interface NetService {

    /**
     * 创建网络
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createNet(@Valid NetCreateReqVO createReqVO);

    /**
     * 更新网络
     *
     * @param updateReqVO 更新信息
     */
    void updateNet(@Valid NetUpdateReqVO updateReqVO);

    /**
     * 删除网络
     *
     * @param id 编号
     */
    void deleteNet(Long id);

    /**
     * 获得网络
     *
     * @param id 编号
     * @return 网络
     */
    NetDO getNet(Long id);

    /**
     * 获得网络列表
     *
     * @param ids 编号
     * @return 网络列表
     */
    List<NetDO> getNetList(Collection<Long> ids);

    /**
     * 获得网络分页
     *
     * @param pageReqVO 分页查询
     * @return 网络分页
     */
    PageResult<NetDO> getNetPage(NetPageReqVO pageReqVO);

    /**
     * 获得网络列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 网络列表
     */
    List<NetDO> getNetList(NetExportReqVO exportReqVO);


    NetDO getNet(String symbol);

}
