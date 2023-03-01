package cn.iocoder.yudao.module.blockchain.controller.admin.eth;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo.*;
import cn.iocoder.yudao.module.blockchain.convert.eth.EthBlockConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.eth.EthBlockDO;
import cn.iocoder.yudao.module.blockchain.service.eth.EthBlockService;
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

@Tag(name = "管理后台 - Eth区块数据")
@RestController
@RequestMapping("/blockchain/eth-block")
@Validated
public class EthBlockController {

    @Resource
    private EthBlockService ethBlockService;

    @PostMapping("/create")
    @Operation(summary = "创建Eth区块数据")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-block:create')")
    public CommonResult<Long> createEthBlock(@Valid @RequestBody EthBlockCreateReqVO createReqVO) {
        return success(ethBlockService.createEthBlock(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新Eth区块数据")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-block:update')")
    public CommonResult<Boolean> updateEthBlock(@Valid @RequestBody EthBlockUpdateReqVO updateReqVO) {
        ethBlockService.updateEthBlock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除Eth区块数据")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-block:delete')")
    public CommonResult<Boolean> deleteEthBlock(@RequestParam("id") Long id) {
        ethBlockService.deleteEthBlock(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得Eth区块数据")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-block:query')")
    public CommonResult<EthBlockRespVO> getEthBlock(@RequestParam("id") Long id) {
        EthBlockDO ethBlock = ethBlockService.getEthBlock(id);
        return success(EthBlockConvert.INSTANCE.convert(ethBlock));
    }

    @GetMapping("/list")
    @Operation(summary = "获得Eth区块数据列表")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-block:query')")
    public CommonResult<List<EthBlockRespVO>> getEthBlockList(@RequestParam("ids") Collection<Long> ids) {
        List<EthBlockDO> list = ethBlockService.getEthBlockList(ids);
        return success(EthBlockConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得Eth区块数据分页")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-block:query')")
    public CommonResult<PageResult<EthBlockRespVO>> getEthBlockPage(@Valid EthBlockPageReqVO pageVO) {
        PageResult<EthBlockDO> pageResult = ethBlockService.getEthBlockPage(pageVO);
        return success(EthBlockConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出Eth区块数据 Excel")
    @PreAuthorize("@ss.hasPermission('blockchain:eth-block:export')")
    @OperateLog(type = EXPORT)
    public void exportEthBlockExcel(@Valid EthBlockExportReqVO exportReqVO,
                                    HttpServletResponse response) throws IOException {
        List<EthBlockDO> list = ethBlockService.getEthBlockList(exportReqVO);
        // 导出 Excel
        List<EthBlockExcelVO> datas = EthBlockConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "Eth区块数据.xls", "数据", EthBlockExcelVO.class, datas);
    }

}
