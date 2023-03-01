package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 会员账户流水 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class MemberAccountLogBaseVO {

    @Schema(description = "发生方式", required = true)
    @NotNull(message = "发生方式不能为空")
    private String action;

    @Schema(description = "充值余额变动", required = true)
    @NotNull(message = "充值余额变动不能为空")
    private BigDecimal balance;

    @Schema(description = "赠送余额变动", required = true)
    @NotNull(message = "赠送余额变动不能为空")
    private BigDecimal gift;

    @Schema(description = "积分变动", required = true)
    @NotNull(message = "积分变动不能为空")
    private BigDecimal point;

    @Schema(description = "成长值变动", required = true)
    @NotNull(message = "成长值变动不能为空")
    private BigDecimal growth;

    @Schema(description = "信息")
    private String info;

}
