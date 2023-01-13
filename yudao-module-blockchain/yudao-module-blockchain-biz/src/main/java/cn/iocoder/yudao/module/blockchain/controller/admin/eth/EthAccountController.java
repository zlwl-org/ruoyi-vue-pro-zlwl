package cn.iocoder.yudao.module.blockchain.controller.admin.eth;

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

import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthAccountDO;
import cn.iocoder.yudao.module.blockchain.convert.eth.EthAccountConvert;
import cn.iocoder.yudao.module.blockchain.service.eth.EthAccountService;

@Api(tags = "管理后台 - 以太坊账户")
@RestController
@RequestMapping("/blockchain/eth-account")
@Validated
public class EthAccountController {

    @Resource
    private EthAccountService ethAccountService;

    @PostMapping("/create")
    @ApiOperation("创建以太坊账户")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:create')")
    public CommonResult<Long> createEthAccount(@Valid @RequestBody EthAccountCreateReqVO createReqVO) {
        return success(ethAccountService.createEthAccount(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新以太坊账户")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:update')")
    public CommonResult<Boolean> updateEthAccount(@Valid @RequestBody EthAccountUpdateReqVO updateReqVO) {
        ethAccountService.updateEthAccount(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除以太坊账户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:delete')")
    public CommonResult<Boolean> deleteEthAccount(@RequestParam("id") Long id) {
        ethAccountService.deleteEthAccount(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得以太坊账户")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:query')")
    public CommonResult<EthAccountRespVO> getEthAccount(@RequestParam("id") Long id) {
        EthAccountDO ethAccount = ethAccountService.getEthAccount(id);
        return success(EthAccountConvert.INSTANCE.convert(ethAccount));
    }

    @GetMapping("/list")
    @ApiOperation("获得以太坊账户列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:query')")
    public CommonResult<List<EthAccountRespVO>> getEthAccountList(@RequestParam("ids") Collection<Long> ids) {
        List<EthAccountDO> list = ethAccountService.getEthAccountList(ids);
        return success(EthAccountConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得以太坊账户分页")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-account:query')")
    public CommonResult<PageResult<EthAccountRespVO>> getEthAccountPage(@Valid EthAccountPageReqVO pageVO) {
        PageResult<EthAccountDO> pageResult = ethAccountService.getEthAccountPage(pageVO);
        return success(EthAccountConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出以太坊账户 Excel")
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
