package cn.iocoder.yudao.module.shop.controller.admin.order;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.convert.member.ShopMemberConvert;
import cn.iocoder.yudao.module.shop.convert.order.ShopOrderConvert;
import cn.iocoder.yudao.module.shop.convert.order.ShopOrderItemConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderItemDO;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberService;
import cn.iocoder.yudao.module.shop.service.order.ShopOrderItemService;
import cn.iocoder.yudao.module.shop.service.order.ShopOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 门店订单")
@RestController
@RequestMapping("/shop/order")
@Validated
public class ShopOrderController {

    @Resource
    private ShopOrderService orderService;

    @Resource
    private ShopMemberService memberService;

    @Resource
    private ShopOrderItemService itemService;

    @PostMapping("/create")
    @Operation(summary = "创建门店订单")
    @PreAuthorize("@ss.hasPermission('shop:order:create')")
    public CommonResult<Long> createOrder(@Valid @RequestBody ShopOrderCreateReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店订单")
    @PreAuthorize("@ss.hasPermission('shop:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody ShopOrderUpdateReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @PutMapping("/pay")
    @Operation(summary = "更新门店订单")
    @PreAuthorize("@ss.hasPermission('shop:order:update')")
    public CommonResult<Boolean> payOrder(@Valid @RequestBody ShopOrderPayVO payVO) {
        orderService.payOrder(payVO);
        return success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消订单")
    @PreAuthorize("@ss.hasPermission('shop:order:update')")
    public CommonResult<Boolean> cancelOrder(@RequestParam("id") Long id) {
        orderService.cancelOrder(id);
        return success(true);
    }

    @PutMapping("/change")
    @Operation(summary = "取消订单")
    @PreAuthorize("@ss.hasPermission('shop:order:update')")
    public CommonResult<Boolean> changeOrder(@RequestParam("id") Long id) {
        orderService.changeOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店订单")
    @PreAuthorize("@ss.hasPermission('shop:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店订单")
    @PreAuthorize("@ss.hasPermission('shop:order:query')")
    public CommonResult<ShopOrderRespVO> getOrder(@RequestParam("id") Long id) {
        ShopOrderDO order = orderService.getOrder(id);
        ShopOrderRespVO vo = ShopOrderConvert.INSTANCE.convert(order);
        if (order.getMemberId() != null) {
            ShopMemberDO member = memberService.getMember(order.getMemberId());
            vo.setMember(ShopMemberConvert.INSTANCE.convert(member));
        }
        List<ShopOrderItemDO> orderItemList = itemService.getOrderItemList(id);
        List<ShopOrderItemRespVO> items = new ArrayList<>(orderItemList.size());
        for (ShopOrderItemDO item : orderItemList) {
            items.add(ShopOrderItemConvert.INSTANCE.convert(item));
        }
        vo.setItems(items);
        return success(vo);
    }

    @GetMapping("/list")
    @Operation(summary = "获得门店订单列表")
    @PreAuthorize("@ss.hasPermission('shop:order:query')")
    public CommonResult<List<ShopOrderRespVO>> getOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<ShopOrderDO> list = orderService.getOrderList(ids);
        return success(ShopOrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门店订单分页")
    @PreAuthorize("@ss.hasPermission('shop:order:query')")
    public CommonResult<PageResult<ShopOrderRespVO>> getOrderPage(@Valid ShopOrderPageReqVO pageVO) {
        PageResult<ShopOrderDO> pageResult = orderService.getOrderPage(pageVO);
        return success(ShopOrderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出门店订单 Excel")
    @PreAuthorize("@ss.hasPermission('shop:order:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderExcel(@Valid ShopOrderExportReqVO exportReqVO,
                                 HttpServletResponse response) throws IOException {
        List<ShopOrderDO> list = orderService.getOrderList(exportReqVO);
        // 导出 Excel
        List<ShopOrderExcelVO> datas = ShopOrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "门店订单.xls", "数据", ShopOrderExcelVO.class, datas);
    }

}
