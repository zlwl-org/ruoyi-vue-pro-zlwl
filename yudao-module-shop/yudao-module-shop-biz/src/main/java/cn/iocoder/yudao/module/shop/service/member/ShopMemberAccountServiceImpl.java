package cn.iocoder.yudao.module.shop.service.member;

import cn.iocoder.yudao.module.shop.controller.admin.member.vo.MemberAccountLogCreateReqVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import cn.iocoder.yudao.module.shop.service.recharge.RechargeService;
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
    private RechargeService rechargeService;
    @Resource
    private MemberAccountLogService memberAccountLogService;
    @Resource
    private ShopMemberService memberService;

    @Override
    public void recharge(RechargeOrderDO rechargeOrder) {
        // 生成流水
        MemberAccountLogCreateReqVO createReqVO= new MemberAccountLogCreateReqVO();
        createReqVO.setAction("recharge");
        createReqVO.setBalance(rechargeOrder.getAmount());
        createReqVO.setMemberId(rechargeOrder.getMemberId());

        // 获取符合的充值规则
        RechargeDO bestRecharge = rechargeService.getBestRecharge(rechargeOrder.getAmount());
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
        createReqVO.setRelatedId(rechargeOrder.getId());

        memberAccountLogService.createMemberAccountLog(createReqVO);

        // 更新主表
        int i = memberService.updateMemberAccount(createReqVO.getBalance(), createReqVO.getGift(), createReqVO.getPoint(),
                createReqVO.getGrowth(), createReqVO.getMemberId());
        log.info("updateMemberAccount ===>　{}", i);

    }
}
