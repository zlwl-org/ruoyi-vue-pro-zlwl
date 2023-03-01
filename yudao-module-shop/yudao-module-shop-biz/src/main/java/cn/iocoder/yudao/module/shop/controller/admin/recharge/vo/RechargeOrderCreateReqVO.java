package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 充值订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeOrderCreateReqVO extends RechargeOrderBaseVO {

    @Schema(description = "会员编号", required = true)
    @NotNull(message = "会员编号不能为空")
    private Long memberId;

    @Schema(description = "充值金额", required = true)
    @NotNull(message = "充值金额不能为空")
    private BigDecimal amount;

    @Schema(description = "充值活动编号")
    private Long rechargeId;

    @Schema(description = "充值活动名称")
    private String rechargeName;

}
