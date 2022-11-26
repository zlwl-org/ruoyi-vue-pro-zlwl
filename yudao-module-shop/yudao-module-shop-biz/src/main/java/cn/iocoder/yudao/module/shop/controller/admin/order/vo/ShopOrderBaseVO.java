package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 门店订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ShopOrderBaseVO {

    @ApiModelProperty(value = "会员编号")
    private Long memberId;

    @ApiModelProperty(value = "订单类型")
    private String orderType;

    @ApiModelProperty(value = "订单交易号")
    private String orderNo;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "付款方式")
    private String payType;

    @ApiModelProperty(value = "支付状态")
    private String payStatus;

    @ApiModelProperty(value = "付款时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date payTime;

    @ApiModelProperty(value = "收银员")
    private Long cashier;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal price;

    @ApiModelProperty(value = "余额实付金额")
    private BigDecimal balancePay;

    @ApiModelProperty(value = "现金实付金额")
    private BigDecimal cashPay;

    @ApiModelProperty(value = "店铺编号", required = true)
    @NotNull(message = "店铺编号不能为空")
    private Long branchId;

}
