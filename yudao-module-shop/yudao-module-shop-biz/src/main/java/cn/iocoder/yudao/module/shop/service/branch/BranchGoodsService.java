package cn.iocoder.yudao.module.shop.service.branch;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 门店商品 Service 接口
 *
 * @author ruanzh
 */
public interface BranchGoodsService {

    /**
     * 创建门店商品
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createBranchGoods(@Valid BranchGoodsCreateReqVO createReqVO);

    /**
     * 更新门店商品
     *
     * @param updateReqVO 更新信息
     */
    void updateBranchGoods(@Valid BranchGoodsUpdateReqVO updateReqVO);

    /**
     * 删除门店商品
     *
     * @param id 编号
     */
    void deleteBranchGoods(Long id);

    /**
     * 获得门店商品
     *
     * @param id 编号
     * @return 门店商品
     */
    BranchGoodsDO getBranchGoods(Long id);

    /**
     * 获得门店商品列表
     *
     * @param ids 编号
     * @return 门店商品列表
     */
    List<BranchGoodsDO> getBranchGoodsList(Collection<Long> ids);

    /**
     * 获得门店商品分页
     *
     * @param pageReqVO 分页查询
     * @return 门店商品分页
     */
    PageResult<BranchGoodsDO> getBranchGoodsPage(BranchGoodsPageReqVO pageReqVO);

    /**
     * 获得门店商品列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 门店商品列表
     */
    List<BranchGoodsDO> getBranchGoodsList(BranchGoodsExportReqVO exportReqVO);

}
