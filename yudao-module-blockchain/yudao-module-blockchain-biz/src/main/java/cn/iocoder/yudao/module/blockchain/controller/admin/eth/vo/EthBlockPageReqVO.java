package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import lombok.*;
import java.util.*;
import io.swagger.annotations.*;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@ApiModel("管理后台 - Eth区块数据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EthBlockPageReqVO extends PageParam {

    @ApiModelProperty(value = "区块号码")
    private String number;

    @ApiModelProperty(value = "区块哈希")
    private String hash;

    @ApiModelProperty(value = "父区块哈希")
    private String parentHash;

    @ApiModelProperty(value = "nonce")
    private String nonce;

    @ApiModelProperty(value = "sha3uncles")
    private String sha3uncles;

    @ApiModelProperty(value = "logs_bloom")
    private String logsBloom;

    @ApiModelProperty(value = "transactions_root")
    private String transactionsRoot;

    @ApiModelProperty(value = "state_root")
    private String stateRoot;

    @ApiModelProperty(value = "receipts_root")
    private String receiptsRoot;

    @ApiModelProperty(value = "author")
    private String author;

    @ApiModelProperty(value = "矿工")
    private String miner;

    @ApiModelProperty(value = "mix_hash")
    private String mixHash;

    @ApiModelProperty(value = "难度")
    private String difficulty;

    @ApiModelProperty(value = "总难度")
    private String totalDifficulty;

    @ApiModelProperty(value = "size")
    private String size;

    @ApiModelProperty(value = "gas_limit")
    private String gasLimit;

    @ApiModelProperty(value = "gas_used")
    private String gasUsed;

    @ApiModelProperty(value = "时间")
    private String timestamp;

    @ApiModelProperty(value = "base_fee_per_gas")
    private String baseFeePerGas;

    @ApiModelProperty(value = "是否处理")
    private Boolean done;

    @ApiModelProperty(value = "信息")
    private String info;

    @ApiModelProperty(value = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
