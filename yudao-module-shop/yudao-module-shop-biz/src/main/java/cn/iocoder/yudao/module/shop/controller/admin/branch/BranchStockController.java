package cn.iocoder.yudao.module.shop.controller.admin.branch;

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

import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockDO;
import cn.iocoder.yudao.module.shop.convert.branch.BranchStockConvert;
import cn.iocoder.yudao.module.shop.service.branch.BranchStockService;

@Api(tags = "管理后台 - 门店出入库")
@RestController
@RequestMapping("/shop/branch-stock")
@Validated
public class BranchStockController {

    @Resource
    private BranchStockService branchStockService;

    @PostMapping("/create")
    @ApiOperation("创建门店出入库")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:create')")
    public CommonResult<Long> createBranchStock(@Valid @RequestBody BranchStockCreateReqVO createReqVO) {
        return success(branchStockService.createBranchStock(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新门店出入库")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:update')")
    public CommonResult<Boolean> updateBranchStock(@Valid @RequestBody BranchStockUpdateReqVO updateReqVO) {
        branchStockService.updateBranchStock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除门店出入库")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:delete')")
    public CommonResult<Boolean> deleteBranchStock(@RequestParam("id") Long id) {
        branchStockService.deleteBranchStock(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得门店出入库")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<BranchStockRespVO> getBranchStock(@RequestParam("id") Long id) {
        BranchStockDO branchStock = branchStockService.getBranchStock(id);
        return success(BranchStockConvert.INSTANCE.convert(branchStock));
    }

    @GetMapping("/list")
    @ApiOperation("获得门店出入库列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<List<BranchStockRespVO>> getBranchStockList(@RequestParam("ids") Collection<Long> ids) {
        List<BranchStockDO> list = branchStockService.getBranchStockList(ids);
        return success(BranchStockConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得门店出入库分页")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<PageResult<BranchStockRespVO>> getBranchStockPage(@Valid BranchStockPageReqVO pageVO) {
        PageResult<BranchStockDO> pageResult = branchStockService.getBranchStockPage(pageVO);
        return success(BranchStockConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出门店出入库 Excel")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:export')")
    @OperateLog(type = EXPORT)
    public void exportBranchStockExcel(@Valid BranchStockExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BranchStockDO> list = branchStockService.getBranchStockList(exportReqVO);
        // 导出 Excel
        List<BranchStockExcelVO> datas = BranchStockConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "门店出入库.xls", "数据", BranchStockExcelVO.class, datas);
    }

}
