package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 充值订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeOrderRespVO extends RechargeOrderBaseVO {

    @ApiModelProperty(value = "订单编号", required = true)
    private Long id;

    @ApiModelProperty(value = "会员编号", required = true)
    private Long memberId;

    @ApiModelProperty(value = "充值金额", required = true)
    private BigDecimal amount;

    @ApiModelProperty(value = "充值活动编号")
    private Long rechargeId;

    @ApiModelProperty(value = "充值活动名称")
    private String rechargeName;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
