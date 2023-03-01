package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 会员 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class ShopMemberBaseVO {

    @Schema(description = "姓名")
    private String name;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "手机号", required = true)
    @NotNull(message = "手机号不能为空")
    private String mobile;

    @Schema(description = "销售员", required = true)
    @NotNull(message = "销售员不能为空")
    private Long salesman;

    @Schema(description = "客户类型")
    private Integer type;

    @Schema(description = "状态")
    private Integer status;

    @Schema(description = "店铺编号")
    private Long branchId;

}
