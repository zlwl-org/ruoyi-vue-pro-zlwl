package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


@Schema(description = "管理后台 - 充值订单 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeOrderRespVO extends RechargeOrderBaseVO {

    @Schema(description = "订单编号", required = true)
    private Long id;

    @Schema(description = "会员编号", required = true)
    private Long memberId;

    @Schema(description = "充值金额", required = true)
    private BigDecimal amount;

    @Schema(description = "充值活动编号")
    private Long rechargeId;

    @Schema(description = "充值活动名称")
    private String rechargeName;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
