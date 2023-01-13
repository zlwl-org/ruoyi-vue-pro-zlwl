package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@ApiModel("管理后台 - 会员更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ShopMemberUpdateReqVO extends ShopMemberBaseVO {

    @ApiModelProperty(value = "会员编号", required = true)
    @NotNull(message = "会员编号不能为空")
    private Long id;

}
