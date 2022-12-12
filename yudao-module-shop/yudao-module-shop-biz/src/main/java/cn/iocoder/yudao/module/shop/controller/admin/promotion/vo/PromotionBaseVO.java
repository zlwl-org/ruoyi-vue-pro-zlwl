package cn.iocoder.yudao.module.shop.controller.admin.promotion.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 促销活动 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class PromotionBaseVO {

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "促销类型")
    private String promotionType;

    @ApiModelProperty(value = "条件")
    private BigDecimal condition;

    @ApiModelProperty(value = "促销值")
    private BigDecimal target;

    @ApiModelProperty(value = "产品编号")
    private Long productId;

    @ApiModelProperty(value = "门店编号")
    private Long branchId;

    @ApiModelProperty(value = "商品编号")
    private Long goodId;

    @ApiModelProperty(value = "信息")
    private String info;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

}
