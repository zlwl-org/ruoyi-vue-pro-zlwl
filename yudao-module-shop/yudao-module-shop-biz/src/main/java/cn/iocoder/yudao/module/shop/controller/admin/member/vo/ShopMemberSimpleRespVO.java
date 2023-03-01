package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "门店会员精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopMemberSimpleRespVO {

    @Schema(description = "会员编号", required = true)
    private Long id;

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号", required = true)
    private String mobile;

}
