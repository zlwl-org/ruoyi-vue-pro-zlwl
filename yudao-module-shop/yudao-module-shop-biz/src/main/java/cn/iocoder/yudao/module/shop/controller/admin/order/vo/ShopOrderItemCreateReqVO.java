package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 门店订单明细创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopOrderItemCreateReqVO extends ShopOrderItemBaseVO {

}
