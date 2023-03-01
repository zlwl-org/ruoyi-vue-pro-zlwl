package cn.iocoder.yudao.module.shop.controller.admin.brand;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.brand.vo.*;
import cn.iocoder.yudao.module.shop.convert.brand.BrandConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.brand.BrandDO;
import cn.iocoder.yudao.module.shop.service.brand.BrandService;
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

@Tag(name = "管理后台 - 商品品牌")
@RestController
@RequestMapping("/shop/brand")
@Validated
public class BrandController {

    @Resource
    private BrandService brandService;

    @PostMapping("/create")
    @Operation(summary = "创建商品品牌")
    @PreAuthorize("@ss.hasPermission('shop:brand:create')")
    public CommonResult<Long> createBrand(@Valid @RequestBody BrandCreateReqVO createReqVO) {
        return success(brandService.createBrand(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新商品品牌")
    @PreAuthorize("@ss.hasPermission('shop:brand:update')")
    public CommonResult<Boolean> updateBrand(@Valid @RequestBody BrandUpdateReqVO updateReqVO) {
        brandService.updateBrand(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除商品品牌")
    @PreAuthorize("@ss.hasPermission('shop:brand:delete')")
    public CommonResult<Boolean> deleteBrand(@RequestParam("id") Long id) {
        brandService.deleteBrand(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得商品品牌")
    @PreAuthorize("@ss.hasPermission('shop:brand:query')")
    public CommonResult<BrandRespVO> getBrand(@RequestParam("id") Long id) {
        BrandDO brand = brandService.getBrand(id);
        return success(BrandConvert.INSTANCE.convert(brand));
    }

    @GetMapping("/list")
    @Operation(summary = "获得商品品牌列表")
    @PreAuthorize("@ss.hasPermission('shop:brand:query')")
    public CommonResult<List<BrandRespVO>> getBrandList(@RequestParam("ids") Collection<Long> ids) {
        List<BrandDO> list = brandService.getBrandList(ids);
        return success(BrandConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得商品品牌分页")
    @PreAuthorize("@ss.hasPermission('shop:brand:query')")
    public CommonResult<PageResult<BrandRespVO>> getBrandPage(@Valid BrandPageReqVO pageVO) {
        PageResult<BrandDO> pageResult = brandService.getBrandPage(pageVO);
        return success(BrandConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出商品品牌 Excel")
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
