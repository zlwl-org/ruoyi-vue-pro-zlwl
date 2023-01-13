package cn.iocoder.yudao.module.shop.controller.admin.promotion;

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

import cn.iocoder.yudao.module.shop.controller.admin.promotion.vo.*;
import cn.iocoder.yudao.module.shop.dal.dataobject.promotion.PromotionDO;
import cn.iocoder.yudao.module.shop.convert.promotion.PromotionConvert;
import cn.iocoder.yudao.module.shop.service.promotion.PromotionService;

@Api(tags = "管理后台 - 促销活动")
@RestController
@RequestMapping("/shop/promotion")
@Validated
public class PromotionController {

    @Resource
    private PromotionService promotionService;

    @PostMapping("/create")
    @ApiOperation("创建促销活动")
    @PreAuthorize("@ss.hasPermission('shop:promotion:create')")
    public CommonResult<Long> createPromotion(@Valid @RequestBody PromotionCreateReqVO createReqVO) {
        return success(promotionService.createPromotion(createReqVO));
    }

    @PutMapping("/update")
    @ApiOperation("更新促销活动")
    @PreAuthorize("@ss.hasPermission('shop:promotion:update')")
    public CommonResult<Boolean> updatePromotion(@Valid @RequestBody PromotionUpdateReqVO updateReqVO) {
        promotionService.updatePromotion(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除促销活动")
    @ApiImplicitParam(name = "id", value = "编号", required = true, dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:promotion:delete')")
    public CommonResult<Boolean> deletePromotion(@RequestParam("id") Long id) {
        promotionService.deletePromotion(id);
        return success(true);
    }

    @GetMapping("/get")
    @ApiOperation("获得促销活动")
    @ApiImplicitParam(name = "id", value = "编号", required = true, example = "1024", dataTypeClass = Long.class)
    @PreAuthorize("@ss.hasPermission('shop:promotion:query')")
    public CommonResult<PromotionRespVO> getPromotion(@RequestParam("id") Long id) {
        PromotionDO promotion = promotionService.getPromotion(id);
        return success(PromotionConvert.INSTANCE.convert(promotion));
    }

    @GetMapping("/list")
    @ApiOperation("获得促销活动列表")
    @ApiImplicitParam(name = "ids", value = "编号列表", required = true, example = "1024,2048", dataTypeClass = List.class)
    @PreAuthorize("@ss.hasPermission('shop:promotion:query')")
    public CommonResult<List<PromotionRespVO>> getPromotionList(@RequestParam("ids") Collection<Long> ids) {
        List<PromotionDO> list = promotionService.getPromotionList(ids);
        return success(PromotionConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @ApiOperation("获得促销活动分页")
    @PreAuthorize("@ss.hasPermission('shop:promotion:query')")
    public CommonResult<PageResult<PromotionRespVO>> getPromotionPage(@Valid PromotionPageReqVO pageVO) {
        PageResult<PromotionDO> pageResult = promotionService.getPromotionPage(pageVO);
        return success(PromotionConvert.INSTANCE.convertPage(pageResult));
    }

    @GetMapping("/export-excel")
    @ApiOperation("导出促销活动 Excel")
    @PreAuthorize("@ss.hasPermission('shop:promotion:export')")
    @OperateLog(type = EXPORT)
    public void exportPromotionExcel(@Valid PromotionExportReqVO exportReqVO,
              HttpServletResponse response) throws IOException {
        List<PromotionDO> list = promotionService.getPromotionList(exportReqVO);
        // 导出 Excel
        List<PromotionExcelVO> datas = PromotionConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "促销活动.xls", "数据", PromotionExcelVO.class, datas);
    }

}
