package cn.iocoder.yudao.module.shop.controller.admin.product;

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

import cn.iocoder.yudao.module.shop.controller.admin.product.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.product.ProductDO;
import cn.iocoder.yudao.module.shop.convert.product.ProductConvert;
import cn.iocoder.yudao.module.shop.service.product.ProductService;

@Api(tags = "管理后台 - 产品")
@RestController
@RequestMapping("/shop/product")
@Validated
public class ProductController {

    @Resource
    private ProductService productService;

    @PostMapping("/create")
    @ApiOperation("创建产品")
    @PreAuthorize("@ss.hasPermission('shop:product:create')")
    public CommonResult<Long> createProduct(@Valid @RequestBody ProductCreateReqVO createReqVO) {
        return success(productService.createProduct(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新产品")
    @PreAuthorize("@ss.hasPermission('shop:product:update')")
    public CommonResult<Boolean> updateProduct(@Valid @RequestBody ProductUpdateReqVO updateReqVO) {
        productService.updateProduct(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除产品")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:product:delete')")
    public CommonResult<Boolean> deleteProduct(@RequestParam("id") Long id) {
        productService.deleteProduct(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得产品")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:product:query')")
    public CommonResult<ProductRespVO> getProduct(@RequestParam("id") Long id) {
        ProductDO product = productService.getProduct(id);
        return success(ProductConvert.INSTANCE.convert(product));
    }

    @GetMapping("/list")
    @ApiOperation("获得产品列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:product:query')")
    public CommonResult<List<ProductRespVO>> getProductList(@RequestParam("ids") Collection<Long> ids) {
        List<ProductDO> list = productService.getProductList(ids);
        return success(ProductConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得产品分页")
    @PreAuthorize("@ss.hasPermission('shop:product:query')")
    public CommonResult<PageResult<ProductRespVO>> getProductPage(@Valid ProductPageReqVO pageVO) {
        PageResult<ProductDO> pageResult = productService.getProductPage(pageVO);
        return success(ProductConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出产品 Excel")
    @PreAuthorize("@ss.hasPermission('shop:product:export')")
    @OperateLog(type = EXPORT)
    public void exportProductExcel(@Valid ProductExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<ProductDO> list = productService.getProductList(exportReqVO);
        // 导出 Excel
        List<ProductExcelVO> datas = ProductConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "产品.xls", "数据", ProductExcelVO.class, datas);
    }

    @GetMapping("/list-all")
    @ApiOperation("获得产品列表")
    @ApiImplicitParam(dataTypeClass = List.class)
//    @PreAuthorize("@ss.hasPermission('shop:product:query')")
    public CommonResult<List<ProductRespVO>> getProducts() {
        List<ProductDO> list = productService.getProducts();
        return success(ProductConvert.INSTANCE.convertList(list));
    }

}
