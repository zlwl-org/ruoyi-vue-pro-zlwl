package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

/**
* 充值订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RechargeOrderBaseVO {

    @ApiModelProperty(value = "支付方式", required = true)
    @NotNull(message = "支付方式不能为空")
    private String payType;

    @ApiModelProperty(value = "支付状态", required = true)
    @NotNull(message = "支付状态不能为空")
    private Integer payStatus;

    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date payTime;

}
