package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
* 会员充值套餐 Base VO，提供给添加、修改、详细的子 VO 使用
* 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
*/
@Data
public class RechargeBaseVO {

    @ApiModelProperty(value = "套餐名称", required = true)
    @NotNull(message = "套餐名称不能为空")
    private String name;

    @ApiModelProperty(value = "面值", required = true)
    @NotNull(message = "面值不能为空")
    private BigDecimal faceValue;

    @ApiModelProperty(value = "售价", required = true)
    @NotNull(message = "售价不能为空")
    private BigDecimal price;

    @ApiModelProperty(value = "积分", required = true)
    @NotNull(message = "积分不能为空")
    private BigDecimal point;

    @ApiModelProperty(value = "成长值", required = true)
    @NotNull(message = "成长值不能为空")
    private BigDecimal growth;

    @ApiModelProperty(value = "优惠券")
    private String couponId;

    @ApiModelProperty(value = "不限数量", required = true)
    @NotNull(message = "不限数量不能为空")
    private Boolean unlimited;

    @ApiModelProperty(value = "发放数量")
    private Integer saleNum;

    @ApiModelProperty(value = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

}
