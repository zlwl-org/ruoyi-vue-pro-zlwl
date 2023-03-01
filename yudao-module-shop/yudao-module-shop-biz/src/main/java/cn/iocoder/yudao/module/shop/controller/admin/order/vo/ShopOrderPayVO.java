package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 订单支付VO
 */
@Data
public class ShopOrderPayVO {

    @Schema(description = "订单编号")
    private Long id;

    @Schema(description = "付款方式")
    private String payType;

    @Schema(description = "支付金额")
    private BigDecimal amount;

}
