package cn.iocoder.yudao.module.blockchain.controller.admin.eth;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;
import cn.iocoder.yudao.module.blockchain.convert.eth.EthAccountConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;
import cn.iocoder.yudao.module.blockchain.service.eth.EthAccountService;
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

@Tag(name = "管理后台 - 以太坊账户")
@RestController
@RequestMapping("/blockchain/eth-account")
@Validated
public class EthAccountController {

    @Resource
    private EthAccountService ethAccountService;

    @PostMapping("/create")
    @Operation(summary = "创建以太坊账户")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:create')")
    public CommonResult<Long> createEthAccount(@Valid @RequestBody EthAccountCreateReqVO createReqVO) {
        return success(ethAccountService.createEthAccount(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新以太坊账户")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:update')")
    public CommonResult<Boolean> updateEthAccount(@Valid @RequestBody EthAccountUpdateReqVO updateReqVO) {
        ethAccountService.updateEthAccount(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除以太坊账户")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:delete')")
    public CommonResult<Boolean> deleteEthAccount(@RequestParam("id") Long id) {
        ethAccountService.deleteEthAccount(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得以太坊账户")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:query')")
    public CommonResult<EthAccountRespVO> getEthAccount(@RequestParam("id") Long id) {
        EthAccountDO ethAccount = ethAccountService.getEthAccount(id);
        return success(EthAccountConvert.INSTANCE.convert(ethAccount));
    }

    @GetMapping("/list")
    @Operation(summary = "获得以太坊账户列表")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:query')")
    public CommonResult<List<EthAccountRespVO>> getEthAccountList(@RequestParam("ids") Collection<Long> ids) {
        List<EthAccountDO> list = ethAccountService.getEthAccountList(ids);
        return success(EthAccountConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得以太坊账户分页")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:query')")
    public CommonResult<PageResult<EthAccountRespVO>> getEthAccountPage(@Valid EthAccountPageReqVO pageVO) {
        PageResult<EthAccountDO> pageResult = ethAccountService.getEthAccountPage(pageVO);
        return success(EthAccountConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出以太坊账户 Excel")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:export')")
    @OperateLog(type = EXPORT)
    public void exportEthAccountExcel(@Valid EthAccountExportReqVO exportReqVO,
                                      HttpServletResponse response) throws IOException {
        List<EthAccountDO> list = ethAccountService.getEthAccountList(exportReqVO);
        // 导出 Excel
        List<EthAccountExcelVO> datas = EthAccountConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "以太坊账户.xls", "数据", EthAccountExcelVO.class, datas);
    }

}
