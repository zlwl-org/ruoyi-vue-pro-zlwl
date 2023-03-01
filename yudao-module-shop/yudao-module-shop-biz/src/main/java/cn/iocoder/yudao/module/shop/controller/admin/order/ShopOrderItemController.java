package cn.iocoder.yudao.module.shop.controller.admin.order;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.convert.order.ShopOrderItemConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderItemDO;
import cn.iocoder.yudao.module.shop.service.order.ShopOrderItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.EXPORT;

@Tag(name = "管理后台 - 门店订单明细")
@RestController
@RequestMapping("/shop/order-item")
@Validated
public class ShopOrderItemController {

    @Resource
    private ShopOrderItemService orderItemService;

    @PostMapping("/create")
    @Operation(summary = "创建门店订单明细")
    @PreAuthorize("@ss.hasPermission('shop:order-item:create')")
    public CommonResult<Long> createOrderItem(@Valid @RequestBody ShopOrderItemCreateReqVO createReqVO) {
        return success(orderItemService.createOrderItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店订单明细")
    @PreAuthorize("@ss.hasPermission('shop:order-item:update')")
    public CommonResult<Boolean> updateOrderItem(@Valid @RequestBody ShopOrderItemUpdateReqVO updateReqVO) {
        orderItemService.updateOrderItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店订单明细")
//    //@ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:order-item:delete')")
    public CommonResult<Boolean> deleteOrderItem(@RequestParam("id") Long id) {
        orderItemService.deleteOrderItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店订单明细")
//    //@ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:order-item:query')")
    public CommonResult<ShopOrderItemRespVO> getOrderItem(@RequestParam("id") Long id) {
        ShopOrderItemDO orderItem = orderItemService.getOrderItem(id);
        return success(ShopOrderItemConvert.INSTANCE.convert(orderItem));
    }

    @GetMapping("/list")
    @Operation(summary = "获得门店订单明细列表")
//    //@ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:order-item:query')")
    public CommonResult<List<ShopOrderItemRespVO>> getOrderItemList(@RequestParam("ids") Collection<Long> ids) {
        List<ShopOrderItemDO> list = orderItemService.getOrderItemList(ids);
        return success(ShopOrderItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门店订单明细分页")
    @PreAuthorize("@ss.hasPermission('shop:order-item:query')")
    public CommonResult<PageResult<ShopOrderItemRespVO>> getOrderItemPage(@Valid ShopOrderItemPageReqVO pageVO) {
        PageResult<ShopOrderItemDO> pageResult = orderItemService.getOrderItemPage(pageVO);
        return success(ShopOrderItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出门店订单明细 Excel")
    @PreAuthorize("@ss.hasPermission('shop:order-item:export')")
    @OperateLog(type = EXPORT)
    public void exportOrderItemExcel(@Valid ShopOrderItemExportReqVO exportReqVO,
                                     HttpServletResponse response) throws IOException {
        List<ShopOrderItemDO> list = orderItemService.getOrderItemList(exportReqVO);
        // 导出 Excel
        List<ShopOrderItemExcelVO> datas = ShopOrderItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "门店订单明细.xls", "数据", ShopOrderItemExcelVO.class, datas);
    }

}
