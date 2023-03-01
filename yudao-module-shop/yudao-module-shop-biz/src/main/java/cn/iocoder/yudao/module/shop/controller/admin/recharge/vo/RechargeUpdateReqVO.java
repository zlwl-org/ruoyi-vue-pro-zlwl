package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 充值活动更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeUpdateReqVO extends RechargeBaseVO {

    @Schema(description = "活动编号", required = true)
    @NotNull(message = "活动编号不能为空")
    private Long id;

}
