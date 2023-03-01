package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Schema(description = "管理后台 - 门店订单创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderCreateReqVO extends ShopOrderBaseVO {

    @Schema(description = "优惠券")
    private String coupon;

    @Schema(description = "积分抵扣")
    private String point;

    @Schema(description = "选购商品")
    private List<ShopOrderItemCreateReqVO> items;

}
