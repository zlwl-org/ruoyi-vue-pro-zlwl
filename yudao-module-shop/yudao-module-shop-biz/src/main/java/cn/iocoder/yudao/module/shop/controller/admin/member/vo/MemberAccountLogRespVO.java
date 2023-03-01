package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Date;


@Schema(description = "管理后台 - 会员账户流水 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberAccountLogRespVO extends MemberAccountLogBaseVO {

    @Schema(description = "流水编号", required = true)
    private Long id;

    @Schema(description = "会员编号", required = true)
    private Long memberId;

    @Schema(description = "关联表编号")
    private Long relatedId;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

}
