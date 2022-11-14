package cn.iocoder.yudao.module.shop.controller.admin.member.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 会员账户流水更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MemberAccountLogUpdateReqVO extends MemberAccountLogBaseVO {

    @ApiModelProperty(value = "流水编号", required = true)
    @NotNull(message = "流水编号不能为空")
    private Long id;

}
