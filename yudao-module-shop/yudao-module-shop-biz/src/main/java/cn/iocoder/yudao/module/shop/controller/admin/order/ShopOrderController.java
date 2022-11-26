package cn.iocoder.yudao.module.shop.controller.admin.order;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.order.vo.*;
import cn.iocoder.yudao.module.shop.convert.member.ShopMemberConvert;
import cn.iocoder.yudao.module.shop.convert.order.ShopOrderConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.order.ShopOrderDO;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberService;
import cn.iocoder.yudao.module.shop.service.order.ShopOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "管理后台 - 门店订单")
@RestController
@RequestMapping("/shop/order")
@Validated
public class ShopOrderController {

    @Resource
    private ShopOrderService orderService;

    @Resource
    private ShopMemberService memberService;

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
        ShopOrderRespVO vo = ShopOrderConvert.INSTANCE.convert(order);
        if (order.getMemberId()!= null) {
            ShopMemberDO member = memberService.getMember(order.getMemberId());
            vo.setMember(ShopMemberConvert.INSTANCE.convert(member));
        }
        return success(vo);
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
