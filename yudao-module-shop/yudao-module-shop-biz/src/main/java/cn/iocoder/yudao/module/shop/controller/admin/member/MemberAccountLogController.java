package cn.iocoder.yudao.module.shop.controller.admin.member;

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

import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.MemberAccountLogDO;
import cn.iocoder.yudao.module.shop.convert.member.MemberAccountLogConvert;
import cn.iocoder.yudao.module.shop.service.member.MemberAccountLogService;

@Api(tags = "管理后台 - 会员账户流水")
@RestController
@RequestMapping("/shop/member-account-log")
@Validated
public class MemberAccountLogController {

    @Resource
    private MemberAccountLogService memberAccountLogService;

    @PostMapping("/create")
    @ApiOperation("创建会员账户流水")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:create')")
    public CommonResult<Long> createMemberAccountLog(@Valid @RequestBody MemberAccountLogCreateReqVO createReqVO) {
        return success(memberAccountLogService.createMemberAccountLog(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新会员账户流水")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:update')")
    public CommonResult<Boolean> updateMemberAccountLog(@Valid @RequestBody MemberAccountLogUpdateReqVO updateReqVO) {
        memberAccountLogService.updateMemberAccountLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除会员账户流水")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:delete')")
    public CommonResult<Boolean> deleteMemberAccountLog(@RequestParam("id") Long id) {
        memberAccountLogService.deleteMemberAccountLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得会员账户流水")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:query')")
    public CommonResult<MemberAccountLogRespVO> getMemberAccountLog(@RequestParam("id") Long id) {
        MemberAccountLogDO memberAccountLog = memberAccountLogService.getMemberAccountLog(id);
        return success(MemberAccountLogConvert.INSTANCE.convert(memberAccountLog));
    }

    @GetMapping("/list")
    @ApiOperation("获得会员账户流水列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:query')")
    public CommonResult<List<MemberAccountLogRespVO>> getMemberAccountLogList(@RequestParam("ids") Collection<Long> ids) {
        List<MemberAccountLogDO> list = memberAccountLogService.getMemberAccountLogList(ids);
        return success(MemberAccountLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得会员账户流水分页")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:query')")
    public CommonResult<PageResult<MemberAccountLogRespVO>> getMemberAccountLogPage(@Valid MemberAccountLogPageReqVO pageVO) {
        PageResult<MemberAccountLogDO> pageResult = memberAccountLogService.getMemberAccountLogPage(pageVO);
        return success(MemberAccountLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出会员账户流水 Excel")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:export')")
    @OperateLog(type = EXPORT)
    public void exportMemberAccountLogExcel(@Valid MemberAccountLogExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<MemberAccountLogDO> list = memberAccountLogService.getMemberAccountLogList(exportReqVO);
        // 导出 Excel
        List<MemberAccountLogExcelVO> datas = MemberAccountLogConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "会员账户流水.xls", "数据", MemberAccountLogExcelVO.class, datas);
    }

}
