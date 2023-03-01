package cn.iocoder.yudao.module.shop.controller.admin.branch;

import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.convert.branch.BranchConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchDO;
import cn.iocoder.yudao.module.shop.service.branch.BranchService;
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

@Tag(name = "管理后台 - 门店")
@RestController
@RequestMapping("/shop/branch")
@Validated
public class BranchController {

    @Resource
    private BranchService branchService;

    @PostMapping("/create")
    @Operation(summary = "创建门店")
    @PreAuthorize("@ss.hasPermission('shop:branch:create')")
    public CommonResult<Long> createBranch(@Valid @RequestBody BranchCreateReqVO createReqVO) {
        return success(branchService.createBranch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店")
    @PreAuthorize("@ss.hasPermission('shop:branch:update')")
    public CommonResult<Boolean> updateBranch(@Valid @RequestBody BranchUpdateReqVO updateReqVO) {
        branchService.updateBranch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店")
    @PreAuthorize("@ss.hasPermission('shop:branch:delete')")
    public CommonResult<Boolean> deleteBranch(@RequestParam("id") Long id) {
        branchService.deleteBranch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店")
    @PreAuthorize("@ss.hasPermission('shop:branch:query')")
    public CommonResult<BranchRespVO> getBranch(@RequestParam("id") Long id) {
        BranchDO branch = branchService.getBranch(id);
        return success(BranchConvert.INSTANCE.convert(branch));
    }

    @GetMapping("/list")
    @Operation(summary = "获得门店列表")
    @PreAuthorize("@ss.hasPermission('shop:branch:query')")
    public CommonResult<List<BranchRespVO>> getBranchList(@RequestParam("ids") Collection<Long> ids) {
        List<BranchDO> list = branchService.getBranchList(ids);
        return success(BranchConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门店分页")
    @PreAuthorize("@ss.hasPermission('shop:branch:query')")
    public CommonResult<PageResult<BranchRespVO>> getBranchPage(@Valid BranchPageReqVO pageVO) {
        PageResult<BranchDO> pageResult = branchService.getBranchPage(pageVO);
        return success(BranchConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出门店 Excel")
    @PreAuthorize("@ss.hasPermission('shop:branch:export')")
    @OperateLog(type = EXPORT)
    public void exportBranchExcel(@Valid BranchExportReqVO exportReqVO,
                                  HttpServletResponse response) throws IOException {
        List<BranchDO> list = branchService.getBranchList(exportReqVO);
        // 导出 Excel
        List<BranchExcelVO> datas = BranchConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "门店.xls", "数据", BranchExcelVO.class, datas);
    }

    @GetMapping("/list-all-simple")
    @Operation(summary = "获取门店精简信息列表", description = "只包含被开启的门店，主要用于前端的下拉选项")
    public CommonResult<List<BranchSimpleRespVO>> getSimpleBranches() {
        List<BranchDO> list = branchService.getBranchesByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(BranchConvert.INSTANCE.convertList03(list));
    }

}
