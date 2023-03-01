package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 门店订单明细分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderItemPageReqVO extends PageParam {

    @Schema(description = "会员编号")
    private Long memberId;

    @Schema(description = "订单编号")
    private Long orderId;

    @Schema(description = "商品编号")
    private Long goodId;

    @Schema(description = "商品名称")
    private String goodName;

    @Schema(description = "商品售价")
    private BigDecimal goodPrice;

    @Schema(description = "数量")
    private Integer amount;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

    @Schema(description = "类型")
    private String type;

}
