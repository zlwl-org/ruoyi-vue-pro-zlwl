package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel(value = "管理后台 - 门店订单 Excel 导出 Request VO", description = "参数和 ShopOrderPageReqVO 是一致的")
@Data
public class ShopOrderExportReqVO {

    @ApiModelProperty(value = "会员编号")
    private Long memberId;

    @ApiModelProperty(value = "订单类型")
    private String orderType;

    @ApiModelProperty(value = "订单交易号")
    private String orderNo;

    @ApiModelProperty(value = "订单状态")
    private String orderStatus;

    @ApiModelProperty(value = "付款方式")
    private String payType;

    @ApiModelProperty(value = "支付状态")
    private String payStatus;

    @ApiModelProperty(value = "付款时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] payTime;

    @ApiModelProperty(value = "收银员")
    private Long cashier;

    @ApiModelProperty(value = "店铺编号")
    private Long branchId;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Date[] createTime;

}
