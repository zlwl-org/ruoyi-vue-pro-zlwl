package cn.iocoder.yudao.module.shop.service.member;


import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;

public interface ShopMemberAccountService {

    void recharge(RechargeOrderDO rechargeOrder);
}
