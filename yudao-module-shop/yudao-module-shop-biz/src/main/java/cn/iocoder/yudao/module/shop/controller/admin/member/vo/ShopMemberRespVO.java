package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;


@Schema(description = "管理后台 - 会员 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopMemberRespVO extends ShopMemberBaseVO {

    @Schema(description = "会员编号", required = true)
    private Long id;

    @Schema(description = "积分", required = true)
    private BigDecimal point;

    @Schema(description = "余额", required = true)
    private BigDecimal balance;

    @Schema(description = "成长值", required = true)
    private BigDecimal growth;

    @Schema(description = "创建时间", required = true)
    private Date createTime;

    @Schema(description = "赠送余额", required = true)
    private BigDecimal gift;

}
