package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

/**
* 充值活动 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RechargeBaseVO {

    @ApiModelProperty(value = "名称", required = true)
    @NotNull(message = "名称不能为空")
    private String name;

    @ApiModelProperty(value = "充值金额", required = true)
    @NotNull(message = "充值金额不能为空")
    private BigDecimal price;

    @ApiModelProperty(value = "赠送金额", required = true)
    @NotNull(message = "赠送金额不能为空")
    private BigDecimal gift;

    @ApiModelProperty(value = "赠送积分", required = true)
    @NotNull(message = "赠送积分不能为空")
    private BigDecimal point;

    @ApiModelProperty(value = "赠送成长值", required = true)
    @NotNull(message = "赠送成长值不能为空")
    private BigDecimal growth;

    @ApiModelProperty(value = "赠送优惠券")
    private String coupon;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

}
