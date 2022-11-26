package cn.iocoder.yudao.module.shop.controller.admin.order;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import static cn.iocoder.yudao.framework.operatelog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.convert.order.ShopOrderConvert;
import cn.iocoder.yudao.module.shop.service.order.ShopOrderService;

@Api(tags = "管理后台 - 门店订单")
@RestController
@RequestMapping("/shop/order")
@Validated
public class ShopOrderController {

    @Resource
    private ShopOrderService orderService;

    @PostMapping("/create")
    @ApiOperation("创建门店订单")
    @PreAuthorize("@ss.hasPermission('shop:order:create')")
    public CommonResult<Long> createOrder(@Valid @RequestBody ShopOrderCreateReqVO createReqVO) {
        return success(orderService.createOrder(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新门店订单")
    @PreAuthorize("@ss.hasPermission('shop:order:update')")
    public CommonResult<Boolean> updateOrder(@Valid @RequestBody ShopOrderUpdateReqVO updateReqVO) {
        orderService.updateOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除门店订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:order:delete')")
    public CommonResult<Boolean> deleteOrder(@RequestParam("id") Long id) {
        orderService.deleteOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得门店订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:order:query')")
    public CommonResult<ShopOrderRespVO> getOrder(@RequestParam("id") Long id) {
        ShopOrderDO order = orderService.getOrder(id);
        return success(ShopOrderConvert.INSTANCE.convert(order));
    }

    @GetMapping("/list")
    @ApiOperation("获得门店订单列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:order:query')")
    public CommonResult<List<ShopOrderRespVO>> getOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<ShopOrderDO> list = orderService.getOrderList(ids);
        return success(ShopOrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得门店订单分页")
    @PreAuthorize("@ss.hasPermission('shop:order:query')")
    public CommonResult<PageResult<ShopOrderRespVO>> getOrderPage(@Valid ShopOrderPageReqVO pageVO) {
        PageResult<ShopOrderDO> pageResult = orderService.getOrderPage(pageVO);
        return success(ShopOrderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出门店订单 Excel")
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
