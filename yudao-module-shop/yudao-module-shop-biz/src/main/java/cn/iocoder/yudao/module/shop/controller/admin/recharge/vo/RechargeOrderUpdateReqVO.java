package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 充值订单更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeOrderUpdateReqVO extends RechargeOrderBaseVO {

    @ApiModelProperty(value = "充值订单编号", required = true)
    @NotNull(message = "充值订单编号不能为空")
    private Long id;

}
