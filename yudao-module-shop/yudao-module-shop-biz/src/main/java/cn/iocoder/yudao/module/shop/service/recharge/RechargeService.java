package cn.iocoder.yudao.module.shop.service.recharge;

import java.util.*;
import javax.validation.*;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 会员充值套餐 Service 接口
 *
 * @author ZLWL
 */
public interface RechargeService {

    /**
     * 创建会员充值套餐
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createRecharge(@Valid RechargeCreateReqVO createReqVO);

    /**
     * 更新会员充值套餐
     *
     * @param updateReqVO 更新信息
     */
    void updateRecharge(@Valid RechargeUpdateReqVO updateReqVO);

    /**
     * 删除会员充值套餐
     *
     * @param id 编号
     */
    void deleteRecharge(Long id);

    /**
     * 获得会员充值套餐
     *
     * @param id 编号
     * @return 会员充值套餐
     */
    RechargeDO getRecharge(Long id);

    /**
     * 获得会员充值套餐列表
     *
     * @param ids 编号
     * @return 会员充值套餐列表
     */
    List<RechargeDO> getRechargeList(Collection<Long> ids);

    /**
     * 获得会员充值套餐分页
     *
     * @param pageReqVO 分页查询
     * @return 会员充值套餐分页
     */
    PageResult<RechargeDO> getRechargePage(RechargePageReqVO pageReqVO);

    /**
     * 获得会员充值套餐列表, 用于 Excel 导出
     *
     * @param exportReqVO 查询条件
     * @return 会员充值套餐列表
     */
    List<RechargeDO> getRechargeList(RechargeExportReqVO exportReqVO);

}
