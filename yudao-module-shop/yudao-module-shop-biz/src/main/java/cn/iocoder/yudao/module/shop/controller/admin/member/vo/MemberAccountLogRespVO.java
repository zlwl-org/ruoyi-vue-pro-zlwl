package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 会员账户流水 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberAccountLogRespVO extends MemberAccountLogBaseVO {

    @ApiModelProperty(value = "流水编号", required = true)
    private Long id;

    @ApiModelProperty(value = "会员编号", required = true)
    private Long memberId;

    @ApiModelProperty(value = "关联表编号")
    private Long relatedId;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
