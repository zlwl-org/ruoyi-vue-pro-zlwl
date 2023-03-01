package cn.iocoder.yudao.module.demo.controller.admin.infra.net;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.demo.controller.admin.infra.net.vo.*;
import cn.iocoder.yudao.module.demo.convert.infra.net.NetConvert;
import cn.iocoder.yudao.module.demo.dal.dataobject.infra.net.NetDO;
import cn.iocoder.yudao.module.demo.service.infra.net.NetService;
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

@Tag(name = "管理后台 - 区块链网络")
@RestController
@RequestMapping("/demo/net")
@Validated
public class NetController {

    @Resource
    private NetService netService;

    @PostMapping("/create")
    @Operation(summary = "创建区块链网络")
    @PreAuthorize("@ss.hasPermission('demo:net:create')")
    public CommonResult<Long> createNet(@Valid @RequestBody NetCreateReqVO createReqVO) {
        return success(netService.createNet(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新区块链网络")
    @PreAuthorize("@ss.hasPermission('demo:net:update')")
    public CommonResult<Boolean> updateNet(@Valid @RequestBody NetUpdateReqVO updateReqVO) {
        netService.updateNet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除区块链网络")
//    //@ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('demo:net:delete')")
    public CommonResult<Boolean> deleteNet(@RequestParam("id") Long id) {
        netService.deleteNet(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得区块链网络")
    //@ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('demo:net:query')")
    public CommonResult<NetRespVO> getNet(@RequestParam("id") Long id) {
        NetDO net = netService.getNet(id);
        return success(NetConvert.INSTANCE.convert(net));
    }

    @GetMapping("/list")
    @Operation(summary = "获得区块链网络列表")
    //@ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('demo:net:query')")
    public CommonResult<List<NetRespVO>> getNetList(@RequestParam("ids") Collection<Long> ids) {
        List<NetDO> list = netService.getNetList(ids);
        return success(NetConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得区块链网络分页")
    @PreAuthorize("@ss.hasPermission('demo:net:query')")
    public CommonResult<PageResult<NetRespVO>> getNetPage(@Valid NetPageReqVO pageVO) {
        PageResult<NetDO> pageResult = netService.getNetPage(pageVO);
        return success(NetConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出区块链网络 Excel")
    @PreAuthorize("@ss.hasPermission('demo:net:export')")
    @OperateLog(type = EXPORT)
    public void exportNetExcel(@Valid NetExportReqVO exportReqVO,
                               HttpServletResponse response) throws IOException {
        List<NetDO> list = netService.getNetList(exportReqVO);
        // 导出 Excel
        List<NetExcelVO> datas = NetConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "区块链网络.xls", "数据", NetExcelVO.class, datas);
    }

}
