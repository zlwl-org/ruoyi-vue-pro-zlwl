package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 充值订单 Excel 导出 Request VO", description = "参数和 RechargeOrderPageReqVO 是一致的")
@Data
public class RechargeOrderExportReqVO {

    @ApiModelProperty(value = "订单编号")
    private String orderNo;

    @ApiModelProperty(value = "订单流水号")
    private String outTradeNo;

    @ApiModelProperty(value = "充值金额")
    private BigDecimal rechargeAmount;

    @ApiModelProperty(value = "支付方式")
    private String payType;

    @ApiModelProperty(value = "支付状态")
    private Integer status;

    @ApiModelProperty(value = "支付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] payTime;

    @ApiModelProperty(value = "会员编号")
    private Integer memberId;

    @ApiModelProperty(value = "订单来源")
    private String orderFrom;

    @ApiModelProperty(value = "订单来源名称")
    private String orderFromName;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}