package cn.iocoder.yudao.module.blockchain.dal.dataobject.eth;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 以太坊账户 DO
 *
 * @author ruanzh
 */
@TableName("blockchain_eth_account")
@KeySequence("blockchain_eth_account_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EthAccountDO extends BaseDO {

    /**
     * 编号
     */
    @TableId
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 地址
     */
    private String address;
    /**
     * 助记词
     */
    private String mnemonic;
    /**
     * 私钥
     */
    private String privateKey;
    /**
     * 归属
     */
    private Boolean owned;
    /**
     * 网络
     */
    private String net;

}
