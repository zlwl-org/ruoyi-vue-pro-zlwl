package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 充值活动 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class RechargeBaseVO {

    @Schema(description = "名称", required = true)
    @NotNull(message = "名称不能为空")
    private String name;

    @Schema(description = "充值金额", required = true)
    @NotNull(message = "充值金额不能为空")
    private BigDecimal price;

    @Schema(description = "赠送金额", required = true)
    @NotNull(message = "赠送金额不能为空")
    private BigDecimal gift;

    @Schema(description = "赠送积分", required = true)
    @NotNull(message = "赠送积分不能为空")
    private BigDecimal point;

    @Schema(description = "赠送成长值", required = true)
    @NotNull(message = "赠送成长值不能为空")
    private BigDecimal growth;

    @Schema(description = "赠送优惠券")
    private String coupon;

    @Schema(description = "状态", required = true)
    @NotNull(message = "状态不能为空")
    private Integer status;

}
