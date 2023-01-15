package cn.iocoder.yudao.module.blockchain.dal.dataobject.eth;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * Eth区块数据 DO
 *
 * @author ruanzh
 */
@TableName("blockchain_eth_block")
@KeySequence("blockchain_eth_block_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EthBlockDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 区块号码
     */
    private String number;
    /**
     * 区块哈希
     */
    private String hash;
    /**
     * 父区块哈希
     */
    private String parentHash;
    /**
     * nonce
     */
    private String nonce;
    /**
     * sha3uncles
     */
    private String sha3uncles;
    /**
     * logs_bloom
     */
    private String logsBloom;
    /**
     * transactions_root
     */
    private String transactionsRoot;
    /**
     * state_root
     */
    private String stateRoot;
    /**
     * receipts_root
     */
    private String receiptsRoot;
    /**
     * author
     */
    private String author;
    /**
     * 矿工
     */
    private String miner;
    /**
     * mix_hash
     */
    private String mixHash;
    /**
     * 难度
     */
    private String difficulty;
    /**
     * 总难度
     */
    private String totalDifficulty;
    /**
     * size
     */
    private String size;
    /**
     * gas_limit
     */
    private String gasLimit;
    /**
     * gas_used
     */
    private String gasUsed;
    /**
     * 时间
     */
    private String timestamp;
    /**
     * base_fee_per_gas
     */
    private String baseFeePerGas;
    /**
     * 是否处理
     */
    private Boolean done;
    /**
     * 信息
     */
    private String info;

}
