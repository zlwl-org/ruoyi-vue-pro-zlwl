package cn.iocoder.yudao.module.blockchain.dal.dataobject.infra;

import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 网络 DO
 *
 * @author ruanzh
 */
@TableName("blockchain_net")
@KeySequence("blockchain_net_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NetDO extends BaseDO {

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
     * 中文名称
     */
    private String nameZh;
    /**
     * 原生代币
     */
    private String symbol;
    /**
     * 浏览器
     */
    private String explorer;
    /**
     * 默认节点
     */
    private String publicRpc;
    /**
     * 私密节点
     */
    private String privateRpc;
    /**
     * 网络类型
     *
     * 枚举 {@link TODO blockchain_net_type 对应的类}
     */
    private String type;
    /**
     * 链ID
     */
    private Integer chainId;

}
