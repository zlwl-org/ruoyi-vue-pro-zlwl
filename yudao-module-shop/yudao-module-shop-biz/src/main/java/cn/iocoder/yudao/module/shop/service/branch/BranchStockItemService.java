package cn.iocoder.yudao.module.shop.service.branch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.BranchStockItemCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.BranchStockItemExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.BranchStockItemPageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.BranchStockItemUpdateReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockItemDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 门店出入库明细 Service 接口
 *
 * @author ruanzh
 */
public interface BranchStockItemService {

    /**
     * 创建门店出入库明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBranchStockItem(@Valid BranchStockItemCreateReqVO createReqVO);

    /**
     * 更新门店出入库明细
     *
     * @param updateReqVO 更新信息
     */
    void updateBranchStockItem(@Valid BranchStockItemUpdateReqVO updateReqVO);

    /**
     * 删除门店出入库明细
     *
     * @param id 编号
     */
    void deleteBranchStockItem(Long id);

    /**
     * 获得门店出入库明细
     *
     * @param id 编号
     * @return 门店出入库明细
     */
    BranchStockItemDO getBranchStockItem(Long id);

    /**
     * 获得门店出入库明细列表
     *
     * @param ids 编号
     * @return 门店出入库明细列表
     */
    List<BranchStockItemDO> getBranchStockItemList(Collection<Long> ids);

    /**
     * 获得门店出入库明细分页
     *
     * @param pageReqVO 分页查询
     * @return 门店出入库明细分页
     */
    PageResult<BranchStockItemDO> getBranchStockItemPage(BranchStockItemPageReqVO pageReqVO);

    /**
     * 获得门店出入库明细列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 门店出入库明细列表
     */
    List<BranchStockItemDO> getBranchStockItemList(BranchStockItemExportReqVO exportReqVO);

}
