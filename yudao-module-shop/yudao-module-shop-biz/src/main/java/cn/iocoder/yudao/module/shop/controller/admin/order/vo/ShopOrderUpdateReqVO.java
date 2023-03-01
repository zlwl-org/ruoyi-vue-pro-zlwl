package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 门店订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderUpdateReqVO extends ShopOrderBaseVO {

    @Schema(description = "订单编号", required = true)
    @NotNull(message = "订单编号不能为空")
    private Long id;

    @Schema(description = "店铺优惠")
    private BigDecimal branchDiscount;

    @Schema(description = "订单减免")
    private BigDecimal orderDiscount;

    @Schema(description = "优惠券")
    private String coupon;

    @Schema(description = "积分抵扣")
    private String point;

}
