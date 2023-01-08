package cn.iocoder.yudao.module.blockchain.dal.dataobject.eth;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 以太坊主网地址 DO
 *
 * @author ruanzh
 */
@TableName("blockchain_eth_main_net_address")
@KeySequence("blockchain_eth_main_net_address_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EthMainNetAddressDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 地址
     */
    private String address;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 标签
     */
    private String tags;

}
