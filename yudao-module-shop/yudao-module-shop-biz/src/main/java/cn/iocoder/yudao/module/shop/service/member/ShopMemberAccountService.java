package cn.iocoder.yudao.module.shop.service.member;


import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;

public interface ShopMemberAccountService {

    void recharge(RechargeOrderDO rechargeOrder, RechargeDO bestRecharge);

    void shopping(ShopOrderDO shopOrder, ShopMemberDO member);
}
