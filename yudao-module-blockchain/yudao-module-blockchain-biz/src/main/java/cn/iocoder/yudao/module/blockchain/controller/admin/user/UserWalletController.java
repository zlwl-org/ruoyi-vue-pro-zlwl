package cn.iocoder.yudao.module.blockchain.controller.admin.user;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.blockchain.controller.admin.user.vo.*;
import cn.iocoder.yudao.module.blockchain.convert.user.UserWalletConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.user.UserWalletDO;
import cn.iocoder.yudao.module.blockchain.service.user.UserWalletService;
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

@Tag(name = "管理后台 - 用户钱包")
@RestController
@RequestMapping("/blockchain/user-wallet")
@Validated
public class UserWalletController {

    @Resource
    private UserWalletService userWalletService;

    @PostMapping("/create")
    @Operation(summary = "创建用户钱包")
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:create')")
    public CommonResult<Long> createUserWallet(@Valid @RequestBody UserWalletCreateReqVO createReqVO) {
        return success(userWalletService.createUserWallet(createReqVO));
    }

    @PostMapping("/import")
    @Operation(summary = "导入用户钱包")
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:create')")
    public CommonResult<Long> importUserWallet(@Valid @RequestBody UserWalletImportReqVO createReqVO) {
        return success(userWalletService.importUserWallet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新用户钱包")
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:update')")
    public CommonResult<Boolean> updateUserWallet(@Valid @RequestBody UserWalletUpdateReqVO updateReqVO) {
        userWalletService.updateUserWallet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除用户钱包")
//    //@ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:delete')")
    public CommonResult<Boolean> deleteUserWallet(@RequestParam("id") Long id) {
        userWalletService.deleteUserWallet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得用户钱包")
//    //@ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:query')")
    public CommonResult<UserWalletRespVO> getUserWallet(@RequestParam("id") Long id) {
        UserWalletDO userWallet = userWalletService.getUserWallet(id);
        return success(UserWalletConvert.INSTANCE.convert(userWallet));
    }

    @GetMapping("/list")
    @Operation(summary = "获得用户钱包列表")
//    //@ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:query')")
    public CommonResult<List<UserWalletRespVO>> getUserWalletList(@RequestParam("ids") Collection<Long> ids) {
        List<UserWalletDO> list = userWalletService.getUserWalletList(ids);
        return success(UserWalletConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得用户钱包分页")
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:query')")
    public CommonResult<PageResult<UserWalletRespVO>> getUserWalletPage(@Valid UserWalletPageReqVO pageVO) {
        PageResult<UserWalletDO> pageResult = userWalletService.getUserWalletPage(pageVO);
        return success(UserWalletConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出用户钱包 Excel")
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:export')")
    @OperateLog(type = EXPORT)
    public void exportUserWalletExcel(@Valid UserWalletExportReqVO exportReqVO,
                                      HttpServletResponse response) throws IOException {
        List<UserWalletDO> list = userWalletService.getUserWalletList(exportReqVO);
        // 导出 Excel
        List<UserWalletExcelVO> datas = UserWalletConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "用户钱包.xls", "数据", UserWalletExcelVO.class, datas);
    }

}
