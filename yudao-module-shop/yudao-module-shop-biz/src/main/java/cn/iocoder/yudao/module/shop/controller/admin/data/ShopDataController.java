package cn.iocoder.yudao.module.shop.controller.admin.data;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.shop.controller.admin.data.vo.ShopDataRespVo;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import cn.iocoder.yudao.module.shop.enums.ShopOrderStatusEnum;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberService;
import cn.iocoder.yudao.module.shop.service.order.ShopOrderService;
import cn.iocoder.yudao.module.shop.service.recharge.RechargeOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - Shop数据")
@RestController
@RequestMapping("/shop/data")
@Validated
public class ShopDataController {

    @Resource
    private ShopMemberService memberService;
    @Resource
    private ShopOrderService orderService;
    @Resource
    private RechargeOrderService rechargeOrderService;

    @GetMapping("/get")
    @Operation(summary = "获得Shop数据")
//    @PreAuthorize("@ss.hasPermission('shop:data:query')")
    public CommonResult<ShopDataRespVo> get() {
        ShopDataRespVo vo = new ShopDataRespVo();
        // 会员统计
        List<ShopMemberDO> newMembers = memberService.todayNewMember();
        List<ShopMemberDO> members = memberService.shopMembers();
        vo.setTodayMember(newMembers.size());
        vo.setTotalMember(members.size());

        // 订单统计
        List<ShopOrderDO> todayOrder = orderService.todayOrder();
        List<ShopOrderDO> totalOrder = orderService.shopOrders();
        vo.setTodayOrder(todayOrder.size());
        vo.setTodaySale(todayOrder.stream().filter(item -> item.getOrderStatus().equals(ShopOrderStatusEnum.DONE.getStatus())).map(ShopOrderDO::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        vo.setTotalOrder(totalOrder.size());
        vo.setTotalSale(totalOrder.stream().filter(item -> item.getOrderStatus().equals(ShopOrderStatusEnum.DONE.getStatus())).map(ShopOrderDO::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        vo.setTodayConsume(todayOrder.stream().filter(item -> item.getOrderStatus().equals(ShopOrderStatusEnum.DONE.getStatus()) && item.getOrderType().equals("consume")).map(ShopOrderDO::getOrderPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        vo.setTotalConsume(totalOrder.stream().filter(item -> item.getOrderStatus().equals(ShopOrderStatusEnum.DONE.getStatus()) && item.getOrderType().equals("consume")).map(ShopOrderDO::getOrderPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));

        // 充值统计
        List<RechargeOrderDO> todayRecharge = rechargeOrderService.todayOrder();
        List<RechargeOrderDO> totalRecharge = rechargeOrderService.shopOrders();

        vo.setTodayRecharge(todayRecharge.stream().filter(item -> item.getPayStatus() == 10).map(RechargeOrderDO::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        vo.setTotalRecharge(totalRecharge.stream().filter(item -> item.getPayStatus() == 10).map(RechargeOrderDO::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        return success(vo);
    }

}
