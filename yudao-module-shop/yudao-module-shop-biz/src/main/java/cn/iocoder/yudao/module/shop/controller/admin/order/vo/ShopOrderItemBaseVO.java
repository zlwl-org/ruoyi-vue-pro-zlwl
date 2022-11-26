package cn.iocoder.yudao.module.shop.controller.admin.order.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 门店订单明细 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class ShopOrderItemBaseVO {

    @ApiModelProperty(value = "会员编号")
    private Long memberId;

    @ApiModelProperty(value = "订单编号", required = true)
    @NotNull(message = "订单编号不能为空")
    private Long orderId;

    @ApiModelProperty(value = "商品编号", required = true)
    @NotNull(message = "商品编号不能为空")
    private Long goodId;

    @ApiModelProperty(value = "商品名称")
    private String goodName;

    @ApiModelProperty(value = "商品售价", required = true)
    @NotNull(message = "商品售价不能为空")
    private BigDecimal goodPrice;

    @ApiModelProperty(value = "数量", required = true)
    @NotNull(message = "数量不能为空")
    private Integer amount;

}
