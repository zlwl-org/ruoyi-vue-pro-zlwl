package cn.iocoder.yudao.module.blockchain.controller.admin.eth.vo;

import lombok.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import io.swagger.annotations.*;

import com.alibaba.excel.annotation.ExcelProperty;

/**
 * Eth区块数据 Excel VO
 *
 * @author ruanzh
 */
@Data
public class EthBlockExcelVO {

    @ExcelProperty("编号")
    private Long id;

    @ExcelProperty("区块号码")
    private String number;

    @ExcelProperty("区块哈希")
    private String hash;

    @ExcelProperty("父区块哈希")
    private String parentHash;

    @ExcelProperty("nonce")
    private String nonce;

    @ExcelProperty("sha3uncles")
    private String sha3uncles;

    @ExcelProperty("logs_bloom")
    private String logsBloom;

    @ExcelProperty("transactions_root")
    private String transactionsRoot;

    @ExcelProperty("state_root")
    private String stateRoot;

    @ExcelProperty("receipts_root")
    private String receiptsRoot;

    @ExcelProperty("author")
    private String author;

    @ExcelProperty("矿工")
    private String miner;

    @ExcelProperty("mix_hash")
    private String mixHash;

    @ExcelProperty("难度")
    private String difficulty;

    @ExcelProperty("总难度")
    private String totalDifficulty;

    @ExcelProperty("size")
    private String size;

    @ExcelProperty("gas_limit")
    private String gasLimit;

    @ExcelProperty("gas_used")
    private String gasUsed;

    @ExcelProperty("时间")
    private String timestamp;

    @ExcelProperty("base_fee_per_gas")
    private String baseFeePerGas;

    @ExcelProperty("是否处理")
    private Boolean done;

    @ExcelProperty("信息")
    private String info;

    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
