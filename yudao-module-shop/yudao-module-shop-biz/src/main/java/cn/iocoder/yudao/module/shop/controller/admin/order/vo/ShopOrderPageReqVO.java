package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 门店订单分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderPageReqVO extends PageParam {

    @Schema(description = "会员编号")
    private Long memberId;

    @Schema(description = "订单类型")
    private String orderType;

    @Schema(description = "订单交易号")
    private String orderNo;

    @Schema(description = "订单状态")
    private String orderStatus;

    @Schema(description = "付款方式")
    private String payType;

    @Schema(description = "支付状态")
    private String payStatus;

    @Schema(description = "付款时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] payTime;

    @Schema(description = "收银员")
    private Long cashier;

    @Schema(description = "店铺编号")
    private Long branchId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
