package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ApiModel("管理后台 - 门店订单明细创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderItemCreateReqVO extends ShopOrderItemBaseVO {

    @ApiModelProperty(value = "促销活动编号")
    private Long promotionId;

}
