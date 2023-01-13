package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 门店订单明细 Excel 导出 Request VO", description = "参数和 ShopOrderItemPageReqVO 是一致的")
@Data
public class ShopOrderItemExportReqVO {

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

    @ApiModelProperty(value = "类型")
    private String type;

}
