package cn.iocoder.yudao.module.shop.controller.admin.recharge;

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

import cn.iocoder.yudao.module.shop.controller.admin.recharge.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeOrderDO;
import cn.iocoder.yudao.module.shop.convert.recharge.RechargeOrderConvert;
import cn.iocoder.yudao.module.shop.service.recharge.RechargeOrderService;

@Api(tags = "管理后台 - 充值订单")
@RestController
@RequestMapping("/shop/recharge-order")
@Validated
public class RechargeOrderController {

    @Resource
    private RechargeOrderService rechargeOrderService;

    @PostMapping("/create")
    @ApiOperation("创建充值订单")
    @PreAuthorize("@ss.hasPermission('shop:recharge-order:create')")
    public CommonResult<Long> createRechargeOrder(@Valid @RequestBody RechargeOrderCreateReqVO createReqVO) {
        return success(rechargeOrderService.createRechargeOrder(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新充值订单")
    @PreAuthorize("@ss.hasPermission('shop:recharge-order:update')")
    public CommonResult<Boolean> updateRechargeOrder(@Valid @RequestBody RechargeOrderUpdateReqVO updateReqVO) {
        rechargeOrderService.updateRechargeOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除充值订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:recharge-order:delete')")
    public CommonResult<Boolean> deleteRechargeOrder(@RequestParam("id") Long id) {
        rechargeOrderService.deleteRechargeOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得充值订单")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:recharge-order:query')")
    public CommonResult<RechargeOrderRespVO> getRechargeOrder(@RequestParam("id") Long id) {
        RechargeOrderDO rechargeOrder = rechargeOrderService.getRechargeOrder(id);
        return success(RechargeOrderConvert.INSTANCE.convert(rechargeOrder));
    }

    @GetMapping("/list")
    @ApiOperation("获得充值订单列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:recharge-order:query')")
    public CommonResult<List<RechargeOrderRespVO>> getRechargeOrderList(@RequestParam("ids") Collection<Long> ids) {
        List<RechargeOrderDO> list = rechargeOrderService.getRechargeOrderList(ids);
        return success(RechargeOrderConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得充值订单分页")
    @PreAuthorize("@ss.hasPermission('shop:recharge-order:query')")
    public CommonResult<PageResult<RechargeOrderRespVO>> getRechargeOrderPage(@Valid RechargeOrderPageReqVO pageVO) {
        PageResult<RechargeOrderDO> pageResult = rechargeOrderService.getRechargeOrderPage(pageVO);
        return success(RechargeOrderConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出充值订单 Excel")
    @PreAuthorize("@ss.hasPermission('shop:recharge-order:export')")
    @OperateLog(type = EXPORT)
    public void exportRechargeOrderExcel(@Valid RechargeOrderExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RechargeOrderDO> list = rechargeOrderService.getRechargeOrderList(exportReqVO);
        // 导出 Excel
        List<RechargeOrderExcelVO> datas = RechargeOrderConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "充值订单.xls", "数据", RechargeOrderExcelVO.class, datas);
    }

}
