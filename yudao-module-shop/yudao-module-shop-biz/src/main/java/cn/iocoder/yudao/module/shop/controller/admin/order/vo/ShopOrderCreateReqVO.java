package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 门店订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderCreateReqVO extends ShopOrderBaseVO {

    @ApiModelProperty(value = "店铺优惠")
    private BigDecimal branchDiscount;

    @ApiModelProperty(value = "订单减免")
    private BigDecimal orderDiscount;

    @ApiModelProperty(value = "优惠券")
    private String coupon;

    @ApiModelProperty(value = "积分抵扣")
    private String point;

    @ApiModelProperty(value = "选购商品")
    private List<ShopOrderItemCreateReqVO> items;

}
