package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 会员充值套餐更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeUpdateReqVO extends RechargeBaseVO {

    @ApiModelProperty(value = "充值套餐编号", required = true)
    @NotNull(message = "充值套餐编号不能为空")
    private Long id;

}
