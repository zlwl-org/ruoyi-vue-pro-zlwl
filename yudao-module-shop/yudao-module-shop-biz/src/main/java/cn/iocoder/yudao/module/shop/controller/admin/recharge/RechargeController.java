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
import cn.iocoder.yudao.module.shop.dal.dataobject.recharge.RechargeDO;
import cn.iocoder.yudao.module.shop.convert.recharge.RechargeConvert;
import cn.iocoder.yudao.module.shop.service.recharge.RechargeService;

@Api(tags = "管理后台 - 充值活动")
@RestController
@RequestMapping("/shop/recharge")
@Validated
public class RechargeController {

    @Resource
    private RechargeService rechargeService;

    @PostMapping("/create")
    @ApiOperation("创建充值活动")
    @PreAuthorize("@ss.hasPermission('shop:recharge:create')")
    public CommonResult<Long> createRecharge(@Valid @RequestBody RechargeCreateReqVO createReqVO) {
        return success(rechargeService.createRecharge(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新充值活动")
    @PreAuthorize("@ss.hasPermission('shop:recharge:update')")
    public CommonResult<Boolean> updateRecharge(@Valid @RequestBody RechargeUpdateReqVO updateReqVO) {
        rechargeService.updateRecharge(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除充值活动")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:recharge:delete')")
    public CommonResult<Boolean> deleteRecharge(@RequestParam("id") Long id) {
        rechargeService.deleteRecharge(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得充值活动")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:recharge:query')")
    public CommonResult<RechargeRespVO> getRecharge(@RequestParam("id") Long id) {
        RechargeDO recharge = rechargeService.getRecharge(id);
        return success(RechargeConvert.INSTANCE.convert(recharge));
    }

    @GetMapping("/list")
    @ApiOperation("获得充值活动列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:recharge:query')")
    public CommonResult<List<RechargeRespVO>> getRechargeList(@RequestParam("ids") Collection<Long> ids) {
        List<RechargeDO> list = rechargeService.getRechargeList(ids);
        return success(RechargeConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得充值活动分页")
    @PreAuthorize("@ss.hasPermission('shop:recharge:query')")
    public CommonResult<PageResult<RechargeRespVO>> getRechargePage(@Valid RechargePageReqVO pageVO) {
        PageResult<RechargeDO> pageResult = rechargeService.getRechargePage(pageVO);
        return success(RechargeConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出充值活动 Excel")
    @PreAuthorize("@ss.hasPermission('shop:recharge:export')")
    @OperateLog(type = EXPORT)
    public void exportRechargeExcel(@Valid RechargeExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<RechargeDO> list = rechargeService.getRechargeList(exportReqVO);
        // 导出 Excel
        List<RechargeExcelVO> datas = RechargeConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "充值活动.xls", "数据", RechargeExcelVO.class, datas);
    }

}
