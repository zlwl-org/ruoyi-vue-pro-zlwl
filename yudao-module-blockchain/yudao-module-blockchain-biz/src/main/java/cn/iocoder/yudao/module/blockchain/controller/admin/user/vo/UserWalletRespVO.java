package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户钱包 Response VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserWalletRespVO extends UserWalletBaseVO {

    @Schema(description = "钱包编号", required = true)
    private Long id;

    @Schema(description = "用户ID", required = true)
    private Long userId;

    @Schema(description = "网络")
    private String net;

    @Schema(description = "代币")
    private String symbol;

    @Schema(description = "余额")
    private BigDecimal balance;

    @Schema(description = "创建时间", required = true)
    private LocalDateTime createTime;

}
