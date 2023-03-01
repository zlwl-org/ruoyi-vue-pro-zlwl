package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 门店出入库明细 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BranchStockItemBaseVO {

    @Schema(description = "库存编号", required = true)
    @NotNull(message = "库存编号不能为空")
    private Long stockId;

    @Schema(description = "出入库类型", required = true)
    @NotNull(message = "出入库类型不能为空")
    private String type;

    @Schema(description = "店铺编号", required = true)
    @NotNull(message = "店铺编号不能为空")
    private Long branchId;

    @Schema(description = "商品编号", required = true)
    @NotNull(message = "商品编号不能为空")
    private Long productId;

    @Schema(description = "数量", required = true)
    @NotNull(message = "数量不能为空")
    private Integer amount;

    @Schema(description = "产品名称")
    private String productName;

}
