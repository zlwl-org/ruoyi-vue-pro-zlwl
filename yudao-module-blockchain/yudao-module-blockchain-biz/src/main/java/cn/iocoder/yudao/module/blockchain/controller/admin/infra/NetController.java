package cn.iocoder.yudao.module.blockchain.controller.admin.infra;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.blockchain.controller.admin.infra.vo.*;
import cn.iocoder.yudao.module.blockchain.convert.infra.NetConvert;
import cn.iocoder.yudao.module.blockchain.dal.dataobject.infra.NetDO;
import cn.iocoder.yudao.module.blockchain.service.infra.NetService;
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

@Api(tags = "管理后台 - 网络")
@RestController
@RequestMapping("/blockchain/net")
@Validated
public class NetController {

    @Resource
    private NetService netService;

    @PostMapping("/create")
    @ApiOperation("创建网络")
    @PreAuthorize("@ss.hasPermission('blockchain:net:create')")
    public CommonResult<Long> createNet(@Valid @RequestBody NetCreateReqVO createReqVO) {
        return success(netService.createNet(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新网络")
    @PreAuthorize("@ss.hasPermission('blockchain:net:update')")
    public CommonResult<Boolean> updateNet(@Valid @RequestBody NetUpdateReqVO updateReqVO) {
        netService.updateNet(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除网络")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:net:delete')")
    public CommonResult<Boolean> deleteNet(@RequestParam("id") Long id) {
        netService.deleteNet(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得网络")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('blockchain:net:query')")
    public CommonResult<NetRespVO> getNet(@RequestParam("id") Long id) {
        NetDO net = netService.getNet(id);
        return success(NetConvert.INSTANCE.convert(net));
    }

    @GetMapping("/list")
    @ApiOperation("获得网络列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('blockchain:net:query')")
    public CommonResult<List<NetRespVO>> getNetList(@RequestParam("ids") Collection<Long> ids) {
        List<NetDO> list = netService.getNetList(ids);
        return success(NetConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得网络分页")
    @PreAuthorize("@ss.hasPermission('blockchain:net:query')")
    public CommonResult<PageResult<NetRespVO>> getNetPage(@Valid NetPageReqVO pageVO) {
        PageResult<NetDO> pageResult = netService.getNetPage(pageVO);
        return success(NetConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出网络 Excel")
    @PreAuthorize("@ss.hasPermission('blockchain:net:export')")
    @OperateLog(type = EXPORT)
    public void exportNetExcel(@Valid NetExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<NetDO> list = netService.getNetList(exportReqVO);
        // 导出 Excel
        List<NetExcelVO> datas = NetConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "网络.xls", "数据", NetExcelVO.class, datas);
    }

    @GetMapping("/list-simple")
    @ApiOperation("获得网络列表")
    @PreAuthorize("@ss.hasPermission('blockchain:net:query')")
    public CommonResult<List<NetRespSimpleVO>> getNetListSimple() {
        List<NetDO> list = netService.getAllNet();
        return success(NetConvert.INSTANCE.convertList03(list));
    }

}
