package cn.iocoder.yudao.module.blockchain.controller.admin.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(name = "管理后台 - 用户钱包 Excel 导出 Request VO", description = "参数和 UserWalletPageReqVO 是一致的")
@Data
public class UserWalletExportReqVO {

    @Schema(description = "用户ID")
    private Long userId;

    @Schema(description = "名称")
    private String name;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "网络")
    private String net;

    @Schema(description = "代币")
    private String symbol;

    @Schema(description = "余额")
    private BigDecimal balance;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
