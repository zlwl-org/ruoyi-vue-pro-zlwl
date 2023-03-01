package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Schema(description = "管理后台 - 会员更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopMemberUpdateReqVO extends ShopMemberBaseVO {

    @Schema(description = "会员编号", required = true)
    @NotNull(message = "会员编号不能为空")
    private Long id;

}
