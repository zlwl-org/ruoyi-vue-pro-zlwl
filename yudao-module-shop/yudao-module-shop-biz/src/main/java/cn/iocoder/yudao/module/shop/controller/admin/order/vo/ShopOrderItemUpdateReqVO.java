package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 门店订单明细更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderItemUpdateReqVO extends ShopOrderItemBaseVO {

    @ApiModelProperty(value = "明细编号", required = true)
    @NotNull(message = "明细编号不能为空")
    private Long id;

}
