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
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;
import cn.iocoder.yudao.module.shop.convert.branch.BranchGoodsConvert;
import cn.iocoder.yudao.module.shop.service.branch.BranchGoodsService;

@Api(tags = "管理后台 - 门店商品")
@RestController
@RequestMapping("/shop/branch-goods")
@Validated
public class BranchGoodsController {

    @Resource
    private BranchGoodsService branchGoodsService;

    @PostMapping("/create")
    @ApiOperation("创建门店商品")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:create')")
    public CommonResult<Long> createBranchGoods(@Valid @RequestBody BranchGoodsCreateReqVO createReqVO) {
        return success(branchGoodsService.createBranchGoods(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新门店商品")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:update')")
    public CommonResult<Boolean> updateBranchGoods(@Valid @RequestBody BranchGoodsUpdateReqVO updateReqVO) {
        branchGoodsService.updateBranchGoods(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除门店商品")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:delete')")
    public CommonResult<Boolean> deleteBranchGoods(@RequestParam("id") Long id) {
        branchGoodsService.deleteBranchGoods(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得门店商品")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:query')")
    public CommonResult<BranchGoodsRespVO> getBranchGoods(@RequestParam("id") Long id) {
        BranchGoodsDO branchGoods = branchGoodsService.getBranchGoods(id);
        return success(BranchGoodsConvert.INSTANCE.convert(branchGoods));
    }

    @GetMapping("/list")
    @ApiOperation("获得门店商品列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:query')")
    public CommonResult<List<BranchGoodsRespVO>> getBranchGoodsList(@RequestParam("ids") Collection<Long> ids) {
        List<BranchGoodsDO> list = branchGoodsService.getBranchGoodsList(ids);
        return success(BranchGoodsConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得门店商品分页")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:query')")
    public CommonResult<PageResult<BranchGoodsRespVO>> getBranchGoodsPage(@Valid BranchGoodsPageReqVO pageVO) {
        PageResult<BranchGoodsDO> pageResult = branchGoodsService.getBranchGoodsPage(pageVO);
        return success(BranchGoodsConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出门店商品 Excel")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:export')")
    @OperateLog(type = EXPORT)
    public void exportBranchGoodsExcel(@Valid BranchGoodsExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BranchGoodsDO> list = branchGoodsService.getBranchGoodsList(exportReqVO);
        // 导出 Excel
        List<BranchGoodsExcelVO> datas = BranchGoodsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "门店商品.xls", "数据", BranchGoodsExcelVO.class, datas);
    }

}
