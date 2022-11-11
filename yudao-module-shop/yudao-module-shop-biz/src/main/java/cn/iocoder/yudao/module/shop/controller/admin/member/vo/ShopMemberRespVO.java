package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;

@ApiModel("管理后台 - 会员 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopMemberRespVO extends ShopMemberBaseVO {

    @ApiModelProperty(value = "会员编号", required = true)
    private Long id;

    @ApiModelProperty(value = "积分", required = true)
    private BigDecimal point;

    @ApiModelProperty(value = "余额", required = true)
    private BigDecimal balance;

    @ApiModelProperty(value = "成长值", required = true)
    private BigDecimal growth;

    @ApiModelProperty(value = "创建时间", required = true)
    private Date createTime;

}
