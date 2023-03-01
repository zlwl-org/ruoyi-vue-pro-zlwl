package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 门店订单明细 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ShopOrderItemBaseVO {

    @Schema(description = "会员编号")
    private Long memberId;

    @Schema(description = "订单编号", required = true)
    @NotNull(message = "订单编号不能为空")
    private Long orderId;

    @Schema(description = "商品编号", required = true)
    @NotNull(message = "商品编号不能为空")
    private Long goodId;

    @Schema(description = "商品名称")
    private String goodName;

    @Schema(description = "商品售价", required = true)
    @NotNull(message = "商品售价不能为空")
    private BigDecimal goodPrice;

    @Schema(description = "数量", required = true)
    @NotNull(message = "数量不能为空")
    private Integer amount;

    @Schema(description = "减免金额", required = true)
    @NotNull(message = "减免金额不能为空")
    private BigDecimal discount;

    @Schema(description = "实际金额", required = true)
    @NotNull(message = "实际金额不能为空")
    private BigDecimal realPrice;

    @Schema(description = "促销活动编号")
    private Long promotionId;

    @Schema(description = "促销活动名称")
    private String promotionName;

    @Schema(description = "类型")
    private String type;

}
