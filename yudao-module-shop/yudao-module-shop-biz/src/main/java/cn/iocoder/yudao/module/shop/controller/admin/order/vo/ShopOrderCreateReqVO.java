package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ApiModel("管理后台 - 门店订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderCreateReqVO extends ShopOrderBaseVO {

    @ApiModelProperty(value = "优惠券")
    private String coupon;

    @ApiModelProperty(value = "积分抵扣")
    private String point;

    @ApiModelProperty(value = "选购商品")
    private List<ShopOrderItemCreateReqVO> items;

}
