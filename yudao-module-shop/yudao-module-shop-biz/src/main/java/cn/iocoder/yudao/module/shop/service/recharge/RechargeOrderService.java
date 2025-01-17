package cn.iocoder.yudao.module.shop.service.recharge;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderPageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderUpdateReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

/**
 * 充值订单 Service 接口
 *
 * @author ruanzh
 */
public interface RechargeOrderService {

    /**
     * 创建充值订单
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRechargeOrder(@Valid RechargeOrderCreateReqVO createReqVO);

    /**
     * 更新充值订单
     *
     * @param updateReqVO 更新信息
     */
    void updateRechargeOrder(@Valid RechargeOrderUpdateReqVO updateReqVO);

    /**
     * 删除充值订单
     *
     * @param id 编号
     */
    void deleteRechargeOrder(Long id);

    /**
     * 获得充值订单
     *
     * @param id 编号
     * @return 充值订单
     */
    RechargeOrderDO getRechargeOrder(Long id);

    /**
     * 获得充值订单列表
     *
     * @param ids 编号
     * @return 充值订单列表
     */
    List<RechargeOrderDO> getRechargeOrderList(Collection<Long> ids);

    /**
     * 获得充值订单分页
     *
     * @param pageReqVO 分页查询
     * @return 充值订单分页
     */
    PageResult<RechargeOrderDO> getRechargeOrderPage(RechargeOrderPageReqVO pageReqVO);

    /**
     * 获得充值订单列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 充值订单列表
     */
    List<RechargeOrderDO> getRechargeOrderList(RechargeOrderExportReqVO exportReqVO);

    List<RechargeOrderDO> todayOrder();

    List<RechargeOrderDO> shopOrders();
}
