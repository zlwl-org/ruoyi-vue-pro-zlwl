package cn.iocoder.yudao.module.shop.controller.admin.branch;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.framework.operatelog.core.annotations.OperateLog;
import cn.iocoder.yudao.module.shop.controller.admin.branch.vo.*;
import cn.iocoder.yudao.module.shop.convert.branch.BranchGoodsConvert;
import cn.iocoder.yudao.module.shop.convert.promotion.PromotionConvert;
import cn.iocoder.yudao.module.shop.dal.dataobject.branch.BranchGoodsDO;
import cn.iocoder.yudao.module.shop.dal.dataobject.promotion.PromotionDO;
import cn.iocoder.yudao.module.shop.service.branch.BranchGoodsService;
import cn.iocoder.yudao.module.shop.service.promotion.PromotionService;
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

@Tag(name = "管理后台 - 门店商品")
@RestController
@RequestMapping("/shop/branch-goods")
@Validated
public class BranchGoodsController {

    @Resource
    private BranchGoodsService branchGoodsService;

    @Resource
    private PromotionService promotionService;

    @PostMapping("/create")
    @Operation(summary = "创建门店商品")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:create')")
    public CommonResult<Long> createBranchGoods(@Valid @RequestBody BranchGoodsCreateReqVO createReqVO) {
        return success(branchGoodsService.createBranchGoods(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新门店商品")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:update')")
    public CommonResult<Boolean> updateBranchGoods(@Valid @RequestBody BranchGoodsUpdateReqVO updateReqVO) {
        branchGoodsService.updateBranchGoods(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除门店商品")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:delete')")
    public CommonResult<Boolean> deleteBranchGoods(@RequestParam("id") Long id) {
        branchGoodsService.deleteBranchGoods(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得门店商品")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:query')")
    public CommonResult<BranchGoodsRespVO> getBranchGoods(@RequestParam("id") Long id) {
        BranchGoodsDO branchGoods = branchGoodsService.getBranchGoods(id);
        return success(BranchGoodsConvert.INSTANCE.convert(branchGoods));
    }

    @GetMapping("/list")
    @Operation(summary = "获得门店商品列表")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:query')")
    public CommonResult<List<BranchGoodsRespVO>> getBranchGoodsList(@RequestParam("ids") Collection<Long> ids) {
        List<BranchGoodsDO> list = branchGoodsService.getBranchGoodsList(ids);
        return success(BranchGoodsConvert.INSTANCE.convertList(list));
    }

    @GetMapping("/page")
    @Operation(summary = "获得门店商品分页")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:query')")
    public CommonResult<PageResult<BranchGoodsRespVO>> getBranchGoodsPage(@Valid BranchGoodsPageReqVO pageVO) {
        PageResult<BranchGoodsDO> pageResult = branchGoodsService.getBranchGoodsPage(pageVO);
        PageResult<BranchGoodsRespVO> result = BranchGoodsConvert.INSTANCE.convertPage(pageResult);
        List<Long> ids = pageResult.getList().stream().map(BranchGoodsDO::getProductId).toList();
        if (ids.size() != 0) {
            List<PromotionDO> promotionList = promotionService.getPromotionListByProductIds(ids);
            if (promotionList != null) {
                for (PromotionDO promotionDO : promotionList) {
                    for (int i = 0; i < result.getList().size(); i++) {
                        BranchGoodsRespVO good = result.getList().get(i);
                        if (good.getProductId() == null) {
                            break;
                        }
                        if (good.getProductId().equals(promotionDO.getProductId())) {
                            result.getList().get(i).setPromotion(PromotionConvert.INSTANCE.convert(promotionDO));
                            break;
                        }
                    }
                }
            }
        }

        return success(result);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出门店商品 Excel")
    @PreAuthorize("@ss.hasPermission('shop:branch-goods:export')")
    @OperateLog(type = EXPORT)
    public void exportBranchGoodsExcel(@Valid BranchGoodsExportReqVO exportReqVO,
                                       HttpServletResponse response) throws IOException {
        List<BranchGoodsDO> list = branchGoodsService.getBranchGoodsList(exportReqVO);
        // 导出 Excel
        List<BranchGoodsExcelVO> datas = BranchGoodsConvert.INSTANCE.convertList02(list);
        ExcelUtils.write(response, "门店商品.xls", "数据", BranchGoodsExcelVO.class, datas);
    }

}
