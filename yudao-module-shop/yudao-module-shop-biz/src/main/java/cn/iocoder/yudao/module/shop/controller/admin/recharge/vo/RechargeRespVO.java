package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@Schema(description = "管理后台 - 充值活动 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeRespVO extends RechargeBaseVO {

    @Schema(description = "活动编号", required = true)
    private Long id;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
