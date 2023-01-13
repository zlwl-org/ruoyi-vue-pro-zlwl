package cn.iocoder.yudao.module.shop.controller.admin.brand;

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

import cn.iocoder.yudao.module.shop.controller.admin.brand.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.brand.BrandDO;
import cn.iocoder.yudao.module.shop.convert.brand.BrandConvert;
import cn.iocoder.yudao.module.shop.service.brand.BrandService;

@Api(tags = "管理后台 - 商品品牌")
@RestController
@RequestMapping("/shop/brand")
@Validated
public class BrandController {

    @Resource
    private BrandService brandService;

    @PostMapping("/create")
    @ApiOperation("创建商品品牌")
    @PreAuthorize("@ss.hasPermission('shop:brand:create')")
    public CommonResult<Long> createBrand(@Valid @RequestBody BrandCreateReqVO createReqVO) {
        return success(brandService.createBrand(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新商品品牌")
    @PreAuthorize("@ss.hasPermission('shop:brand:update')")
    public CommonResult<Boolean> updateBrand(@Valid @RequestBody BrandUpdateReqVO updateReqVO) {
        brandService.updateBrand(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除商品品牌")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:brand:delete')")
    public CommonResult<Boolean> deleteBrand(@RequestParam("id") Long id) {
        brandService.deleteBrand(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得商品品牌")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:brand:query')")
    public CommonResult<BrandRespVO> getBrand(@RequestParam("id") Long id) {
        BrandDO brand = brandService.getBrand(id);
        return success(BrandConvert.INSTANCE.convert(brand));
    }

    @GetMapping("/list")
    @ApiOperation("获得商品品牌列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:brand:query')")
    public CommonResult<List<BrandRespVO>> getBrandList(@RequestParam("ids") Collection<Long> ids) {
        List<BrandDO> list = brandService.getBrandList(ids);
        return success(BrandConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得商品品牌分页")
    @PreAuthorize("@ss.hasPermission('shop:brand:query')")
    public CommonResult<PageResult<BrandRespVO>> getBrandPage(@Valid BrandPageReqVO pageVO) {
        PageResult<BrandDO> pageResult = brandService.getBrandPage(pageVO);
        return success(BrandConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出商品品牌 Excel")
    @PreAuthorize("@ss.hasPermission('shop:brand:export')")
    @OperateLog(type = EXPORT)
    public void exportBrandExcel(@Valid BrandExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<BrandDO> list = brandService.getBrandList(exportReqVO);
        // 导出 Excel
        List<BrandExcelVO> datas = BrandConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "商品品牌.xls", "数据", BrandExcelVO.class, datas);
    }

}
