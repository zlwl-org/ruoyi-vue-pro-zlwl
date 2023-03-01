package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
 * 门店订单 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ShopOrderBaseVO {

    @Schema(description = "会员编号")
    private Long memberId;

    @Schema(description = "订单类型")
    private String orderType;

    @Schema(description = "订单交易号")
    private String orderNo;

    @Schema(description = "订单状态")
    private String orderStatus;

    @Schema(description = "付款方式")
    private String payType;

    @Schema(description = "支付状态")
    private String payStatus;

    @Schema(description = "付款时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date payTime;

    @Schema(description = "收银员")
    private Long cashier;

    @Schema(description = "订单金额")
    private BigDecimal orderPrice;

    @Schema(description = "实付金额")
    private BigDecimal price;

    @Schema(description = "订单优惠")
    private BigDecimal orderDiscount;

    @Schema(description = "减免金额")
    private BigDecimal branchDiscount;

    @Schema(description = "余额实付金额")
    private BigDecimal balancePay;

    @Schema(description = "现金实付金额")
    private BigDecimal cashPay;

    @Schema(description = "店铺编号", required = true)
    @NotNull(message = "店铺编号不能为空")
    private Long branchId;

}
