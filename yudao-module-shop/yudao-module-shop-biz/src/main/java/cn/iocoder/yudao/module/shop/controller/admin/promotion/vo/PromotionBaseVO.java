package cn.iocoder.yudao.module.shop.controller.admin.promotion.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 促销活动 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class PromotionBaseVO {

    @Schema(description = "名称")
    private String name;

    @Schema(description = "促销类型")
    private String promotionType;

    @Schema(description = "产品编号")
    private Long productId;

    @Schema(description = "门店编号")
    private Long branchId;

    @Schema(description = "商品编号")
    private Long goodId;

    @Schema(description = "信息")
    private String info;

    @Schema(description = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

    @Schema(description = "金额门槛")
    private BigDecimal priceCondition;

    @Schema(description = "金额促销")
    private BigDecimal priceTarget;

    @Schema(description = "数量门槛")
    private Integer amountCondition;

    @Schema(description = "数量促销")
    private Integer amountTarget;

}
