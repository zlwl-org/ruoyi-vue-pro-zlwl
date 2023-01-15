package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Eth区块数据 Base VO，提供给添加、修改、详细的子 VO 使用
 * 如果子 VO 存在差异的字段，请不要添加到这里，影响 Swagger 文档生成
 */
@Data
public class EthBlockBaseVO {

    @ApiModelProperty(value = "区块号码", required = true)
    @NotNull(message = "区块号码不能为空")
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

}
