package cn.iocoder.yudao.module.shop.controller.admin.member;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.member.vo.*;
import cn.iocoder.yudao.module.shop.convert.member.ShopMemberConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.member.ShopMemberDO;
import cn.iocoder.yudao.module.shop.service.member.ShopMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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

@Api(tags = "管理后台 - 会员")
@RestController
@RequestMapping("/shop/member")
@Validated
public class ShopMemberController {

    @Resource
    private ShopMemberService memberService;

    @PostMapping("/create")
    @ApiOperation("创建会员")
    @PreAuthorize("@ss.hasPermission('shop:member:create')")
    public CommonResult<Long> createMember(@Valid @RequestBody ShopMemberCreateReqVO createReqVO) {
        return success(memberService.createMember(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新会员")
    @PreAuthorize("@ss.hasPermission('shop:member:update')")
    public CommonResult<Boolean> updateMember(@Valid @RequestBody ShopMemberUpdateReqVO updateReqVO) {
        memberService.updateMember(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除会员")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:member:delete')")
    public CommonResult<Boolean> deleteMember(@RequestParam("id") Long id) {
        memberService.deleteMember(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得会员")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:member:query')")
    public CommonResult<ShopMemberRespVO> getMember(@RequestParam("id") Long id) {
        ShopMemberDO member = memberService.getMember(id);
        return success(ShopMemberConvert.INSTANCE.convert(member));
    }

    @GetMapping("/list")
    @ApiOperation("获得会员列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:member:query')")
    public CommonResult<List<ShopMemberRespVO>> getMemberList(@RequestParam("ids") Collection<Long> ids) {
        List<ShopMemberDO> list = memberService.getMemberList(ids);
        return success(ShopMemberConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得会员分页")
    @PreAuthorize("@ss.hasPermission('shop:member:query')")
    public CommonResult<PageResult<ShopMemberRespVO>> getMemberPage(@Valid ShopMemberPageReqVO pageVO) {
        PageResult<ShopMemberDO> pageResult = memberService.getMemberPage(pageVO);
        return success(ShopMemberConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出会员 Excel")
    @PreAuthorize("@ss.hasPermission('shop:member:export')")
    @OperateLog(type = EXPORT)
    public void exportMemberExcel(@Valid ShopMemberExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ShopMemberDO> list = memberService.getMemberList(exportReqVO);
        // 导出 Excel
        List<ShopMemberExcelVO> datas = ShopMemberConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "会员.xls", "数据", ShopMemberExcelVO.class, datas);
    }

    @GetMapping("/list-by-user")
    @ApiOperation("获得会员列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:member:query')")
    public CommonResult<List<ShopMemberRespVO>> getMemberListByUser() {
        List<ShopMemberDO> list = memberService.getMemberListByUser();
        return success(ShopMemberConvert.INSTANCE.convertList(list));
    }
}
