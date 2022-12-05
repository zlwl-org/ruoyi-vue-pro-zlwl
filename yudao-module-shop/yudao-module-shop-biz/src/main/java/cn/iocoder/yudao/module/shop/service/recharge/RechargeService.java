package cn.iocoder.yudao.module.shop.service.recharge;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargePageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeUpdateReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * 充值活动 Service 接口
 *
 * @author ZLWL
 */
public interface RechargeService {

    /**
     * 创建充值活动
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRecharge(@Valid RechargeCreateReqVO createReqVO);

    /**
     * 更新充值活动
     *
     * @param updateReqVO 更新信息
     */
    void updateRecharge(@Valid RechargeUpdateReqVO updateReqVO);

    /**
     * 删除充值活动
     *
     * @param id 编号
     */
    void deleteRecharge(Long id);

    /**
     * 获得充值活动
     *
     * @param id 编号
     * @return 充值活动
     */
    RechargeDO getRecharge(Long id);

    /**
     * 获得充值活动列表
     *
     * @param ids 编号
     * @return 充值活动列表
     */
    List<RechargeDO> getRechargeList(Collection<Long> ids);

    /**
     * 获得充值活动分页
     *
     * @param pageReqVO 分页查询
     * @return 充值活动分页
     */
    PageResult<RechargeDO> getRechargePage(RechargePageReqVO pageReqVO);

    /**
     * 获得充值活动列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 充值活动列表
     */
    List<RechargeDO> getRechargeList(RechargeExportReqVO exportReqVO);

    /**
     * 获得所有充值活动列表
     *
     * @return {@link List}<{@link RechargeDO}>
     */
    List<RechargeDO> getAll();

    /**
     * 得到最好的充值方案
     *
     * @param amount 充值金额
     * @return {@link RechargeDO}
     */
    RechargeDO getBestRecharge(BigDecimal amount);

    List<RechargeDO> todayOrder();
}
