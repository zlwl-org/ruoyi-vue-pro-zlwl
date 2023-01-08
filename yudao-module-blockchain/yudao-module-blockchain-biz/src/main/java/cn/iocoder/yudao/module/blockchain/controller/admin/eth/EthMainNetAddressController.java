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
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthMainNetAddressDO;
import cn.iocoder.yudao.module.blockchain.convert.eth.EthMainNetAddressConvert;
import cn.iocoder.yudao.module.blockchain.service.eth.EthMainNetAddressService;

@Api(tags = "管理后台 - 以太坊主网地址")
@RestController
@RequestMapping("/blockchain/eth-main-net-address")
@Validated
public class EthMainNetAddressController {

    @Resource
    private EthMainNetAddressService ethMainNetAddressService;

    @PostMapping("/create")
    @ApiOperation("创建以太坊主网地址")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-main-net-address:create')")
    public CommonResult<Long> createEthMainNetAddress(@Valid @RequestBody EthMainNetAddressCreateReqVO createReqVO) {
        return success(ethMainNetAddressService.createEthMainNetAddress(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新以太坊主网地址")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-main-net-address:update')")
    public CommonResult<Boolean> updateEthMainNetAddress(@Valid @RequestBody EthMainNetAddressUpdateReqVO updateReqVO) {
        ethMainNetAddressService.updateEthMainNetAddress(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除以太坊主网地址")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:eth-main-net-address:delete')")
    public CommonResult<Boolean> deleteEthMainNetAddress(@RequestParam("id") Long id) {
        ethMainNetAddressService.deleteEthMainNetAddress(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得以太坊主网地址")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:eth-main-net-address:query')")
    public CommonResult<EthMainNetAddressRespVO> getEthMainNetAddress(@RequestParam("id") Long id) {
        EthMainNetAddressDO ethMainNetAddress = ethMainNetAddressService.getEthMainNetAddress(id);
        return success(EthMainNetAddressConvert.INSTANCE.convert(ethMainNetAddress));
    }

    @GetMapping("/list")
    @ApiOperation("获得以太坊主网地址列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blockchain:eth-main-net-address:query')")
    public CommonResult<List<EthMainNetAddressRespVO>> getEthMainNetAddressList(@RequestParam("ids") Collection<Long> ids) {
        List<EthMainNetAddressDO> list = ethMainNetAddressService.getEthMainNetAddressList(ids);
        return success(EthMainNetAddressConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得以太坊主网地址分页")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-main-net-address:query')")
    public CommonResult<PageResult<EthMainNetAddressRespVO>> getEthMainNetAddressPage(@Valid EthMainNetAddressPageReqVO pageVO) {
        PageResult<EthMainNetAddressDO> pageResult = ethMainNetAddressService.getEthMainNetAddressPage(pageVO);
        return success(EthMainNetAddressConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出以太坊主网地址 Excel")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-main-net-address:export')")
    @OperateLog(type = EXPORT)
    public void exportEthMainNetAddressExcel(@Valid EthMainNetAddressExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<EthMainNetAddressDO> list = ethMainNetAddressService.getEthMainNetAddressList(exportReqVO);
        // 导出 Excel
        List<EthMainNetAddressExcelVO> datas = EthMainNetAddressConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "以太坊主网地址.xls", "数据", EthMainNetAddressExcelVO.class, datas);
    }

}
