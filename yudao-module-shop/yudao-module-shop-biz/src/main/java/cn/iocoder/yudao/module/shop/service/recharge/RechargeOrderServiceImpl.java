package cn.iocoder.yudao.module.shop.service.recharge;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderExportReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderPageReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.RechargeOrderUpdateReqVO;
import cn.iocoder.yudao.module.shop.convert.recharge.RechargeOrderConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import cn.iocoder.yudao.module.shop.dal.mysql.recharge.RechargeOrderMapper;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberAccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.shop.enums.ErrorCodeConstants.RECHARGE_ORDER_NOT_EXISTS;

/**
 * 充值订单 Service 实现类
 *
 * @author ruanzh
 */
@Service
@Validated
@Transactional
public class RechargeOrderServiceImpl implements RechargeOrderService {

    @Resource
    private RechargeOrderMapper rechargeOrderMapper;

    @Resource
    private ShopMemberAccountService shopMemberAccountService;
    @Resource
    private RechargeService rechargeService;

    @Override
    public Long createRechargeOrder(RechargeOrderCreateReqVO createReqVO) {
        // 插入
        RechargeOrderDO rechargeOrder = RechargeOrderConvert.INSTANCE.convert(createReqVO);
        // 获取最优的充值活动
        RechargeDO bestRecharge = rechargeService.getBestRecharge(rechargeOrder.getAmount());
        if (bestRecharge != null){
            rechargeOrder.setRechargeId(bestRecharge.getId());
            rechargeOrder.setRechargeName(bestRecharge.getName());
        }
        rechargeOrderMapper.insert(rechargeOrder);

        shopMemberAccountService.recharge(rechargeOrder, bestRecharge);
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
