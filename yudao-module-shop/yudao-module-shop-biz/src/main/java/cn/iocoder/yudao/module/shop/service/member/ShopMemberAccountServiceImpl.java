package cn.iocoder.yudao.module.shop.service.member;

import cn.hutool.core.util.NumberUtil;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogCreateReqVO;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderPayVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Slf4j
@Service
@Transactional
public class ShopMemberAccountServiceImpl implements ShopMemberAccountService {
    @Resource
    private MemberAccountLogService memberAccountLogService;
    @Resource
    private ShopMemberService memberService;

    @Override
    public void recharge(RechargeOrderDO rechargeOrder, RechargeDO bestRecharge) {
        // 生成流水
        MemberAccountLogCreateReqVO recharge= new MemberAccountLogCreateReqVO();
        recharge.setAction("recharge");
        recharge.setRelatedId(rechargeOrder.getId());
        recharge.setMemberId(rechargeOrder.getMemberId());
        recharge.setBalance(rechargeOrder.getAmount());
        recharge.setGift(BigDecimal.ZERO);

        BigDecimal change;
        if (bestRecharge != null){
            recharge.setPoint(bestRecharge.getPoint());
            recharge.setGrowth(bestRecharge.getGrowth());

            // 符合充值活动，生成赠送流水
            MemberAccountLogCreateReqVO gift= new MemberAccountLogCreateReqVO();
            gift.setAction("recharge_gift");
            gift.setRelatedId(rechargeOrder.getId());
            gift.setMemberId(rechargeOrder.getMemberId());
            gift.setBalance(bestRecharge.getGift());
            gift.setGift(BigDecimal.ZERO);
            gift.setPoint(BigDecimal.ZERO);
            gift.setGrowth(BigDecimal.ZERO);

            memberAccountLogService.createMemberAccountLog(gift);

            change = NumberUtil.add(recharge.getBalance(), gift.getBalance());
        } else {
            recharge.setPoint(BigDecimal.ZERO);
            recharge.setGrowth(BigDecimal.ZERO);

            change = recharge.getBalance();
        }
        memberAccountLogService.createMemberAccountLog(recharge);



        // 更新主表
        int i = memberService.updateMemberBalance(rechargeOrder.getMemberId(),change);

    }

    @Override
    public void shopping(ShopOrderPayVO payVO, ShopMemberDO member) {

        // 生成流水
        MemberAccountLogCreateReqVO accountLog= new MemberAccountLogCreateReqVO();
        accountLog.setAction("shopping");
        accountLog.setRelatedId(payVO.getId());
        accountLog.setMemberId(member.getId());
        accountLog.setPoint(BigDecimal.ZERO);
        accountLog.setGrowth(BigDecimal.ZERO);
        accountLog.setGift(BigDecimal.ZERO);
        accountLog.setBalance(payVO.getAmount().negate());
        memberAccountLogService.createMemberAccountLog(accountLog);

        // 更新主表
        int i = memberService.updateMemberBalance(member.getId(),payVO.getAmount().negate());

    }

    @Override
    public void refund(ShopOrderDO order) {
        // 生成流水
        MemberAccountLogCreateReqVO accountLog= new MemberAccountLogCreateReqVO();
        accountLog.setAction("refund");
        accountLog.setRelatedId(order.getId());
        accountLog.setMemberId(order.getMemberId());
        accountLog.setPoint(BigDecimal.ZERO);
        accountLog.setGrowth(BigDecimal.ZERO);
        accountLog.setGift(BigDecimal.ZERO);
        accountLog.setBalance(order.getBalancePay());
        memberAccountLogService.createMemberAccountLog(accountLog);

        // 更新主表
        int i = memberService.updateMemberBalance(order.getMemberId(), order.getBalancePay());
    }
}
