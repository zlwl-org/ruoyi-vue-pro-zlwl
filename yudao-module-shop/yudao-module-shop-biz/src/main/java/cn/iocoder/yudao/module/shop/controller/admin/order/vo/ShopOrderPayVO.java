package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 订单支付VO
*/
@Data
public class ShopOrderPayVO {

    @ApiModelProperty(value = "订单编号")
    private Long id;

    @ApiModelProperty(value = "付款方式")
    private String payType;

    @ApiModelProperty(value = "支付金额")
    private BigDecimal amount;

}
