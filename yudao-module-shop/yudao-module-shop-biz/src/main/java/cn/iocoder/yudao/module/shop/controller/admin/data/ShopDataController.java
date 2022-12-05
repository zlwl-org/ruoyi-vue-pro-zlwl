package cn.iocoder.yudao.module.shop.controller.admin.data;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.shop.controller.admin.data.vo.ShopDataRespVo;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberService;
import cn.iocoder.yudao.module.shop.service.order.ShopOrderService;
import cn.iocoder.yudao.module.shop.service.order.ShopOrderStatusEnum;
import cn.iocoder.yudao.module.shop.service.recharge.RechargeOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Api(tags = "管理后台 - Shop数据")
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
    @ApiOperation("获得Shop数据")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
//    @PreAuthorize("@ss.hasPermission('shop:data:query')")
    public CommonResult<ShopDataRespVo> get() {
        List<ShopMemberDO> newMembers = memberService.todayNewMember();
        List<ShopMemberDO> members = memberService.shopMembers();
        List<ShopOrderDO> todayOrder = orderService.todayOrder();
        List<ShopOrderDO> totalOrder = orderService.shopOrders();
        List<RechargeOrderDO> todayRecharge = rechargeOrderService.todayOrder();
        List<RechargeOrderDO> totalRecharge = rechargeOrderService.shopOrders();
        ShopDataRespVo vo = new ShopDataRespVo();
        vo.setTodayMember(newMembers.size());
        vo.setTotalMember(members.size());
        vo.setTodayOrder(todayOrder.size());
        vo.setTodaySale(todayOrder.stream().filter(item -> item.getOrderStatus().equals(ShopOrderStatusEnum.DONE.getStatus())).map(ShopOrderDO::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        vo.setTotalOrder(totalOrder.size());
        vo.setTotalSale(totalOrder.stream().filter(item -> item.getOrderStatus().equals(ShopOrderStatusEnum.DONE.getStatus())).map(ShopOrderDO::getPrice).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        vo.setTodayRecharge(todayRecharge.stream().filter(item -> item.getPayStatus() == 10).map(RechargeOrderDO::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        vo.setTotalRecharge(totalRecharge.stream().filter(item -> item.getPayStatus() == 10).map(RechargeOrderDO::getAmount).reduce(BigDecimal::add).orElse(BigDecimal.ZERO));
        return success(vo);
    }

}
