package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 会员账户流水更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberAccountLogUpdateReqVO extends MemberAccountLogBaseVO {

    @Schema(description = "流水编号", required = true)
    @NotNull(message = "流水编号不能为空")
    private Long id;

}
