package cn.iocoder.yudao.module.blockchain.controller.admin.user;

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

import cn.iocoder.yudao.module.blockchain.controller.admin.user.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.user.UserWalletDO;
import cn.iocoder.yudao.module.blockchain.convert.user.UserWalletConvert;
import cn.iocoder.yudao.module.blockchain.service.user.UserWalletService;

@Api(tags = "管理后台 - 用户钱包")
@RestController
@RequestMapping("/blockchain/user-wallet")
@Validated
public class UserWalletController {

    @Resource
    private UserWalletService userWalletService;

    @PostMapping("/create")
    @ApiOperation("创建用户钱包")
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:create')")
    public CommonResult<Long> createUserWallet(@Valid @RequestBody UserWalletCreateReqVO createReqVO) {
        return success(userWalletService.createUserWallet(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新用户钱包")
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:update')")
    public CommonResult<Boolean> updateUserWallet(@Valid @RequestBody UserWalletUpdateReqVO updateReqVO) {
        userWalletService.updateUserWallet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除用户钱包")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:delete')")
    public CommonResult<Boolean> deleteUserWallet(@RequestParam("id") Long id) {
        userWalletService.deleteUserWallet(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得用户钱包")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:query')")
    public CommonResult<UserWalletRespVO> getUserWallet(@RequestParam("id") Long id) {
        UserWalletDO userWallet = userWalletService.getUserWallet(id);
        return success(UserWalletConvert.INSTANCE.convert(userWallet));
    }

    @GetMapping("/list")
    @ApiOperation("获得用户钱包列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:query')")
    public CommonResult<List<UserWalletRespVO>> getUserWalletList(@RequestParam("ids") Collection<Long> ids) {
        List<UserWalletDO> list = userWalletService.getUserWalletList(ids);
        return success(UserWalletConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得用户钱包分页")
    @PreAuthorize("@ss.hasPermission('blockchain:user-wallet:query')")
    public CommonResult<PageResult<UserWalletRespVO>> getUserWalletPage(@Valid UserWalletPageReqVO pageVO) {
        PageResult<UserWalletDO> pageResult = userWalletService.getUserWalletPage(pageVO);
        return success(UserWalletConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出用户钱包 Excel")
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
