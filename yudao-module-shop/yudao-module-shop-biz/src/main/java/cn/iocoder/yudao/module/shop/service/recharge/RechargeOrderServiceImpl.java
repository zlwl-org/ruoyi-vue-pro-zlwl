package cn.iocoder.yudao.module.shop.service.recharge;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

import cn.iocoder.yudao.module.shop.convert.recharge.RechargeOrderConvert;
import cn.iocoder.yudao.module.shop.dal.mysql.recharge.RechargeOrderMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.*;

/**
 * 充值订单 Service 实现类
 *
 * @author ZLWL
 */
@Service
@Validated
public class RechargeOrderServiceImpl implements RechargeOrderService {

    @Resource
    private RechargeOrderMapper rechargeOrderMapper;

    @Override
    public Long createRechargeOrder(RechargeOrderCreateReqVO createReqVO) {
        // 插入
        RechargeOrderDO rechargeOrder = RechargeOrderConvert.INSTANCE.convert(createReqVO);
        rechargeOrderMapper.insert(rechargeOrder);
        // 返回
        return rechargeOrder.getId();
    }

    @Override
    public void updateRechargeOrder(RechargeOrderUpdateReqVO updateReqVO) {
        // 校验存在
        this.validateRechargeOrderExists(updateReqVO.getId());
        // 更新
        RechargeOrderDO updateObj = RechargeOrderConvert.INSTANCE.convert(updateReqVO);
        rechargeOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteRechargeOrder(Long id) {
        // 校验存在
        this.validateRechargeOrderExists(id);
        // 删除
        rechargeOrderMapper.deleteById(id);
    }

    private void validateRechargeOrderExists(Long id) {
        if (rechargeOrderMapper.selectById(id) == null) {
            throw exception(RECHARGE_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public RechargeOrderDO getRechargeOrder(Long id) {
        return rechargeOrderMapper.selectById(id);
    }

    @Override
    public List<RechargeOrderDO> getRechargeOrderList(Collection<Long> ids) {
        return rechargeOrderMapper.selectBatchIds(ids);
    }

    @Override
    public PageResult<RechargeOrderDO> getRechargeOrderPage(RechargeOrderPageReqVO pageReqVO) {
        return rechargeOrderMapper.selectPage(pageReqVO);
    }

    @Override
    public List<RechargeOrderDO> getRechargeOrderList(RechargeOrderExportReqVO exportReqVO) {
        return rechargeOrderMapper.selectList(exportReqVO);
    }

}
