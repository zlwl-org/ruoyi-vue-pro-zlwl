package cn.iocoder.yudao.module.shop.controller.admin.recharge.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 充值活动更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RechargeUpdateReqVO extends RechargeBaseVO {

    @ApiModelProperty(value = "活动编号", required = true)
    @NotNull(message = "活动编号不能为空")
    private Long id;

}
