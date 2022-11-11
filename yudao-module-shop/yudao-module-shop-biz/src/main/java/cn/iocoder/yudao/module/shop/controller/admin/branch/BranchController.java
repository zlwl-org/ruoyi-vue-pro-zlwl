package cn.iocoder.yudao.module.shop.controller.admin.branch;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.enums.CommonStatusEnum;

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
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchDO;
import cn.iocoder.yudao.module.shop.convert.branch.BranchConvert;
import cn.iocoder.yudao.module.shop.service.branch.BranchService;

@Api(tags = "管理后台 - 门店")
@RestController
@RequestMapping("/shop/branch")
@Validated
public class BranchController {

    @Resource
    private BranchService branchService;

    @PostMapping("/create")
    @ApiOperation("创建门店")
    @PreAuthorize("@ss.hasPermission('shop:branch:create')")
    public CommonResult<Long> createBranch(@Valid @RequestBody BranchCreateReqVO createReqVO) {
        return success(branchService.createBranch(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新门店")
    @PreAuthorize("@ss.hasPermission('shop:branch:update')")
    public CommonResult<Boolean> updateBranch(@Valid @RequestBody BranchUpdateReqVO updateReqVO) {
        branchService.updateBranch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除门店")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:branch:delete')")
    public CommonResult<Boolean> deleteBranch(@RequestParam("id") Long id) {
        branchService.deleteBranch(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得门店")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:branch:query')")
    public CommonResult<BranchRespVO> getBranch(@RequestParam("id") Long id) {
        BranchDO branch = branchService.getBranch(id);
        return success(BranchConvert.INSTANCE.convert(branch));
    }

    @GetMapping("/list")
    @ApiOperation("获得门店列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:branch:query')")
    public CommonResult<List<BranchRespVO>> getBranchList(@RequestParam("ids") Collection<Long> ids) {
        List<BranchDO> list = branchService.getBranchList(ids);
        return success(BranchConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得门店分页")
    @PreAuthorize("@ss.hasPermission('shop:branch:query')")
    public CommonResult<PageResult<BranchRespVO>> getBranchPage(@Valid BranchPageReqVO pageVO) {
        PageResult<BranchDO> pageResult = branchService.getBranchPage(pageVO);
        return success(BranchConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出门店 Excel")
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
    @ApiOperation(value = "获取用户精简信息列表", notes = "只包含被开启的用户，主要用于前端的下拉选项")
    public CommonResult<List<BranchSimpleRespVO>>  getSimpleBranches() {
        List<BranchDO> list = branchService.getBranchesByStatus(CommonStatusEnum.ENABLE.getStatus());
        return success(BranchConvert.INSTANCE.convertList03(list));
    }

}
