package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
* 充值订单 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RechargeOrderBaseVO {

    @ApiModelProperty(value = "订单编号", required = true)
    @NotNull(message = "订单编号不能为空")
    private String orderNo;

    @ApiModelProperty(value = "订单流水号", required = true)
    @NotNull(message = "订单流水号不能为空")
    private String outTradeNo;

    @ApiModelProperty(value = "充值金额", required = true)
    @NotNull(message = "充值金额不能为空")
    private BigDecimal rechargeAmount;

    @ApiModelProperty(value = "套餐编号", required = true)
    @NotNull(message = "套餐编号不能为空")
    private Integer rechargeId;

    @ApiModelProperty(value = "支付方式", required = true)
    @NotNull(message = "支付方式不能为空")
    private String payType;

    @ApiModelProperty(value = "支付状态", required = true)
    @NotNull(message = "支付状态不能为空")
    private Integer status;

    @ApiModelProperty(value = "支付时间", required = true)
    @NotNull(message = "支付时间不能为空")
    private Integer payTime;

    @ApiModelProperty(value = "会员编号", required = true)
    @NotNull(message = "会员编号不能为空")
    private Integer memberId;

    @ApiModelProperty(value = "订单来源", required = true)
    @NotNull(message = "订单来源不能为空")
    private String orderFrom;

    @ApiModelProperty(value = "订单来源名称", required = true)
    @NotNull(message = "订单来源名称不能为空")
    private String orderFromName;

}
