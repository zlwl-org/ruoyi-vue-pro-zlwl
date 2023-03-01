package cn.iocoder.yudao.module.shop.controller.admin.branch;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.convert.branch.BranchStockItemConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockItemDO;
import cn.iocoder.yudao.module.shop.service.branch.BranchStockItemService;
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

@Tag(name = "管理后台 - 门店出入库明细")
@RestController
@RequestMapping("/shop/branch-stock-item")
@Validated
public class BranchStockItemController {

    @Resource
    private BranchStockItemService branchStockItemService;

    @PostMapping("/create")
    @Operation(summary = "创建门店出入库明细")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:create')")
    public CommonResult<Long> createBranchStockItem(@Valid @RequestBody BranchStockItemCreateReqVO createReqVO) {
        return success(branchStockItemService.createBranchStockItem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店出入库明细")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:update')")
    public CommonResult<Boolean> updateBranchStockItem(@Valid @RequestBody BranchStockItemUpdateReqVO updateReqVO) {
        branchStockItemService.updateBranchStockItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店出入库明细")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:delete')")
    public CommonResult<Boolean> deleteBranchStockItem(@RequestParam("id") Long id) {
        branchStockItemService.deleteBranchStockItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店出入库明细")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<BranchStockItemRespVO> getBranchStockItem(@RequestParam("id") Long id) {
        BranchStockItemDO branchStockItem = branchStockItemService.getBranchStockItem(id);
        return success(BranchStockItemConvert.INSTANCE.convert(branchStockItem));
    }

    @GetMapping("/list")
    @Operation(summary = "获得门店出入库明细列表")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<List<BranchStockItemRespVO>> getBranchStockItemList(@RequestParam("ids") Collection<Long> ids) {
        List<BranchStockItemDO> list = branchStockItemService.getBranchStockItemList(ids);
        return success(BranchStockItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门店出入库明细分页")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock:query')")
    public CommonResult<PageResult<BranchStockItemRespVO>> getBranchStockItemPage(@Valid BranchStockItemPageReqVO pageVO) {
        PageResult<BranchStockItemDO> pageResult = branchStockItemService.getBranchStockItemPage(pageVO);
        return success(BranchStockItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出门店出入库明细 Excel")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:export')")
    @OperateLog(type = EXPORT)
    public void exportBranchStockItemExcel(@Valid BranchStockItemExportReqVO exportReqVO,
                                           HttpServletResponse response) throws IOException {
        List<BranchStockItemDO> list = branchStockItemService.getBranchStockItemList(exportReqVO);
        // 导出 Excel
        List<BranchStockItemExcelVO> datas = BranchStockItemConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "门店出入库明细.xls", "数据", BranchStockItemExcelVO.class, datas);
    }

}
