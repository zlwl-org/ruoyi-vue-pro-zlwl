package cn.iocoder.yudao.module.blockchain.dal.dataobject.user;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 用户钱包 DO
 *
 * @author ruanzh
 */
@TableName("blockchain_user_wallet")
@KeySequence("blockchain_user_wallet_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserWalletDO extends BaseDO {

    /**
     * 钱包编号
     */
    @TableId
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 名称
     */
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 网络
     *
     * 枚举 {@link TODO blockchain_net_type 对应的类}
     */
    private String net;
    /**
     * 代币
     */
    private String symbol;
    /**
     * 余额
     */
    private BigDecimal balance;

}
