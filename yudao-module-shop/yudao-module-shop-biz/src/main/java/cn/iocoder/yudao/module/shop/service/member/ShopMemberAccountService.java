package cn.iocoder.yudao.module.shop.service.member;


import cn.iocoder.yudao.module.shop.controller.admin.order.vo.ShopOrderPayVO;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;

public interface ShopMemberAccountService {

    void recharge(RechargeOrderDO rechargeOrder, RechargeDO bestRecharge);

    void shopping(ShopOrderPayVO shopOrder, ShopMemberDO member);
}
