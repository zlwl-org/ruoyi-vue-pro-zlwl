package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - 门店订单明细分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderItemPageReqVO extends PageParam {

    @ApiModelProperty(value = "会员编号")
    private Long memberId;

    @ApiModelProperty(value = "订单编号")
    private Long orderId;

    @ApiModelProperty(value = "商品编号")
    private Long goodId;

    @ApiModelProperty(value = "商品名称")
    private String goodName;

    @ApiModelProperty(value = "商品售价")
    private BigDecimal goodPrice;

    @ApiModelProperty(value = "数量")
    private Integer amount;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
