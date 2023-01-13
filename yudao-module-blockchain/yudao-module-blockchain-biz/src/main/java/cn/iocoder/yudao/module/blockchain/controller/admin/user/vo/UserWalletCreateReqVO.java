package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import io.swagger.annotations.*;
import javax.validation.constraints.*;

@ApiModel("管理后台 - 用户钱包创建 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserWalletCreateReqVO extends UserWalletBaseVO {

    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @ApiModelProperty(value = "网络")
    private String net;

    @ApiModelProperty(value = "代币")
    private String symbol;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

}
