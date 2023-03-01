package cn.iocoder.yudao.module.shop.controller.admin.branch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 门店商品 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class BranchGoodsBaseVO {

    @Schema(description = "商品名称", required = true)
    @NotNull(message = "商品名称不能为空")
    private String name;

    @Schema(description = "售价", required = true)
    @NotNull(message = "售价不能为空")
    private BigDecimal price;

    @Schema(description = "产品编号", required = true)
    @NotNull(message = "产品编号不能为空")
    private Long productId;

    @Schema(description = "店铺编号", required = true)
    @NotNull(message = "店铺编号不能为空")
    private Long branchId;

    @Schema(description = "库存", required = true)
    @NotNull(message = "库存不能为空")
    private Integer stock;

}
