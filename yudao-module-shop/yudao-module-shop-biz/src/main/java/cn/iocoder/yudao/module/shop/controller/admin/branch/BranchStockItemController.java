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
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchStockItemDO;
import cn.iocoder.yudao.module.shop.convert.branch.BranchStockItemConvert;
import cn.iocoder.yudao.module.shop.service.branch.BranchStockItemService;

@Api(tags = "管理后台 - 门店出入库明细")
@RestController
@RequestMapping("/shop/branch-stock-item")
@Validated
public class BranchStockItemController {

    @Resource
    private BranchStockItemService branchStockItemService;

    @PostMapping("/create")
    @ApiOperation("创建门店出入库明细")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:create')")
    public CommonResult<Long> createBranchStockItem(@Valid @RequestBody BranchStockItemCreateReqVO createReqVO) {
        return success(branchStockItemService.createBranchStockItem(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新门店出入库明细")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:update')")
    public CommonResult<Boolean> updateBranchStockItem(@Valid @RequestBody BranchStockItemUpdateReqVO updateReqVO) {
        branchStockItemService.updateBranchStockItem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除门店出入库明细")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:delete')")
    public CommonResult<Boolean> deleteBranchStockItem(@RequestParam("id") Long id) {
        branchStockItemService.deleteBranchStockItem(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得门店出入库明细")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:query')")
    public CommonResult<BranchStockItemRespVO> getBranchStockItem(@RequestParam("id") Long id) {
        BranchStockItemDO branchStockItem = branchStockItemService.getBranchStockItem(id);
        return success(BranchStockItemConvert.INSTANCE.convert(branchStockItem));
    }

    @GetMapping("/list")
    @ApiOperation("获得门店出入库明细列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:query')")
    public CommonResult<List<BranchStockItemRespVO>> getBranchStockItemList(@RequestParam("ids") Collection<Long> ids) {
        List<BranchStockItemDO> list = branchStockItemService.getBranchStockItemList(ids);
        return success(BranchStockItemConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得门店出入库明细分页")
    @PreAuthorize("@ss.hasPermission('shop:branch-stock-item:query')")
    public CommonResult<PageResult<BranchStockItemRespVO>> getBranchStockItemPage(@Valid BranchStockItemPageReqVO pageVO) {
        PageResult<BranchStockItemDO> pageResult = branchStockItemService.getBranchStockItemPage(pageVO);
        return success(BranchStockItemConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出门店出入库明细 Excel")
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
