package cn.iocoder.yudao.module.infra.controller.admin.proxy;

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

import cn.iocoder.yudao.module.infra.controller.admin.proxy.vo.*;
import cn.iocoder.yudao.module.infra.dal.dataobject.proxy.ProxyDO;
import cn.iocoder.yudao.module.infra.convert.proxy.ProxyConvert;
import cn.iocoder.yudao.module.infra.service.proxy.ProxyService;

@Api(tags = "管理后台 - 网络代理")
@RestController
@RequestMapping("/infra/proxy")
@Validated
public class ProxyController {

    @Resource
    private ProxyService proxyService;

    @PostMapping("/create")
    @ApiOperation("创建网络代理")
    @PreAuthorize("@ss.hasPermission('infra:proxy:create')")
    public CommonResult<Long> createProxy(@Valid @RequestBody ProxyCreateReqVO createReqVO) {
        return success(proxyService.createProxy(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新网络代理")
    @PreAuthorize("@ss.hasPermission('infra:proxy:update')")
    public CommonResult<Boolean> updateProxy(@Valid @RequestBody ProxyUpdateReqVO updateReqVO) {
        proxyService.updateProxy(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除网络代理")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('infra:proxy:delete')")
    public CommonResult<Boolean> deleteProxy(@RequestParam("id") Long id) {
        proxyService.deleteProxy(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得网络代理")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('infra:proxy:query')")
    public CommonResult<ProxyRespVO> getProxy(@RequestParam("id") Long id) {
        ProxyDO proxy = proxyService.getProxy(id);
        return success(ProxyConvert.INSTANCE.convert(proxy));
    }

    @GetMapping("/list")
    @ApiOperation("获得网络代理列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('infra:proxy:query')")
    public CommonResult<List<ProxyRespVO>> getProxyList(@RequestParam("ids") Collection<Long> ids) {
        List<ProxyDO> list = proxyService.getProxyList(ids);
        return success(ProxyConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得网络代理分页")
    @PreAuthorize("@ss.hasPermission('infra:proxy:query')")
    public CommonResult<PageResult<ProxyRespVO>> getProxyPage(@Valid ProxyPageReqVO pageVO) {
        PageResult<ProxyDO> pageResult = proxyService.getProxyPage(pageVO);
        return success(ProxyConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出网络代理 Excel")
    @PreAuthorize("@ss.hasPermission('infra:proxy:export')")
    @OperateLog(type = EXPORT)
    public void exportProxyExcel(@Valid ProxyExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProxyDO> list = proxyService.getProxyList(exportReqVO);
        // 导出 Excel
        List<ProxyExcelVO> datas = ProxyConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "网络代理.xls", "数据", ProxyExcelVO.class, datas);
    }

}
