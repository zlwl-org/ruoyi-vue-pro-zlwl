package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 充值订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeOrderCreateReqVO extends RechargeOrderBaseVO {

    @ApiModelProperty(value = "会员编号", required = true)
    @NotNull(message = "会员编号不能为空")
    private Long memberId;

    @ApiModelProperty(value = "充值金额", required = true)
    @NotNull(message = "充值金额不能为空")
    private BigDecimal amount;

    @ApiModelProperty(value = "充值活动编号")
    private Long rechargeId;

    @ApiModelProperty(value = "充值活动名称")
    private String rechargeName;

}
