package cn.iocoder.yudao.module.shop.controller.admin.branch;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.convert.branch.BranchStockConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockDO;
import cn.iocoder.yudao.module.shop.service.branch.BranchStockService;
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

@Tag(name = "管理后台 - 门店出入库")
@RestController
@RequestMapping("/shop/branch-stock")
@Validated
public class BranchStockController {

    @Resource
    private BranchStockService branchStockService;

    @PostMapping("/create")
    @Operation(summary = "创建门店出入库")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:create')")
    public CommonResult<Long> createBranchStock(@Valid @RequestBody BranchStockCreateReqVO createReqVO) {
        return success(branchStockService.createBranchStock(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店出入库")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:update')")
    public CommonResult<Boolean> updateBranchStock(@Valid @RequestBody BranchStockUpdateReqVO updateReqVO) {
        branchStockService.updateBranchStock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店出入库")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:delete')")
    public CommonResult<Boolean> deleteBranchStock(@RequestParam("id") Long id) {
        branchStockService.deleteBranchStock(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店出入库")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<BranchStockRespVO> getBranchStock(@RequestParam("id") Long id) {
        BranchStockDO branchStock = branchStockService.getBranchStock(id);
        return success(BranchStockConvert.INSTANCE.convert(branchStock));
    }

    @GetMapping("/list")
    @Operation(summary = "获得门店出入库列表")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<List<BranchStockRespVO>> getBranchStockList(@RequestParam("ids") Collection<Long> ids) {
        List<BranchStockDO> list = branchStockService.getBranchStockList(ids);
        return success(BranchStockConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门店出入库分页")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<PageResult<BranchStockRespVO>> getBranchStockPage(@Valid BranchStockPageReqVO pageVO) {
        PageResult<BranchStockDO> pageResult = branchStockService.getBranchStockPage(pageVO);
        return success(BranchStockConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出门店出入库 Excel")
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
