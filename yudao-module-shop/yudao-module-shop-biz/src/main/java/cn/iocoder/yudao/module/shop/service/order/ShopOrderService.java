package cn.iocoder.yudao.module.shop.service.order;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 门店订单 Service 接口
 *
 * @author ruanzh
 */
public interface ShopOrderService {

    /**
     * 创建门店订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createOrder(@Valid ShopOrderCreateReqVO createReqVO);

    /**
     * 更新门店订单
     *
     * @param updateReqVO 更新信息
     */
    void updateOrder(@Valid ShopOrderUpdateReqVO updateReqVO);

    /**
     * 删除门店订单
     *
     * @param id 编号
     */
    void deleteOrder(Long id);

    /**
     * 获得门店订单
     *
     * @param id 编号
     * @return 门店订单
     */
    ShopOrderDO getOrder(Long id);

    /**
     * 获得门店订单列表
     *
     * @param ids 编号
     * @return 门店订单列表
     */
    List<ShopOrderDO> getOrderList(Collection<Long> ids);

    /**
     * 获得门店订单分页
     *
     * @param pageReqVO 分页查询
     * @return 门店订单分页
     */
    PageResult<ShopOrderDO> getOrderPage(ShopOrderPageReqVO pageReqVO);

    /**
     * 获得门店订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 门店订单列表
     */
    List<ShopOrderDO> getOrderList(ShopOrderExportReqVO exportReqVO);

    void payOrder(ShopOrderPayVO payVO);

    void cancelOrder(Long id);
}
