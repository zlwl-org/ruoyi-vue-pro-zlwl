package cn.iocoder.yudao.module.blockchain.dal.dataobject.infra;

import lombok.*;
import java.util.*;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

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
    private Integer nameZh;
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
     */
    private String type;
    /**
     * 链ID
     */
    private Integer chainId;

}
