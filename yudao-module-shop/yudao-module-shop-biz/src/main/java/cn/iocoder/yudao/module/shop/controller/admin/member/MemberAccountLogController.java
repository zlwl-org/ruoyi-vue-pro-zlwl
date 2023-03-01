package cn.iocoder.yudao.module.shop.controller.admin.member;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.convert.member.MemberAccountLogConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.MemberAccountLogDO;
import cn.iocoder.yudao.module.shop.service.member.MemberAccountLogService;
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

@Tag(name = "管理后台 - 会员账户流水")
@RestController
@RequestMapping("/shop/member-account-log")
@Validated
public class MemberAccountLogController {

    @Resource
    private MemberAccountLogService memberAccountLogService;

    @PostMapping("/create")
    @Operation(summary = "创建会员账户流水")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:create')")
    public CommonResult<Long> createMemberAccountLog(@Valid @RequestBody MemberAccountLogCreateReqVO createReqVO) {
        return success(memberAccountLogService.createMemberAccountLog(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新会员账户流水")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:update')")
    public CommonResult<Boolean> updateMemberAccountLog(@Valid @RequestBody MemberAccountLogUpdateReqVO updateReqVO) {
        memberAccountLogService.updateMemberAccountLog(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除会员账户流水")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:delete')")
    public CommonResult<Boolean> deleteMemberAccountLog(@RequestParam("id") Long id) {
        memberAccountLogService.deleteMemberAccountLog(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得会员账户流水")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:query')")
    public CommonResult<MemberAccountLogRespVO> getMemberAccountLog(@RequestParam("id") Long id) {
        MemberAccountLogDO memberAccountLog = memberAccountLogService.getMemberAccountLog(id);
        return success(MemberAccountLogConvert.INSTANCE.convert(memberAccountLog));
    }

    @GetMapping("/list")
    @Operation(summary = "获得会员账户流水列表")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:query')")
    public CommonResult<List<MemberAccountLogRespVO>> getMemberAccountLogList(@RequestParam("ids") Collection<Long> ids) {
        List<MemberAccountLogDO> list = memberAccountLogService.getMemberAccountLogList(ids);
        return success(MemberAccountLogConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得会员账户流水分页")
    @PreAuthorize("@ss.hasPermission('shop:member-account-log:query')")
    public CommonResult<PageResult<MemberAccountLogRespVO>> getMemberAccountLogPage(@Valid MemberAccountLogPageReqVO pageVO) {
        PageResult<MemberAccountLogDO> pageResult = memberAccountLogService.getMemberAccountLogPage(pageVO);
        return success(MemberAccountLogConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出会员账户流水 Excel")
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
