package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@ApiModel("门店会员精简信息 Response VO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShopMemberSimpleRespVO{

    @ApiModelProperty(value = "会员编号", required = true)
    private Long id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

}
