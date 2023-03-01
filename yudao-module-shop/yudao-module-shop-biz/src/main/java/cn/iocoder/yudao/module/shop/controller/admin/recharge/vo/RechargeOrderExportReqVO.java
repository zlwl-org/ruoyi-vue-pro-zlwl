package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 充值订单 Excel 导出 Request VO", description = "参数和 RechargeOrderPageReqVO 是一致的")
@Data
public class RechargeOrderExportReqVO {

    @Schema(description = "会员编号")
    private Long memberId;

    @Schema(description = "充值金额")
    private BigDecimal amount;

    @Schema(description = "充值活动编号")
    private Long rechargeId;

    @Schema(description = "充值活动名称")
    private String rechargeName;

    @Schema(description = "支付方式")
    private String payType;

    @Schema(description = "支付状态")
    private Integer payStatus;

    @Schema(description = "支付时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] payTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
