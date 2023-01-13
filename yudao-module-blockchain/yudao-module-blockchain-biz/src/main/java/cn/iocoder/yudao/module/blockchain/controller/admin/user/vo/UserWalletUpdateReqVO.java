package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 用户钱包更新 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserWalletUpdateReqVO extends UserWalletBaseVO {

    @ApiModelProperty(value = "钱包编号", required = true)
    @NotNull(message = "钱包编号不能为空")
    private Long id;

}
