package cn.iocoder.yudao.module.shop.controller.admin.product.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 产品 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ProductBaseVO {

    @Schema(description = "名称", required = true)
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "售价", required = true)
    @NotNull(message = "售价不能为空")
    private BigDecimal price;

    @Schema(description = "市场价")
    private BigDecimal marketPrice;

    @Schema(description = "成本价")
    private BigDecimal costPrice;

    @Schema(description = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "门店编号")
    private Long branchId;

}
