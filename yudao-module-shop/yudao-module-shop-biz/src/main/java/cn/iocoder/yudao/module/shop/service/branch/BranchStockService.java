package cn.iocoder.yudao.module.shop.service.branch;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 门店出入库 Service 接口
 *
 * @author ruanzh
 */
public interface BranchStockService {

    /**
     * 创建门店出入库
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBranchStock(@Valid BranchStockCreateReqVO createReqVO);

    /**
     * 更新门店出入库
     *
     * @param updateReqVO 更新信息
     */
    void updateBranchStock(@Valid BranchStockUpdateReqVO updateReqVO);

    /**
     * 删除门店出入库
     *
     * @param id 编号
     */
    void deleteBranchStock(Long id);

    /**
     * 获得门店出入库
     *
     * @param id 编号
     * @return 门店出入库
     */
    BranchStockDO getBranchStock(Long id);

    /**
     * 获得门店出入库列表
     *
     * @param ids 编号
     * @return 门店出入库列表
     */
    List<BranchStockDO> getBranchStockList(Collection<Long> ids);

    /**
     * 获得门店出入库分页
     *
     * @param pageReqVO 分页查询
     * @return 门店出入库分页
     */
    PageResult<BranchStockDO> getBranchStockPage(BranchStockPageReqVO pageReqVO);

    /**
     * 获得门店出入库列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 门店出入库列表
     */
    List<BranchStockDO> getBranchStockList(BranchStockExportReqVO exportReqVO);

}
