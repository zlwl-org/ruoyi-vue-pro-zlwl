package cn.iocoder.yudao.module.shop.service.member;

import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogCreateReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

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
        MemberAccountLogCreateReqVO createReqVO= new MemberAccountLogCreateReqVO();
        createReqVO.setAction("recharge");
        createReqVO.setRelatedId(rechargeOrder.getId());
        createReqVO.setMemberId(rechargeOrder.getMemberId());
        createReqVO.setBalance(rechargeOrder.getAmount());

        if (bestRecharge != null){
            createReqVO.setGift(bestRecharge.getGift());
            createReqVO.setPoint(bestRecharge.getPoint());
            createReqVO.setGrowth(bestRecharge.getGrowth());
        } else {
            createReqVO.setGift(BigDecimal.ZERO);
            createReqVO.setPoint(BigDecimal.ZERO);
            createReqVO.setGrowth(BigDecimal.ZERO);
        }
//        createReqVO.setInfo("");


        memberAccountLogService.createMemberAccountLog(createReqVO);

        // 更新主表
        int i = memberService.updateMemberAccount(createReqVO.getBalance(), createReqVO.getGift(), createReqVO.getPoint(),
                createReqVO.getGrowth(), createReqVO.getMemberId());

    }

    @Override
    public void shopping(ShopOrderDO shopOrder, ShopMemberDO member) {

        // 生成流水
        MemberAccountLogCreateReqVO createReqVO= new MemberAccountLogCreateReqVO();
        createReqVO.setAction("shopping");
        createReqVO.setRelatedId(shopOrder.getId());
        createReqVO.setMemberId(shopOrder.getMemberId());
        createReqVO.setPoint(BigDecimal.ZERO);
        createReqVO.setGrowth(BigDecimal.ZERO);
        if (member.getGift().compareTo(shopOrder.getBalancePay()) == -1) {
            createReqVO.setGift(member.getGift().negate());
            createReqVO.setBalance(shopOrder.getBalancePay().subtract(member.getGift()));

        } else {
            createReqVO.setGift(shopOrder.getBalancePay().negate());
            createReqVO.setBalance(BigDecimal.ZERO);
        }
        memberAccountLogService.createMemberAccountLog(createReqVO);

        // 更新主表
        int i = memberService.updateMemberAccount(createReqVO.getBalance(), createReqVO.getGift(), createReqVO.getPoint(),
                createReqVO.getGrowth(), createReqVO.getMemberId());

    }
}
