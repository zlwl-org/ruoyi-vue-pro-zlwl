package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - Eth区块数据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthBlockPageReqVO extends PageParam {

    @Schema(description = "区块号码")
    private String number;

    @Schema(description = "区块哈希")
    private String hash;

    @Schema(description = "父区块哈希")
    private String parentHash;

    @Schema(description = "nonce")
    private String nonce;

    @Schema(description = "sha3uncles")
    private String sha3uncles;

    @Schema(description = "logs_bloom")
    private String logsBloom;

    @Schema(description = "transactions_root")
    private String transactionsRoot;

    @Schema(description = "state_root")
    private String stateRoot;

    @Schema(description = "receipts_root")
    private String receiptsRoot;

    @Schema(description = "author")
    private String author;

    @Schema(description = "矿工")
    private String miner;

    @Schema(description = "mix_hash")
    private String mixHash;

    @Schema(description = "难度")
    private String difficulty;

    @Schema(description = "总难度")
    private String totalDifficulty;

    @Schema(description = "size")
    private String size;

    @Schema(description = "gas_limit")
    private String gasLimit;

    @Schema(description = "gas_used")
    private String gasUsed;

    @Schema(description = "时间")
    private String timestamp;

    @Schema(description = "base_fee_per_gas")
    private String baseFeePerGas;

    @Schema(description = "是否处理")
    private Boolean done;

    @Schema(description = "信息")
    private String info;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
