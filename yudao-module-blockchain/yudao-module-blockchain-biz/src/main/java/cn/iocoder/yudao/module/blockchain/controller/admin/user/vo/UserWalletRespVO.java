package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel("管理后台 - 用户钱包 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserWalletRespVO extends UserWalletBaseVO {

    @ApiModelProperty(value = "钱包编号", required = true)
    private Long id;

    @ApiModelProperty(value = "用户ID", required = true)
    private Long userId;

    @ApiModelProperty(value = "网络")
    private String net;

    @ApiModelProperty(value = "代币")
    private String symbol;

    @ApiModelProperty(value = "余额")
    private BigDecimal balance;

    @ApiModelProperty(value = "创建时间", required = true)
    private LocalDateTime createTime;

}
