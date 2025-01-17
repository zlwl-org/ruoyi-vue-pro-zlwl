package cn.iocoder.yudao.module.shop.service.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemPageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderItemUpdateReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderItemDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 门店订单明细 Service 接口
 *
 * @author ruanzh
 */
public interface ShopOrderItemService {

    /**
     * 创建门店订单明细
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrderItem(@Valid ShopOrderItemCreateReqVO createReqVO);

    /**
     * 更新门店订单明细
     *
     * @param updateReqVO 更新信息
     */
    void updateOrderItem(@Valid ShopOrderItemUpdateReqVO updateReqVO);

    /**
     * 删除门店订单明细
     *
     * @param id 编号
     */
    void deleteOrderItem(Long id);

    /**
     * 获得门店订单明细
     *
     * @param id 编号
     * @return 门店订单明细
     */
    ShopOrderItemDO getOrderItem(Long id);

    /**
     * 获得门店订单明细列表
     *
     * @param ids 编号
     * @return 门店订单明细列表
     */
    List<ShopOrderItemDO> getOrderItemList(Collection<Long> ids);

    /**
     * 获得门店订单明细分页
     *
     * @param pageReqVO 分页查询
     * @return 门店订单明细分页
     */
    PageResult<ShopOrderItemDO> getOrderItemPage(ShopOrderItemPageReqVO pageReqVO);

    /**
     * 获得门店订单明细列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 门店订单明细列表
     */
    List<ShopOrderItemDO> getOrderItemList(ShopOrderItemExportReqVO exportReqVO);

    /**
     * 获得订单商品明细
     * @param id 订单编号
     * @return
     */
    List<ShopOrderItemDO> getOrderItemList(Long id);
}
